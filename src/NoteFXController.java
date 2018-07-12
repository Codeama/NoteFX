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
    FileMenu edit = new FileMenu();
    
   
   
    @FXML
    public void onOpen(){
        open.setOnAction(event ->  { 
            edit.openSelectedFile(textArea);
        });
    }
     
    @FXML
    public void onSave(){ 
        save.setOnAction(event -> {
                
            edit.saveContent(textArea);
        });
        
    }
    
    @FXML
    public void onSaveAs(){
        
    }
    
    @FXML public void onExit(){
        exitFile.setOnAction(event -> edit.closeWindow());
        
    }
    @FXML
    public void initialize(){
        
    }
    
     
}
