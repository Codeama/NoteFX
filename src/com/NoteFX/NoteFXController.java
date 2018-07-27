package com.NoteFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.NoteFX.FileMenu;
import java.awt.event.KeyEvent;
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
    
    boolean textEdited;
    
    @FXML
    public void keyPressed(){
        textEdited = textArea.getText()!= null;
        
    }
   
    @FXML
    public void open(){fileMenu.displayFileContent(textArea);}
     
    @FXML
    public void save(){fileMenu.saveContent(textArea);}
                
    @FXML
    public void saveAsNew(){fileMenu.saveAsNewFile(textArea);}
    
       
    @FXML public void exit(){
        if (textEdited==true) 
            fileMenu.showConfirmation(textArea);
        else
            fileMenu.closeWindow();
    }
    
    @FXML public void print(){fileMenu.printText(textArea);}
    
    
    @FXML
    public void initialize(){
        
        
    }
    
     
}
