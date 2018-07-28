/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NoteFX;

import javafx.print.PrinterJob;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

/**
 *
 * @author BUKOLA
 */
public class PrintTask {
    
    //this method needs refactoring
    public void printText(TextInputControl text){
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
            Stage primaryStage = (Stage)text.getScene().getWindow();
            boolean ready = job.showPrintDialog(primaryStage);
                if(ready){
                    boolean success = job.printPage(text);
                        if(success){
                            job.endJob();
                        }
                }
        }
    }
    
    
}
