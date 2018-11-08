package com.NoteFX;


import java.io.BufferedReader;
import java.io.*;
import javafx.scene.control.*;


/**
 *
 * @author Bukola Jimoh
 */
public class FileIO {
    private static FileIO INSTANCE;
    
    private String pathName = null;
    private String fileName = "Untitled";
    private final StageTitle stage = new StageTitle();
    private final FileIOChooser directory = FileIOChooser.getInstance();
    
    private FileIO(){
    }
    
    //singleton
    public static FileIO getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FileIO();
        }
        return INSTANCE;
    }
    
        /**methods start here*/
    
    //see method isTextEdited in NoteFXController 
    public String getPathName(){
        return pathName;
    }
    
    public String getFileName(){
        return fileName;
    }

    //display file content on output area
   public void displayFileContent(TextInputControl output){
        File file = directory.openSourceDirectory();
            if(file != null){
                output.setText(readFile(file));
                stage.updateStageTitle(file, output);
                fileName = file.getName();
                pathName = file.getAbsolutePath();
            }
   }

   //utility method for displayFileContent
    String readFile(File file) {
        StringBuilder text = new StringBuilder();
        try (
            FileInputStream input = new FileInputStream(file);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(input));){
            String line;
            while((line = inputStream.readLine()) != null){
                    text.append(line).append("\n");
            }
        } catch (IOException ex) {
            ErrorAlert.message("Error opening file.");
        }
        return text.toString();
    }


   //save text as file
   public void saveContent(TextInputControl content){
       if(pathName != null){
           File file = new File(pathName);
           saveFile(file, content);
       }
       else{
            saveAsNewFile(content);
        }
    }
   
   
        //save text as new file
    void saveAsNewFile(TextInputControl content){
       File file = directory.openSaveDirectory();   
        if(file != null){
            saveFile(file, content);
            stage.updateStageTitle(file, content);
            fileName = file.getName();
            pathName = file.getAbsolutePath();
        }
   }
   
    //utility method for save methods   
    void saveFile(File file, TextInputControl text){
        String content = text.getText();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        catch(IOException ex){
            ErrorAlert.message("Oops! Something went wrong. Please try again.");
        }
    }

    
    //utility method for showSaveConfirmation and showExitConfirmation
    protected void resetFileAndPathName(){
        pathName = null;
        fileName = "Untitled";
    }
}
