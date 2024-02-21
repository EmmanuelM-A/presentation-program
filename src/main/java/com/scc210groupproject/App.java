package com.scc210groupproject;


import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.scc210groupproject.ui.UIFrame;

/**
 * This where the program is run
 * */

public class App {
    public static void main( String[] args ) throws InvocationTargetException, InterruptedException
    {
        System.setProperty("flatlaf.useWindowDecorations", "true");
        FlatLaf.registerCustomDefaultsSource("com.scc210groupproject.theme");
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
                new UIFrame();
            }
            
        });
        
    }
}

/*
    TODO LIST:
    - Do animations and transistions check discord
    - Settings windows
    - mini toolbar - implement the remaining buttons
    - drag feature to slides
    - shortcuts windows
    - slide manager fix errors DELETE
    - presentation mode
    - Color chooser dialog for text, shapes, arrow
    - Video element
    - Export to pdf
*/
