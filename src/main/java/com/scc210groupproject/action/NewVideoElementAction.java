package com.scc210groupproject.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.structure.VideoElement;
import com.scc210groupproject.ui.SlideManager;

public class NewVideoElementAction implements ActionListener {

    FileFilter videoFilter = new FileNameExtensionFilter("Video(mp4)", "mp4");

    @Override
    public void actionPerformed(ActionEvent discard) {
        JWindow fileWindow = new JWindow();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(videoFilter);
        chooser.addChoosableFileFilter(videoFilter);
        int result = chooser.showOpenDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = chooser.getSelectedFile().getAbsolutePath();
    
        VideoElement videoElement = new VideoElement(new File(path));
        videoElement.setSize(new Dimension(400, 400));
        SlideManager.slideManager.getCurrentSlide().add(videoElement);

    }
}
