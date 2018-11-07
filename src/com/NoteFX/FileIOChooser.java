
package com.NoteFX;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author BUKOLA
 */
public class FileIOChooser {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter[] filters = filters = new FileChooser.ExtensionFilter[]{
            new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"), 
            new FileChooser.ExtensionFilter("All Files", "*.*")};;
    File file;
    private static FileIOChooser INSTANCE;
    
    private FileIOChooser(){
        
    }
    
    //singleton
    public static FileIOChooser getInstance(){
        if(INSTANCE == null)
            INSTANCE = new FileIOChooser();
        return INSTANCE;
    }

    //creates persistent initial directory 
    private void setLastFileDirectory(){
        if(file != null){
            File lastDirectory = file.getParentFile();
            fileChooser.setInitialDirectory(lastDirectory);
        }
    }
    
     public File openSourceDirectory(){
        setLastFileDirectory();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(filters);
        file = fileChooser.showOpenDialog(new Stage());
        
        return file;
    }
     
      public File openSaveDirectory(){
        setLastFileDirectory();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll(filters);
        file = fileChooser.showSaveDialog(new Stage());
        
        return file;
    }
    
}
