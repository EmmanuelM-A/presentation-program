package com.scc210groupproject;

import com.scc210groupproject.ui.UIFrame;

/**
 * This where the program is run
 * */

public class App {
    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
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
                        - Dark and light mode
                        - Font and text section
                        - mini toolbar
                        - drag feature to slides
                        - shortcuts windows
                 */
