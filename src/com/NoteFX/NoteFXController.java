package com.NoteFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;



/**
 *
 * @author Bukola Jimoh
 */
public class NoteFXController {
    
       
    @FXML private TextArea textArea;
    @FXML private MenuItem save;
    FileMenu fileMenu = new FileMenu();
    PrintTask printer = new PrintTask();
    
    boolean textEdited;
    
    
   
    @FXML
    public void open(){fileMenu.displayFileContent(textArea);}
     
    @FXML
    public void save(){fileMenu.saveContent(textArea);}
                
    @FXML
    public void saveAsNew(){fileMenu.saveAsNewFile(textArea);}
    
    @FXML
    public void keyPressed(){
        textEdited = textArea.getLength() != 0;
        
    }
    
    @FXML public void exit(){
        if (textEdited==true) 
            fileMenu.showConfirmation(textArea);
        else
            fileMenu.closeWindow();
    }
    
    @FXML public void print(){printer.printText(textArea);}
    
    
    @FXML
    public void initialize(){
        //save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        
        
    }
    
     
}
