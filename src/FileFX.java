
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Platform;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bukola Jimoh
 */
public class FileFX {
   public void closeWindow(){
       Platform.exit();
   }
   
   public void openSelectedFile(TextInputControl output){
       FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showOpenDialog(new Stage());
            
            if(file != null)
                output.setText(open(file));
   }
   
   private String open(File file) {
        StringBuilder str = new StringBuilder();
        try (
            FileInputStream input = new FileInputStream(file);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(input));){
            String line;
            
            while((line = inputStream.readLine()) != null){
                    str.append(line).append("\n");
            }
            
        } catch (IOException ex) {
            ex.getMessage();
        }
        return str.toString();
        
    }
    
}
