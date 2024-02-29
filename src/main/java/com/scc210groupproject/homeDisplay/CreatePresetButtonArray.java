package com.scc210groupproject.homeDisplay;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.scc210groupproject.App;
import com.scc210groupproject.PresentationApp;
import com.scc210groupproject.readwrite.FileDeserializer;
import com.scc210groupproject.structure.Presentation;

/**Allow  setting the filetypes that will be displayed on a homeContentPanel */
/**
 * @author @leewelto
 */
public class CreatePresetButtonArray {
  
    public static JButton[] createJButtonArray(String directoryPath) {
        // Get a list of "pcomp and pjson " files in the specified directory
        File directory;
        try {
            directory = new File(App.class.getResource(directoryPath).getFile());

        } catch (Exception e) {
            try {
                directory = new File(directoryPath);
            } catch (Exception ex) {
                throw ex;
            }
        }

        File[] recognisedFileType = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".pcomp"));

        if (recognisedFileType != null && recognisedFileType.length > 0) {
            JButton[] buttonArray = new JButton[recognisedFileType.length];

            for (int i = 0; i < recognisedFileType.length; i++) {
                File presentationFile = recognisedFileType[i];
                String fileName = presentationFile.getName();
                JButton button = new JButton(fileName.replace(".pcomp", ""));
                String tmpfn = fileName.replace(".pcomp", ".png");
               
                URL tmpurl = App.class.getResource(directoryPath+"/"+tmpfn);
                //Setting button icons-Gets preview image matching files name
                //Set to specific icon
                
                if(tmpurl != null){
                 
                    ImageIcon finalImageIcon = new ImageIcon(tmpurl);
                    button.setIcon(new ImageIcon(finalImageIcon.getImage().getScaledInstance(100, 32, 1)));
                   
                }
               // if cant find image set to default slide preview c
                else{
                   
                    ImageIcon icon = new ImageIcon(App.class.getResource("/presets/themes/defaultPresetCover.png"));
                    button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(100, 32, 1)));
                }
                
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setSize(new Dimension(72, 72));

                //Add listener to openFile,load from button path at time of creation
             
                //Launch the main program through open to absolute path of file for button selected
                buttonArray[i] = button;   
                buttonArray[i].addActionListener(new ActionListener() {

                    private boolean clicked = false;
                    public void actionPerformed(ActionEvent e) {
                        if (clicked)
                            return;

                        clicked = true;
                // Launch the App window in a new thread
               
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PresentationApp.main(new String[]{});
                                    
                                    String path = App.class.getResource(directoryPath+"/"+fileName).getFile();
                                    Presentation result;
                                    try (FileInputStream fileStream = new FileInputStream(path)) {
                                        if (path.endsWith(".pcomp"))
                                            try (GZIPInputStream compressedStream = new GZIPInputStream(fileStream)) {
                                                result = FileDeserializer.deserialize(compressedStream);
                                            }
                                        else
                                            result = FileDeserializer.deserialize(fileStream);
                                    }

                                    SwingUtilities.invokeAndWait(new Runnable() {

                                        @Override
                                        public void run() {
                                            Presentation.set(result);
                                        }
                                        
                                    });
                                    
                            
                                    // Thread.currentThread().interrupt();

                                    HomeDisplay.instance.dispose();
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
