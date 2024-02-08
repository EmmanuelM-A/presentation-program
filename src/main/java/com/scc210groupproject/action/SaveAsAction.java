package com.scc210groupproject.action;

import com.scc210groupproject.readwrite.FileSerializer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAsAction implements ActionListener{
    
    FileFilter plainFilter = new FileNameExtensionFilter("Debug Presentation File", ".pjson");
    FileFilter compressedFilter = new FileNameExtensionFilter("Compressed Presentation File", ".pcomp");

    @Override
    public void actionPerformed(ActionEvent discard) {

        JWindow fileWindow = new JWindow();

        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(compressedFilter);
        chooser.addChoosableFileFilter(plainFilter);
        chooser.addChoosableFileFilter(compressedFilter);
        int result = chooser.showSaveDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = chooser.getSelectedFile().getAbsolutePath();
        String extension = ((FileNameExtensionFilter) chooser.getFileFilter()).getExtensions()[0];

        if (!path.endsWith(extension))
            path += extension;



        try {
            FileSerializer.writeToPath(path);
        } catch (IOException e) {
            return;
        }
    }

}
