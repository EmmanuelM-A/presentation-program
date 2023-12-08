package com.scc210groupproject.ui;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.scc210groupproject.ui.toolBars.*;

import java.awt.Component;
import java.awt.Dimension;

public class ToolbarPane extends JTabbedPane
{
    public static ToolbarPane instance;

    public ToolbarPane()
    {
        super();
        super.setPreferredSize(new Dimension(0, 150));

        super.add(new FileToolBar());
        super.add(new HomeToolBar());
        super.add(new InsertToolBar());
        super.add(new ViewToolBar());
        super.add(new ShareToolBar());
        super.add(new AboutToolBar());

        for (int i = 0; i < super.getTabCount(); i++)
        {
            Component original = super.getComponentAt(i);

            JLabel largeLabel = new JLabel();
            largeLabel.setText(original.getName());

            Dimension d = new Dimension(50, 30);
            largeLabel.setSize(d);
            largeLabel.setPreferredSize(d);
            largeLabel.setMinimumSize(d);
            largeLabel.setMaximumSize(d);

            super.setTabComponentAt(i, largeLabel);
        }

        super.validate();

        instance = this;
    }
}
