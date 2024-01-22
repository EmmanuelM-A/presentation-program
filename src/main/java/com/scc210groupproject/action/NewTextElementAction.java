package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;

import com.scc210groupproject.structure.DraggableResizableElement;
import com.scc210groupproject.testui.MainDisplayPanel;

public class NewTextElementAction implements ActionListener{

    @Override
    
    public void actionPerformed(ActionEvent _)
    {
        DraggableResizableElement textElement = new DraggableResizableElement(new JTextPane());

        MainDisplayPanel.instance.getDisplayedSlide().add(textElement);

    }
    
}
