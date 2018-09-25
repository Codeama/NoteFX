package com.NoteFX;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 *
 * @author Bukola Jimoh
 */
public class NoteFXController {


    @FXML private TextArea textArea;
    @FXML private RadioMenuItem noteFX;
    @FXML private ToggleGroup group;
    private final FileIO file = FileIO.getInstance();
    private final PrintTask printer = new PrintTask();
    private final Exit exit = new Exit();
    private final StageTitle stage = new StageTitle();
    private final AlertBox alert = new AlertBox();
    /*textChange variable changes value to monitor changes as well as 
      cancel shortcut keys/combinations that are registered as changes*/
    private boolean textChange;




   /***methods begin here***/

    //opens file
    @FXML
    public void open(){
        if(isTextEdited()){
            textChange = alert.isTextChanged();
            alert.showSaveDialogBeforeOpenFile(textArea, textChange);
        }
        else{
            file.displayFileContent(textArea);
        }
    }

    //saves as file
    @FXML
    public void save(){
        file.saveContent(textArea);
        textChange = false;
    }
    
    //saves as new file
    @FXML
    public void saveAsNew(){
        file.saveAsNewFile(textArea);
        textChange = false;
    }
    
    //registers change(s) to text
    @FXML
    public void registerTextChange(){
          textChange = true;
          System.out.println(textChange);
     }
    
    /*
     ******checks if text is edited*****
      condition 1 checks text area is not empty and file is not saved
      condition 2 checks changes to existing/saved file
    */
    private boolean isTextEdited(){
        int textLength = textArea.getLength();

        return  (textLength > 0 && file.getPathName() == null) |   //condition 1
                (textChange && file.getPathName() != null);  //condition 2
    }
    
    //exits application
    @FXML public void exit(){
        if(isTextEdited()){
            alert.showSaveBeforeExit(textArea);
        }
        else{
            exit.closeWindow();
        }
    }
    
    //prints text area
    @FXML public void print(){
        printer.printText(textArea);}

    //creates new text area
    @FXML
    public void newStage(){
        if(isTextEdited())
            alert.showSaveBeforeNewPage(textArea);
        else{
            stage.clearStage(textArea);
            file.resetFileAndPathName();
        }
    }

    //undo change(s)
    @FXML public void undoChange(){
        textArea.undo();
    }

    //about NoteFX
    @FXML public void info(){
        AboutNoteFX.aboutMe();
    }

    //theme change
    public void initialize(){
        group.selectedToggleProperty().addListener(
            (obv, oldValue, newValue) -> {
                if(newValue == noteFX){
                    textArea.setStyle("-fx-control-inner-background:#000000; "
               + "-fx-font-family: Consolas; -fx-font-size: 16px; -fx-highlight-fill: #00ff00; "
               + "-fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00; ");
                }
                else
                    textArea.setStyle("-fx-control-inner-background:#FFFFFF; "
               + "-fx-font-family: Consolas; -fx-font-size: 16px; "
               + "-fx-text-fill: #000000; ");

    });
    }

}
