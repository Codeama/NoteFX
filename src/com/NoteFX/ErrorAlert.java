
package com.NoteFX;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 *
 * @author BUKOLA
 */
public class ErrorAlert{
    public static void message(String text){
       Alert alert = new Alert(AlertType.ERROR, text);
       alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.initStyle(StageStyle.UTILITY);
       alert.showAndWait().ifPresent(response -> {
           if(response == ButtonType.OK){
               alert.close();
           }
       });
    }
    
}
