package com.scc210groupproject.action;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JWindow;

import com.scc210groupproject.structure.DraggableResizableElement;
import com.scc210groupproject.structure.ImageElement;
import com.scc210groupproject.testui.MainDisplayPanel;

public class NewImageElementAction implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent _)
    {
        JWindow fileWindow = new JWindow();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        String path = fileChooser.getSelectedFile().getAbsolutePath();

        ImageElement imageElement = new ImageElement(new ImageIcon(path));

        MainDisplayPanel.instance.getDisplayedSlide().add(imageElement);
    }
    
}
