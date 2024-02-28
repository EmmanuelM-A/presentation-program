package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.readwrite.FileSerializer;

public class SaveAction implements ActionListener {

    FileFilter compressedFilter = new FileNameExtensionFilter("Compressed Presentation File", ".pcomp");

    String path = null;

    @Override
    public void actionPerformed(ActionEvent discard
    ) {

        if (path == null) {
            JWindow fileWindow = new JWindow();

            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setFileFilter(compressedFilter);
            chooser.addChoosableFileFilter(compressedFilter);
            int result = chooser.showSaveDialog(fileWindow);

            if (result != JFileChooser.APPROVE_OPTION)
                return;

            path = chooser.getSelectedFile().getAbsolutePath();
            String extension = ((FileNameExtensionFilter) chooser.getFileFilter()).getExtensions()[0];

            if (!path.endsWith(extension))
                path += extension;

        }

        try {
            FileSerializer.writeToPath(path);
        } catch (IOException e) {
            return;
        }
    }

}
