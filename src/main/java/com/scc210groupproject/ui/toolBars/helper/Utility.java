package com.scc210groupproject.ui.toolBars.helper;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Utility
{
    public static JButton createSingleWidthButton(ResourceReference reference, ActionListener clickHandler)
    {
        JButton b = new JButton(reference.multiLineTitle, reference.icon);

        Dimension d = new Dimension(80, 100);
        b.setSize(d);
        b.setPreferredSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);

        b.setFocusable(false);
        b.setHorizontalTextPosition(SwingConstants.CENTER);
        b.setVerticalTextPosition(SwingConstants.BOTTOM);

        b.addActionListener(clickHandler);

        return b;
    }
}
