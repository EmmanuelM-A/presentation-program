package com.scc210groupproject.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JWindow;

import com.scc210groupproject.structure.VideoElement;
import com.scc210groupproject.ui.SlideManager;

public class NewVideoElementAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent discard) {
        JWindow fileWindow = new JWindow();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = fileChooser.getSelectedFile().getAbsolutePath();
    
        VideoElement VideoElement = new VideoElement(new File(path), 400, 400);
        VideoElement.setSize(new Dimension(400, 400));
        SlideManager.slideManager.getCurrentSlide().add(VideoElement);

    }
}
