package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.TextElement;
import com.scc210groupproject.testui.MainDisplayPanel;

public class NewTextElementAction implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent _)
    {
        TextElement textElement = new TextElement();

        MainDisplayPanel.instance.getDisplayedSlide().add(textElement);
    }
    
}
