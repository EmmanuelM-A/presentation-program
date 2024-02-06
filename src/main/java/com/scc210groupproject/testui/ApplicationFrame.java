package com.scc210groupproject.testui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ApplicationFrame extends JFrame
{
    public static ApplicationFrame instance;

    //public static void main(String[] args) { new ApplicationFrame(); }

    public ApplicationFrame()
    {
        super();
        super.setMinimumSize(new Dimension(960, 840));
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        super.setLayout(new GridBagLayout());


        ToolbarPane toolbarPane = new ToolbarPane();
        {
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.PAGE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            super.add(toolbarPane, c);
        }

        JPanel sidePanel = new JPanel();
        {
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 0.3;
            c.weighty = 1.0;
            super.add(sidePanel, c);
        }

        MainDisplayPanel mainPanel = new MainDisplayPanel();
        {
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.CENTER;
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 0.7;
            c.weighty = 1.0;
            super.add(mainPanel, c);
        }

        SelectorPane scrollPane = new SelectorPane(); 
        {
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.PAGE_END;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 2;
            super.add(scrollPane, c);
        }

        super.validate();
        super.setVisible(true);

        instance = this;
    }
}
