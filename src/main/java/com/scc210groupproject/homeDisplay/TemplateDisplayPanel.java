package com.scc210groupproject.homeDisplay;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JPanel;

public class TemplateDisplayPanel extends HomeContentPanel{
    
    private String presetDirectory = "resources/presets/";
    private JButton[] templateButtons; 
    private JButton setThemesDir;

    private JPanel  templateButtonPanel,mainPanel;
    public TemplateDisplayPanel(){
        templateButtonPanel = new JPanel();
        mainPanel = new JPanel();
        this.add(mainPanel,BorderLayout.SOUTH);
        
        templateButtons = CreatePresetButtonArray.createJButtonArray(presetDirectory);

        setThemesDir = new JButton("Change Template Directory ");
        setThemesDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeThemeDir(mainPanel);
            }
        });
        
        templateButtonPanel.add(setThemesDir);

        this.add(templateButtonPanel,BorderLayout.NORTH);

    }

    /**
     * @param contentPanel Panel to update 
     */
    private void changeThemeDir(JPanel contentPanel) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        /////////
        //check powerpoint files are present in directory
        /////////

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
           
            presetDirectory = selectedFolder.getAbsolutePath();
            File checkDir = new File(presetDirectory);

            //Only call if directoty has valid files else do not redraw panel
            if(CheckForPreset.hasValidFiles(checkDir)) {
                contentPanel.removeAll();
                templateButtons = CreatePresetButtonArray.createJButtonArray(presetDirectory);
                contentPanel.setLayout(new GridLayout(templateButtons.length/2,5));
                for(JButton button:templateButtons){ contentPanel.add(button);}
    
                //repaint
                contentPanel.revalidate();
                contentPanel.repaint();
                this.setVisible(true);

            } else{
                NoFilesPopupWarning.noFilesPopup("Unable to find presets in selected directory", "No Presets Present");
            }
            
        }
        //Return to showing default content directory if no files in new directory 
    }    
}
