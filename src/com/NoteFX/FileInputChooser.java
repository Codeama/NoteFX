
package com.NoteFX;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author  Jimoh
 */
public class FileInputChooser implements FileSystem {
    
    @Override
    public File openDirectory(){
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
    
    
}
