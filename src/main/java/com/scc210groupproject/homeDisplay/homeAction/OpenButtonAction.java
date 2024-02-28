package com.scc210groupproject.homeDisplay.homeAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.PresentationApp;
import com.scc210groupproject.readwrite.FileDeserializer;

public class OpenButtonAction implements ActionListener {

    FileFilter compressedFilter = new FileNameExtensionFilter("Compressed Presentation File", ".pcomp");

    @Override
    public void actionPerformed(ActionEvent b) {
        JWindow fileWindow = new JWindow();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(compressedFilter);
        chooser.addChoosableFileFilter(compressedFilter);
        int result = chooser.showSaveDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = chooser.getSelectedFile().getAbsolutePath();

        //running
        try {
            //launch main
            RunInMain.runPresetFile(PresentationApp.class.getResource("presets/templates/darkpreset.pcomp").toString());
            //run this on it 
            //read path
            FileDeserializer.readFromPath((path));
        } catch (IOException | ClassNotFoundException e) {
            return;
        }
    }
}


