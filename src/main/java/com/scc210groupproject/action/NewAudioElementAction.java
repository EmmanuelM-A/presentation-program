package com.scc210groupproject.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.structure.AudioElement;
import com.scc210groupproject.ui.SlideManager;

public class NewAudioElementAction implements ActionListener {

    FileFilter audioFilter = new FileNameExtensionFilter("Audio(wav)", "wav");

    @Override
    public void actionPerformed(ActionEvent discard) {
        JWindow fileWindow = new JWindow();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(audioFilter);
        chooser.addChoosableFileFilter(audioFilter);
        int result = chooser.showOpenDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = chooser.getSelectedFile().getAbsolutePath();
    
        AudioElement audioElement = new AudioElement(new File(path));
        audioElement.setSize(new Dimension(100, 100));
        SlideManager.slideManager.getCurrentSlide().add(audioElement);

    }
}
