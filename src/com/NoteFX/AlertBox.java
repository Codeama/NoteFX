/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NoteFX;

import java.io.File;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputControl;
import javafx.stage.StageStyle;

/**
 *
 * @author BUKOLA
 */
public class AlertBox {
    FileIO fileIO;
    StageTitle stage;
    private final Exit exit;
    Alert alert;
    private final ButtonType save;
    private final ButtonType dontSave;
    private final ButtonType cancel;
    private final FileIOChooser directory;
    

    
    public AlertBox(){
        this.directory = FileIOChooser.getInstance();
        this.exit = new Exit();
        this.stage = new StageTitle();
        this.fileIO = FileIO.getInstance();
        this.cancel = new ButtonType("Cancel");
        this.dontSave = new ButtonType("Don't Save");
        this.save = new ButtonType("Save");
    }
    
    
    private void setAlertProperties(){
        String messageAlert = "Do you want to save changes to " + "'"+fileIO.getFileName()+"'";
        alert = new Alert(Alert.AlertType.CONFIRMATION, messageAlert,
            save, dontSave, cancel);
        alert.setHeaderText(null);
        alert.setTitle("NoteFX");
        alert.initStyle(StageStyle.UTILITY);
    }
    
    public void showSaveBeforeNewPage(TextInputControl text){
        setAlertProperties();
        alert.showAndWait().ifPresent(response -> {
            if(response == save){
                saveBeforeNewPage(fileIO.getPathName(), text, alert);
            }
            if(response == dontSave){
                fileIO.resetFileAndPathName();
                stage.clearStage(text);
            }
            if (response == cancel)
                alert.close();
            } );
        
    }
    
    //alert save before exit
    public void showSaveBeforeExit(TextInputControl text){
        setAlertProperties();
        alert.showAndWait().ifPresent(response -> {
            if(response == save)
                saveAndClose(fileIO.getPathName(), text, alert); //this ensures if user doesn't save in SaveDialog, it goes back to screen
            if(response == dontSave)
                exit.closeWindow();
            if (response == cancel)
                alert.close();
            } );
    }
    
    private void saveAndClose(String path, TextInputControl text, Alert window){
        if(path != null){
            File file = new File(path);
            fileIO.saveFile(file, text); //save and close main window/stage
            exit.closeWindow();
        }
        else{
            File file = directory.openSaveDirectory();
            if(file != null){
                fileIO.saveFile(file, text);
                exit.closeWindow();
                }
            else window.close();//go back to main window
        }
    }
        
        private void saveBeforeNewPage(String path, TextInputControl text, Alert window){
            if(path != null){
                File file = new File(path);
                fileIO.saveFile(file, text); //save and close main window/stage
                fileIO.resetFileAndPathName();
                stage.clearStage(text);
            }
             else{
                File file = directory.openSaveDirectory();
                if(file != null){
                    fileIO.saveFile(file, text);
                    fileIO.resetFileAndPathName();
                    stage.clearStage(text);
                    }
                else window.close();//go back to main window
            }
        }
        
        //alert save before opening new file
    public void showSaveDialogBeforeOpenFile(TextInputControl text){
        setAlertProperties();
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == save){
            saveBeforeOpenFile(fileIO.getPathName(), text);
        }
        if(result.isPresent() && result.get() == dontSave)
            fileIO.displayFileContent(text);
        if(result.isPresent() && result.get() == cancel)
            alert.close();
    }
        
        private void saveBeforeOpenFile(String path, TextInputControl text){
            if(path != null){  //if path is not null, save, else open directory for user to save
                File file = new File(path);
                fileIO.saveFile(file, text);
                fileIO.displayFileContent(text);//opens fileChooser for file selection
            }
            else{
                fileIO.saveAsNewFile(text); //go back to stage/window after saving as new
                }
        }

}
