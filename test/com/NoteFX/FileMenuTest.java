/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NoteFX;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BUKOLA
 */
public class FileMenuTest {
    
    public FileMenuTest() {
    }

    /**
     * Test of displayFileContent method, of class FileMenu.
     */
    @Test
    public void testDisplayFileContent() {
        //FileMenu file = new FileMenu();
    }

    /**
     * Test of saveContent method, of class FileMenu.
     */
    @Test
    public void testSaveContent() {
    }

    /**
     * Test of saveAsNewFile method, of class FileMenu.
     */
    @Test
    public void testSaveAsNewFile() {
    }

    /**
     * Test of showConfirmation method, of class FileMenu.
     */
    @Test
    public void testShowConfirmation() {
    }

    /**
     * Test of closeWindow method, of class FileMenu.
     */
    @Test
    public void testCloseWindow() {
    }

    /**
     * Test of readFile method, of class FileMenu.
     */
    @Test
    public void testReadFile() throws URISyntaxException {
        FileMenu fileMenu = new FileMenu();
        String hello = "Hello World!\n";
        URL url = getClass().getResource("hello.txt");
        File file = new File(url.toURI());
        assertEquals(hello, fileMenu.readFile(file));
    }

    /**
     * Test of saveFile method, of class FileMenu.
     */
    @Test
    public void testSaveFile() {
    }

    /**
     * Test of saveAndClose method, of class FileMenu.
     */
    @Test
    public void testSaveAndClose() {
    }
    
}
