package com.scc210groupproject.homeDisplay;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;


public class runHome {


    public static void main(String[] args) throws InvocationTargetException, InterruptedException
    {
        System.setProperty("flatlaf.useWindowDecorations", "true");
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatLightLaf.setup();
        FlatDarkLaf.setup();

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }
        catch (UnsupportedLookAndFeelException e) {
            System.out.println("Falling back to default style");
        }
      
           
               
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
              //  new UIFrame();
               new homeDisplay();
            }
            
        });            
            
        
        
    }
}