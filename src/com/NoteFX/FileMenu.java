package com.NoteFX;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Platform;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;


/**
 *
 * @author Bukola Jimoh
 */
public class FileMenu {
    
    private String pathName = null;
    private String fileName = "Untitled";
    
      //display file content on output area
   public void displayFileContent(TextInputControl output){
        FileOutput directory = new FileOutput();
        File file = directory.openDirectory();
            if(file != null){
                output.setText(readFile(file));
                updateStageTitle(file, output);
                pathName = file.getAbsolutePath();
          }
   }
   
   private String readFile(File file) {
        StringBuilder str = new StringBuilder();
        try (
            FileInputStream input = new FileInputStream(file);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(input));){
            String line;
            while((line = inputStream.readLine()) != null){
                    str.append(line).append("\n");
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        return str.toString();
        
    }
        //save text as file
   public void saveContent(TextInputControl content){
       if(pathName != null){
           File file = new File(pathName);
           saveFile(file, content);
       }
       else{
            FileInput directory = new FileInput();
            File file = directory.openDirectory();
            if(file != null){
                saveFile(file, content);
                updateStageTitle(file, content);
                pathName = file.getAbsolutePath();
            }
         }
    }
        //save text as new file
   public void saveAsNewFile(TextInputControl content){
       FileInput directory = new FileInput();
       File file = directory.openDirectory();   
        if(file != null){
            saveFile(file, content);
            updateStageTitle(file, content);
            pathName = file.getAbsolutePath();
        }
   }
   
       
    private void saveFile(File file, TextInputControl text){
        String content = text.getText();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
    
        //update stage title with file name
    private void updateStageTitle(File file, TextInputControl currentTextArea){
        Stage primaryStage = (Stage)currentTextArea.getScene().getWindow();
        fileName = file.getName();
        String title = fileName + " - NoteFX";
        primaryStage.setTitle(title);
    }
    
    public void showConfirmation(TextInputControl text){
        ButtonType save = new ButtonType("Save");
        ButtonType dontSave = new ButtonType("Don't Save");
        ButtonType cancel = new ButtonType("Cancel");
        String messageAlert = "Do you want to save changes to ";
        String message = messageAlert + "'"+fileName+"'";
        Alert alert = new Alert(AlertType.CONFIRMATION, message,
            save, dontSave, cancel);
        alert.setHeaderText(null);
        alert.setTitle("Notepad");
        alert.showAndWait().ifPresent(response -> {
            if(response == save){
                saveAndClose(pathName, text, alert);
            }
            if(response == dontSave)
                closeWindow();
            if (response == cancel)
                alert.close();
            } );
    }
    
    private void saveAndClose(String path, TextInputControl text, Alert window){
        if(path != null){
            File file = new File(path);
            saveFile(file, text); //save and close owner window/stage
            closeWindow();
        }
        else{
            FileInput directory = new FileInput();
            File file = directory.openDirectory();
            if(file != null){
                saveFile(file, text);
                closeWindow();
                }
            else window.close();//go back to owner window
        }
    }
    
      
    public void printText(TextInputControl text){
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
            boolean success = job.printPage(text);
            if(success){
                job.endJob();
            }
        }
    }
    
    
   public void closeWindow(){
       Platform.exit();
   } 
}
