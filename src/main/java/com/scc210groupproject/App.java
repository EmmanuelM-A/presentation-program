package com.scc210groupproject;

//import com.scc210groupproject.ui.ApplicationFrame;
import com.scc210groupproject.applicationWIndow.ApplicationWindow;

/**
 * This where the program is run
 * */

public class App {
    public static void main( String[] args )
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ApplicationWindow();
            }
        });
    }
}
