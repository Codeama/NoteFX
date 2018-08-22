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
    private final FileIO file = new FileIO();
    private final PrintTask printer = new PrintTask();
    private final Exit exit = new Exit();
    private final StageTitle stage = new StageTitle();
    /**text change variables**/
    private int textLength;
    private int changeCount;
   
    
    
    
   /***methods begin here***/
    
    //open file
    @FXML
    public void open(){
        file.displayFileContent(textArea); 
        textLength = textArea.getLength(); 
    }
    
    //save as file
    @FXML
    public void save(){
        textLength = textArea.getLength(); 
        file.saveContent(textArea);
    } 
    //save as new file            
    @FXML
    public void saveAsNew(){
        textLength = textArea.getLength();  
        file.saveAsNewFile(textArea);
    }
    //register change(s) to text
    @FXML
    public void registerTextChange(){ 
        int text = textArea.getLength();
        if(textLength != text)
           changeCount++;
    }
    //check if text is edited
    private boolean isTextEdited(){
        int newLength = textArea.getLength();
        
        return  (newLength != 0 && file.getPathName() == null) |
                (newLength != textLength && file.getPathName() != null) &&
                (changeCount > 0);
    }
    //exit application
    @FXML public void exit(){
        if(isTextEdited()){
            file.showExitConfirmation(textArea);
        }
        else{
            exit.closeWindow();
        }
    }
    //print text area
    @FXML public void print(){
        printer.printText(textArea);}
    
    //create new text area
    @FXML
    public void newStage(){
        if(isTextEdited())
            file.showSaveConfirmation(textArea);
        else{
            stage.clearStage(textArea);}
            textLength = textArea.getLength();
            file.resetFileAndPathName(); 
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
