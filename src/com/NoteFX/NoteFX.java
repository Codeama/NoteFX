package com.NoteFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Bukola Jimoh
 */
public class NoteFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("NoteFX.fxml"));
        Parent root = fxml.load();
        MenuItem exit = (MenuItem) fxml.getNamespace().get("exitFile");
        
        Scene scene = new Scene(root);
        stage.setTitle("Untitled - NoteFX");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jpg")));
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest(e -> {
                Platform.setImplicitExit(false); //prevent external close call before exit.fire()
                exit.fire();
                e.consume();});
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
