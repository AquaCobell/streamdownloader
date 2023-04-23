package com.nico;


import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class Clipboardreader {

    Clipboardreader()
    {

    }
    
    public  String getClipboard()
    {
        try 
        {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } 
        catch (Exception e)
        {
            System.out.println("Error");
        }
        return null;
    }
      
    
}
    