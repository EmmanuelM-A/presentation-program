package com.scc210groupproject.homeDisplay.homeAction;

import com.scc210groupproject.PresentationApp;

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
                
                PresentationApp.main(new String[]{});

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
