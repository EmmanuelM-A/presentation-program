package com.scc210groupproject.ui;

import javax.swing.JScrollPane;
import java.awt.Dimension;

public class SelectorPane extends JScrollPane
{
    public static SelectorPane instance;

    public SelectorPane()
    {
        super(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        super.setPreferredSize(new Dimension(0, 150));

        instance = this;
    }
}
