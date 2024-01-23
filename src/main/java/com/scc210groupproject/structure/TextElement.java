package com.scc210groupproject.structure;

import javax.swing.JTextPane;

public class TextElement extends DraggableResizableElement
{
    public TextElement()
    {
        super();

        super.panel.add(new JTextPane());
    }
}
