package com.scc210groupproject.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.structure.ImageElement;
import com.scc210groupproject.ui.SlideManager;

public class NewImageElementAction implements ActionListener {

    FileFilter imageFilter = new FileNameExtensionFilter("Image(jpeg, jpg, png, bmp, webm)", "jpeg", "jpg", "png", "bmp", "webm");

    @Override
    public void actionPerformed(ActionEvent discard)
    {
        JWindow fileWindow = new JWindow();
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(imageFilter);
        chooser.addChoosableFileFilter(imageFilter);
        int result = chooser.showOpenDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = chooser.getSelectedFile().getAbsolutePath();

        ImageElement imageElement = new ImageElement(new File(path));
        imageElement.setSize(new Dimension(400, 400));
        SlideManager.slideManager.getCurrentSlide().add(imageElement);

    }
    
}
