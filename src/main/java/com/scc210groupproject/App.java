package com.scc210groupproject;


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
    public static void main( String[] args )
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

        new UIFrame();
    }
}

/*
    TODO LIST:
    - Do animations and transistions check discord
    - Settings windows
    - Dark and light mode
    - Font and text section
    - mini toolbar
    - drag feature to slides
    - shortcuts windows
*/
