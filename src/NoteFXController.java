/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Desktop;
import javafx.fxml.FXML;
import javafx.scene.control.*;



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
    
    FileMenu fileMenu = new FileMenu();
    
   
   
    @FXML
    public void onOpen(){
        open.setOnAction(event -> fileMenu.openSelectedFile(textArea));
    }
     
    @FXML
    public void onSave(){ 
        save.setOnAction(event -> fileMenu.saveContent(textArea));
        
    }
    
    @FXML
    public void onSaveAs(){
        
    }
    
    @FXML public void onExit(){
        exitFile.setOnAction(event -> fileMenu.closeWindow());
        
    }
    @FXML
    public void initialize(){
        
    }
    
     
}
