
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputControl;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bukola Jimoh
 */
public class FileMenu {
    
    private String pathName = null; 
      
   public void openSelectedFile(TextInputControl output){
       FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showOpenDialog(new Stage());
            
            if(file != null){
                output.setText(open(file));
                changeStageTitle(file, output);
                pathName = file.getAbsolutePath();
          }
   }
   
   private String open(File file) {
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
   
   public void saveContent(TextInputControl output){
       if(pathName != null){
           File file = new File(pathName);
           saveFile(file, output);
       }
       else{

       FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");

            fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("All Files", "*.txt", "*.*"));
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"), 
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showSaveDialog(new Stage());    
            if(file != null){
                saveFile(file, output);
                changeStageTitle(file, output);
                pathName = file.getAbsolutePath();
            }
         }
    }
   
   public void saveAsNewFile(TextInputControl output){
       FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");

            fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("All Files", "*.txt", "*.*"));
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"), 
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showSaveDialog(new Stage());    
        if(file != null){
            saveFile(file, output);
            changeStageTitle(file, output);
            pathName = file.getAbsolutePath();
        }
   }
   
   
    private void saveFile(File file, TextInputControl output){

        String content = output.getText();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }

        catch(IOException ex){
            ex.getMessage();
        }
    }
    
    private void changeStageTitle(File file, TextInputControl currentTextArea){
        Stage primaryStage = (Stage)currentTextArea.getScene().getWindow();
        String title = file.getName() + " - NoteFX";
        primaryStage.setTitle(title);
    }
    
    private void showConfirmation(TextInputControl output){
        ButtonType saveButton;
        saveButton = new ButtonType("Save", ButtonBar.ButtonData.APPLY);
        ButtonType dontSaveButton = new ButtonType("Don't Save");
        ButtonType cancelButton = new ButtonType("Cancel");
        Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to save changes to...",
            saveButton, dontSaveButton, cancelButton);
        alert.setTitle("Notepad");
        alert.showAndWait()
                .filter(response -> response == ButtonType.APPLY)
                .ifPresent(response -> saveContent(output));
    }
    
    
   public void closeWindow(){
       Platform.exit();
   } 
}
