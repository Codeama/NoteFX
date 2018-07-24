/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 *
 * @author Bukola Jimoh
 */
public class NoteFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("NoteFX.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("NoteFX.fxml"));
        Parent root = fxml.load();
        MenuItem exit = (MenuItem) fxml.getNamespace().get("exitFile");
        
        Scene scene = new Scene(root);
        stage.setTitle("Untitled - NoteFX");
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(e -> exit.fire());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
