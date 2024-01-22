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
