package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JWindow;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.readwrite.FileDeserializer;

public class OpenAction implements ActionListener {

    private static FileFilter anyFilter = new FileNameExtensionFilter("Any Presentation File (.pcomp, .pjson)", "pcomp", "pjson");

    @Override
    public void actionPerformed(ActionEvent discard) {
        JWindow fileWindow = new JWindow();

        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(anyFilter);
        chooser.addChoosableFileFilter(anyFilter);
        int result = chooser.showOpenDialog(fileWindow);

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
