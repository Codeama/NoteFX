package com.NoteFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;



/**
 *
 * @author Bukola Jimoh
 */
public class NoteFXController {
    
       
    @FXML private TextArea textArea;
    @FXML private RadioMenuItem noteFX;
    @FXML private RadioMenuItem classic;
    private final FileIO file = new FileIO();
    private final PrintTask printer = new PrintTask();
    private final Exit exit = new Exit();
    private final StageTitle stage = new StageTitle();
    
    private boolean textEdited;
    private int textLength;
    private int changeCount;
    
    
    
    @FXML
    public void newStage(){
        if(file.getPathName() != null && textEdited == true)
            file.showSaveConfirmation(textArea);
        else{
            stage.clearStage(textArea);
            textLength = textArea.getLength();
            file.setFileName(stage.getFileName()); /*updates file name with 
                                                           current stage title*/
        }
    }
   
    @FXML
    public void open(){
        file.displayFileContent(textArea); 
        textLength = textArea.getLength(); //gets length of text before changes
    }
    
    /*check method bug for save prompt on exit*/
    @FXML
    public void save(){
        textLength = textArea.getLength(); //gets length of text before saving
        file.saveContent(textArea);
    } 
                
    @FXML
    public void saveAsNew(){
        textLength = textArea.getLength(); //get length of text before saving 
        file.saveAsNewFile(textArea);
    }
    
    @FXML
    public void registerTextChange(){ //register changes to text
        int text = textArea.getLength();
        if(textLength != text)
           changeCount++;
    }
    
//    private boolean isTextEdited(){
//        int newLength = textArea.getLength();
//        
//        return (newLength != 0 && file.getPathName() == null) ||
//                (newLength != textLength && file.getPathName() != null) &&
//                (changeCount > 0);
//    }
   
    @FXML public void exit(){
        int newLength = textArea.getLength();
        
        //check text area has content && file doesn't yet exist (not saved)
        if (newLength != 0 && file.getPathName() == null)
            textEdited = true;
        //check current text length has changed && file exists (has been saved)
        if (newLength != textLength && file.getPathName() != null)
                textEdited = true;

        //check text changes before exit
        if (textEdited == true & changeCount > 0 | changeCount > 0){
            file.showConfirmation(textArea, file.getFileName());
            if (newLength != 0 && file.getPathName() == null) //re-assess stage
            textEdited = true;
            
            //set a true condition for check
        System.out.println("changes: " +changeCount);}
        else
            exit.closeWindow();
    }
    
    @FXML public void print(){printer.printText(textArea);}
    
    @FXML public void undoChange(){
        textArea.undo();
    }
    
    @FXML public void changeBackground(){
        if(noteFX.isSelected()){
        textArea.setStyle("-fx-control-inner-background:#000000; "
               + "-fx-font-family: Consolas; -fx-font-size: 16px; -fx-highlight-fill: #00ff00; "
               + "-fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00; ");
        }
        else
            textArea.setStyle(null);//set this appropriately
    }
    
    @FXML public void info(){
        AboutNoteFX.aboutMe();
    }
    
    
    @FXML
    public void initialize(){

    }
    
     
}
