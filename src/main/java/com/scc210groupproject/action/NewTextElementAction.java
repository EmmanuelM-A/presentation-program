package com.scc210groupproject.action;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.TextElement;
import com.scc210groupproject.testui.MainDisplayPanel;
import com.scc210groupproject.ui.SlideManager;

public class NewTextElementAction implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent discard
    )
    {
        TextElement textElement = new TextElement();

        // These three lines are for testing purposes

        textElement.setLocation(new Point(0, 0));
        textElement.setSize(new Dimension(400,400));
        textElement.setText("Insert Text Here.");


        SlideManager.slideManager.getCurrentSlide().add(textElement);
        //MainDisplayPanel.instance.getDisplayedSlide().add(textElement);
    }
    
}
