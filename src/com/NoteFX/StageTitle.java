
package com.NoteFX;

import java.io.File;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

/**
 *
 * @author Bukola Jimoh
 */
public class StageTitle {
    
    private String fileName;
    
    
    protected void updateStageTitle(File file, TextInputControl textArea){
        Stage primaryStage = (Stage)textArea.getScene().getWindow();
        fileName = file.getName(); //update file name
        String title = fileName + " - NoteFX";
        primaryStage.setTitle(title);
    }
    
    protected void clearStage(TextInputControl textArea){
        fileName = "Untitled";
        Stage primaryStage = (Stage)textArea.getScene().getWindow();
        String title = fileName + " - NoteFX";
        primaryStage.setTitle(title);
        textArea.clear();
    }
    
    //see method resetFileAndPathName() in FileIO
    public String getFileName(){
        return fileName;
    }

}
