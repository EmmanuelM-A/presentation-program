package com.scc210groupproject.ui.presentations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.scc210groupproject.ui.MainDisplayPanel;

public class p extends JFrame {
    private MainDisplayPanel presentationDisplay;

    public p() {
        this.setTitle("Presentation Mode");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        System.out.println(super.getSize());

        this.presentationDisplay = new MainDisplayPanel(super.getSize().width, super.getSize().height);
        presentationDisplay.setBackground(Color.BLUE);
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.weightx = 1.0;
            c.weighty = 0.3;
            this.getContentPane().add(presentationDisplay, c);
        }

        System.out.println(presentationDisplay.getWidth());

        this.setVisible(true);
    }
}
