
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import javafx.application.Platform;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
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
    private String fileName = "Untitled";
    
      
   public void readSelectedFile(TextInputControl output){
       FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showOpenDialog(new Stage());
            
            if(file != null){
                output.setText(readFile(file));
                changeStageTitle(file, output);
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
   
   public void saveContent(TextInputControl output){
       if(pathName != null){
           File file = new File(pathName);
           saveFile(file, output);
       }
       else{
            File file = openDirectory();
            if(file != null){
                saveFile(file, output);
                changeStageTitle(file, output);
                pathName = file.getAbsolutePath();
            }
         }
    }
   
   public void saveAsNewFile(TextInputControl output){
       File file = openDirectory();   
        if(file != null){
            saveFile(file, output);
            changeStageTitle(file, output);
            pathName = file.getAbsolutePath();
        }
   }
   
   private File openDirectory(){
       FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");

            fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("All Files", "*.txt", "*.*"));
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"), 
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showSaveDialog(new Stage());
        
        return file;
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
        fileName = file.getName();
        String title = fileName + " - NoteFX";
        primaryStage.setTitle(title);
    }
    
    public void showConfirmation(TextInputControl output){
        ButtonType save = new ButtonType("Save");
        ButtonType dontSave = new ButtonType("Don't Save");
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        String messageAlert = "Do you want to save changes to ";
        String message = messageAlert + "'"+fileName+"'";
        Alert alert = new Alert(AlertType.CONFIRMATION, message,
            save, dontSave, cancel);
        alert.setHeaderText(null);
        alert.setTitle("Notepad");
        alert.showAndWait().ifPresent(response -> {
            if(response == save){
                 if(pathName != null){
                    File file = new File(pathName);
                    saveFile(file, output); //save and close owner window/stage
                    closeWindow();
                    }
                 else{
                    File file = openDirectory();
                    if(file != null){
                        saveFile(file, output);
                        closeWindow();
                        }
                    else alert.close();//go back to owner window
                    }
            }
            if(response == dontSave)
                closeWindow();
            if (response == cancel)
                System.out.println("Can't close me!"); //this closes owner window but it's not the intended outcome. method showConfirmation may need re-doing
                
                
        }   );
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
