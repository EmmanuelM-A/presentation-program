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

    private static FileFilter anyFilter = new FileNameExtensionFilter("Any Presentation File", "pcomp", "pjson");
    private static FileFilter plainFilter = new FileNameExtensionFilter("Plain Text Presentation File", "pjson");
    private static FileFilter compressedFilter = new FileNameExtensionFilter("Compressed Presentation File", "pcomp");

    public static String path = null;

    public static void save(boolean changePath) {
        
        if (path == null || changePath) {
            JWindow fileWindow = new JWindow();

            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setFileFilter(anyFilter);
            chooser.addChoosableFileFilter(anyFilter);
            chooser.addChoosableFileFilter(compressedFilter);
            chooser.addChoosableFileFilter(plainFilter);
            int result = chooser.showSaveDialog(fileWindow);

            if (result != JFileChooser.APPROVE_OPTION)
                return;

            path = chooser.getSelectedFile().getAbsolutePath();

            String[] extensions = ((FileNameExtensionFilter) chooser.getFileFilter()).getExtensions();
            boolean found = false;
            for (String extension : extensions) {
                if (path.endsWith("." + extension)) {
                    found = true;
                    break;
                }
            }
    
            if (!found)
                path += "." + extensions[0];
        }

        try {
            FileSerializer.writeToPath(path);
        } catch (IOException e) {
            return;
        }

    }

    @Override
    public void actionPerformed(ActionEvent discard) {
        save(false);
    }

}
