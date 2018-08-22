
package com.NoteFX;

import javafx.print.PrinterJob;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

/**
 *
 * @author Bukola Jimoh
 */
public class PrintTask {
    
    
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
