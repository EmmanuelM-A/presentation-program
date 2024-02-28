package com.scc210groupproject.homeDisplay.homeAction;

import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.homeDisplay.checkForPreset;
import com.scc210groupproject.homeDisplay.createPresetButtonArray;
import com.scc210groupproject.homeDisplay.noFilesPopupWarning;

public class changePresetDirectory  {

    private String presetDirectory ="";
  //  private JPanel contentPanel;
    private JButton[] templateButtons;
    public  void changeDirectory(JPanel contentPanel) {
        FileFilter pcompFilter = new FileNameExtensionFilter("PCOMP", ".pcomp");

    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(pcompFilter);        
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    


        /////////
        //check powerpoint files are present in directory
        
        /////////
        //if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
           
            presetDirectory = selectedFolder.getAbsolutePath();
            File checkDir = new File(presetDirectory);
           
            //updateContent(selectedFolder);
            //Only call if directoty has valid files else do not redraw panel
            if (checkForPreset.hasValidFiles(checkDir)){
                contentPanel.removeAll();
                templateButtons = createPresetButtonArray.createJButtonArray(presetDirectory);
                contentPanel.setLayout(new GridLayout(templateButtons.length/2,5));

                
            for(JButton button:templateButtons){ contentPanel.add(button);}
    
               
            //repaint
            contentPanel.revalidate();
            contentPanel.repaint();
            

            }
            else{
     
                noFilesPopupWarning.noFilesPopup("No Valid Files Found.\nReturning to DefaultContent Directory", "No presentation files detected");
            }
            
        }
        //Return to showing default content directory if no files in new directory 


    
    

   


   

}
