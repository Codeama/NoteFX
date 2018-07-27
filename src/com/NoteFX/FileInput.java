/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NoteFX;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Bukola
 */
public class FileInput implements SystemDirectory {
    
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
