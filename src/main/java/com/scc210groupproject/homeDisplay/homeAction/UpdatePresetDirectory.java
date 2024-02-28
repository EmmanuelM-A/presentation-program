package com.scc210groupproject.homeDisplay.homeAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JWindow;

import com.scc210groupproject.readwrite.FileDeserializer;

/**
 * Allow user to set folder opresets should be displayed from 
 */
public class UpdatePresetDirectory  implements ActionListener{

@Override
    public void actionPerformed(ActionEvent b) {
        JWindow fileWindow = new JWindow();

        JFileChooser presetDirChooser = new JFileChooser();
        
        
        presetDirChooser.setFileSelectionMode(JFileChooser. DIRECTORIES_ONLY);
        int result = presetDirChooser.showSaveDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = presetDirChooser.getSelectedFile().getAbsolutePath();

        try {
            FileDeserializer.readFromPath(path);
        } catch (IOException | ClassNotFoundException e) {
            return;
        }
    }{
    }
}
