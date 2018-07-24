/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;



/**
 *
 * @author Bukola Jimoh
 */
public class NoteFXController {
    
    @FXML private MenuBar menu;
    
    @FXML private Menu file;
    
    @FXML private MenuItem exitFile;
    
    @FXML private TextArea textArea;
    
    @FXML private MenuItem open;
    
    @FXML private MenuItem save;
    
    FileMenu fileMenu = new FileMenu();
    
    boolean textEdited;
    
    @FXML
    public void isKeyPressed(){
        textEdited = textArea.getText()!= null;
        
    }
   
    @FXML
    public void onOpen(){fileMenu.openSelectedFile(textArea);}
     
    @FXML
    public void onSave(){fileMenu.saveContent(textArea);}
                
    @FXML
    public void onSaveAs(){fileMenu.saveAsNewFile(textArea);}
    
       
    @FXML public void onExit(){
        if (textEdited==true) 
            fileMenu.showConfirmation(textArea);
        else
            fileMenu.closeWindow();
    }
    
    
    @FXML
    public void initialize(){
        
        
    }
    
     
}
