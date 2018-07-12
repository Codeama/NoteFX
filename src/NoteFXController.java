/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 *
 * @author Bukola Jimoh
 */
public class NoteFXController {
    
    @FXML private MenuBar menu;
    
    @FXML private Menu file;
    
    @FXML private MenuItem exitFile;
    
    @FXML private TextArea textArea;
    
    @FXML private MenuItem open;
    
    @FXML private MenuItem save;
    
    private Desktop desktop = Desktop.getDesktop();
    
   
    @FXML public void onExit(){
        exitFile.setOnAction(event -> Platform.exit());
        
    }
    
    @FXML
    public void onOpen(){
        open.setOnAction(event ->  { 
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*.txt"),
                    new ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showOpenDialog(new Stage());
            
            if(file != null)
                textArea.setText(openFile(file));
        });
    }
    
    
    @FXML
    public void onSave(){ 
        save.setOnAction(event -> {
                
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");

            fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("All Files", "*.txt", "*.*"));
            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showSaveDialog(new Stage());    
            if(file != null)
                saveFile(file);
            Stage changeTitle = (Stage)textArea.getScene().getWindow();
            changeTitle.setTitle("");
        });
        
    }
    
    private void changeWindowTitle(){
        
    }
    
    @FXML
    public void onSaveAs(){
        
    }
        
    private String openFile(File file) {
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
    
    private void saveFile(File file){

        String content = textArea.getText();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
         
        catch(IOException ex){
            ex.getMessage();
        }
       
    }
    
    @FXML
    public void initialize(){
        
    }
    
     
}
