package com.scc210groupproject.structure;

import java.awt.BorderLayout;

import javax.swing.JTextPane;

public class TextElement extends DraggableResizableElement
{
    public TextElement()
    {
        super();

        super.panel.add(new JTextPane(), BorderLayout.CENTER);
    }
}
