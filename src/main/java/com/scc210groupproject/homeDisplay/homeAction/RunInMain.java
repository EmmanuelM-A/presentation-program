package com.scc210groupproject.homeDisplay.homeAction;

import javax.swing.SwingUtilities;

import com.scc210groupproject.PresentationApp;
import com.scc210groupproject.homeDisplay.HomeDisplay;
import com.scc210groupproject.ui.UIFrame;

/**
 * method to run open in new thread launched when hoescreen button pressed
 * uses reflection
 */
public class RunInMain {

    public static void runPresetFile(String newFilePath){
        
    //create new thread close current run main App with open
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {

                    @Override
                    public void run() {
                        new UIFrame();
                    }
            
                });
                //PresentationApp.main(new String[]{});

                //call open to run in main for new thread launch
                
                Thread.currentThread().interrupt();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }).start();

        //run main
        
    }
}
