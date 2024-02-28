package com.scc210groupproject.homeDisplay;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.scc210groupproject.App;
import com.scc210groupproject.readwrite.FileDeserializer;

/**Allow  setting the filetypes that will be displayed on a homeContentPanel */
/**
 * @author @leewelto
 */
public class createPresetButtonArray {
  
     
    public static JButton[] createJButtonArray(String directoryPath) {
        // Get a list of "pcomp and pjson " files in the specified directory
        File directory = new File(directoryPath);
     
     
        File[] recognisedFileType = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".pcomp"));

  
        if (recognisedFileType != null && recognisedFileType.length > 0) {
            JButton[] buttonArray = new JButton[recognisedFileType.length];

            for (int i = 0; i < recognisedFileType.length; i++) {
                File presentationFile = recognisedFileType[i];
                String fileName = presentationFile.getName();
                String copyOfPresetPath ="src/main/presets/userCopyDefaultFile"+"/"+fileName;
                JButton button = new JButton(fileName.replace(".pcomp", ""));
                String tmpfn = fileName.replace(".pcomp", ".png");
               
               
               
                File tempcheck =  new File (directoryPath+"/"+tmpfn);
                //Setting button icons-Gets preview image matching files name
                //Set to specific icon
                if(tempcheck.exists()){
                 
                    ImageIcon finalImageIcon = new ImageIcon(directoryPath+"/"+tmpfn);
                    button.setIcon(new ImageIcon(finalImageIcon.getImage().getScaledInstance(100, 32, 1)));
                   
                }
               // if cant find image set to default slide preview 
                else{
                   
                    ImageIcon icon = new ImageIcon("src/main/presets/themes/defaultPresetCover.png");
                    button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(100, 32, 1)));
                }
                
              
               
               
             //   File buttonAbsolutePath = new File( directoryPath+fileName);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setPreferredSize(new Dimension(72, 72));

                //Add listener to openFile,load from button path at time of creation
             
                //Launch the main program through open to absolute path of file for button selected
                buttonArray[i] = button;   
                buttonArray[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                // Launch the App window in a new thread
               
              
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            App.main(new String[]{});
                            
                           FileDeserializer.readFromPath(directoryPath+"/"+fileName);
                           
                           // FileDeserializer.readFromPath(copyOfPresetPath);

                            Thread.currentThread().interrupt();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });                         
                      
                  
            }

            return buttonArray;
        } else {
            // No .pjson/.pcomp files
            return null;
        }

                        

                        
                    
                        
                    }
   


    }


   


    // public ActionListener launchPreset(ActionEvent e){
    //     publc void actionPerformed()

    // }

   





    /**
     * 
     * @param f1 Filetype 
     * @param f2 Second filetype to display 
     * @return
     */

    // static File[] combineValidFileTypes(File[] f1,File[] f2) {
    //     // Calculate the size of the combined array
    //     if(f1 == null){
    //         return f2;
    //     }
    //     if(f2 == null){
    //         return f1;
    //     }

    //     int combinedLength = f1.length + f2.length;


    //     // Create a new array with the calculated size
    //     File[] combinedArray = new File[combinedLength];

    //     // Copy elements from the first array
    //     System.arraycopy(f1, 0, combinedArray, 0, f1.length);

    //     // Copy elements from the second array
    //     System.arraycopy(f2, 0, combinedArray, f1.length, f2.length);

    //     return combinedArray;
    // }



    //Test launching the main App.java al
