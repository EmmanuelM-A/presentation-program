package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.scc210groupproject.ui.helper.GeneralButtons;

public class TextElement extends DraggableResizableElement
{
    JTextPane jTextPane;

    public TextElement()
    {
        jTextPane = new JTextPane();
        //jTextPane.setSize(panel.getWidth(), panel.getHeight());

        super.panel.add(jTextPane, BorderLayout.CENTER);
    }

    class ResizeListener extends ComponentAdapter
    {
        public void componentResized(ComponentEvent e)
        {
            System.out.println(jTextPane.getText());
        }
    }
}
