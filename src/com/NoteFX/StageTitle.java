
package com.NoteFX;

import java.io.File;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

/**
 *
 * @author Bukola Jimoh
 */
public class StageTitle {
    
    String fileName;
    String pathName;
    
    protected void updateStageTitle(File file, TextInputControl textArea){
        Stage primaryStage = (Stage)textArea.getScene().getWindow();
        fileName = file.getName(); //update file name
        String title = fileName + " - NoteFX";
        primaryStage.setTitle(title);
        pathName = file.getAbsolutePath();
    }
    
    protected void clearStage(TextInputControl textArea){
        fileName = "Untitled";
        Stage primaryStage = (Stage)textArea.getScene().getWindow();
        String title = fileName + " - NoteFX";
        primaryStage.setTitle(title);
        textArea.clear();
    }
    
    public String getFileName(){
        return fileName;
    }

}
