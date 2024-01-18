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

    FileFilter plainFilter = new FileNameExtensionFilter("Debug Presentation File", ".pjson");
    FileFilter comrpessedFilter = new FileNameExtensionFilter("Compressed Presentation File", ".pcomp");

    @Override
    public void actionPerformed(ActionEvent _) {
        JWindow fileWindow = new JWindow();

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.addChoosableFileFilter(comrpessedFilter);
        chooser.addChoosableFileFilter(comrpessedFilter);
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
