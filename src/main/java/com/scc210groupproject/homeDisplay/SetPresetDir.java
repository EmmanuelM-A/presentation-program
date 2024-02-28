package com.scc210groupproject.homeDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JWindow;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

import com.scc210groupproject.readwrite.FileDeserializer;

public class SetPresetDir implements ActionListener {

    FileFilter compressedFilter = new FileNameExtensionFilter("Compressed Presentation File", ".pcomp");

    @Override
    public void actionPerformed(ActionEvent _e) {
        JWindow fileWindow = new JWindow();

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //set valid files to be viewable
        chooser.setFileFilter(compressedFilter);
        int result = chooser.showSaveDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = chooser.getSelectedFile().getAbsolutePath();

        try {
            FileDeserializer.readFromPath(path);
        } catch (IOException | ClassNotFoundException e) {
            return;
        }
    }

}
