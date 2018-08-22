
package com.NoteFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 *
 * @author Bukola Jimoh
 */
public class AboutNoteFX {
    public static void aboutMe(){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About NoteFX");
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText("NoteFX");
        alert.setGraphic(null);
        alert.setContentText("This is a simple JavaFX text editor\n\n© Bukola Jimoh 2018");
         alert.showAndWait().ifPresent(response -> {
             if(response == ButtonType.OK)
                 alert.close();
         });
    }
    
}
