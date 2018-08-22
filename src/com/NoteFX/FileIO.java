package com.NoteFX;


import java.io.BufferedReader;
import java.io.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


/**
 *
 * @author Bukola Jimoh
 */
public class FileIO {
    
    private String pathName = null;
    private String fileName = "Untitled";
    StageTitle stage = new StageTitle();
    Exit exit = new Exit();
    /*********buttons for save alert********/
    ButtonType save = new ButtonType("Save");
    ButtonType dontSave = new ButtonType("Don't Save");
    ButtonType cancel = new ButtonType("Cancel");
    
    public String getPathName(){
        return pathName;
    }
    
    public void setPathName(String pathName){
        this.pathName = pathName;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public void setFileName(String fileName){
        if(fileName == null)
            throw new IllegalArgumentException("File name cannot be null.");
        this.fileName = fileName;
    }
    
      //display file content on output area
   public void displayFileContent(TextInputControl output){
        FileOutputChooser directory = new FileOutputChooser();
        File file = directory.openDirectory();
            if(file != null){
                output.setText(readFile(file));
                stage.updateStageTitle(file, output);
                fileName = file.getName();
                pathName = file.getAbsolutePath();
            }
   }
   
   protected String readFile(File file) {
        StringBuilder text = new StringBuilder();
        try (
            FileInputStream input = new FileInputStream(file);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(input));){
            String line;
            while((line = inputStream.readLine()) != null){
                    text.append(line).append("\n");
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        return text.toString();
        
    }
   
  
        //saves or updates already existing text file
   public void saveContent(TextInputControl content){
       if(pathName != null){
           File file = new File(pathName);
           saveFile(file, content);
       }
       else{
            FileInputChooser directory = new FileInputChooser();
            File file = directory.openDirectory();
            if(file != null){
                saveFile(file, content);
                stage.updateStageTitle(file, content);
                fileName = file.getName();
                pathName = file.getAbsolutePath();
            }
         }
    }
        //saves text as new file
   public void saveAsNewFile(TextInputControl content){
       FileInputChooser directory = new FileInputChooser();
       File file = directory.openDirectory();   
        if(file != null){
            saveFile(file, content);
            stage.updateStageTitle(file, content);
            fileName = file.getName();
            pathName = file.getAbsolutePath();
        }
   }
   
       
    protected void saveFile(File file, TextInputControl text){
        String content = text.getText();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
    
    public void showSaveConfirmation(TextInputControl text){
        String messageAlert = "Do you want to save changes to " + "'"+fileName+"'";
        Alert alert = new Alert(AlertType.CONFIRMATION, messageAlert,
            save, dontSave, cancel);
        alert.setHeaderText(null);
        alert.setTitle("NoteFX");
        alert.showAndWait().ifPresent(response -> {
            if(response == save){
                saveContent(text);
                resetFileAndPathName();
                stage.clearStage(text);
            }
            if(response == dontSave){
                resetFileAndPathName();
                stage.clearStage(text);
            }
            if (response == cancel)
                alert.close();
            } );
    }
    
    protected void resetFileAndPathName(){
        pathName = null;
        fileName = stage.getFileName();
    }
    
    public void showConfirmation(TextInputControl text){
        String messageAlert = "Do you want to save changes to " + "'"+fileName+"'";
        Alert alert = new Alert(AlertType.CONFIRMATION, messageAlert,
            save, dontSave, cancel);
        alert.setHeaderText(null);
        alert.setTitle("NoteFX");
        alert.showAndWait().ifPresent(response -> {
            if(response == save)
                saveAndClose(pathName, text, alert);
            if(response == dontSave)
                exit.closeWindow();
            if (response == cancel)
                alert.close();
            } );
    }
   
    
    protected void saveAndClose(String path, TextInputControl text, Alert window){
        if(path != null){
            File file = new File(path);
            saveFile(file, text); //save and close main window/stage
            exit.closeWindow();
        }
        else{
            FileInputChooser directory = new FileInputChooser();
            File file = directory.openDirectory();
            if(file != null){
                saveFile(file, text);
                exit.closeWindow();
                }
            else window.close();//go back to main window
        }
    }
    
}
