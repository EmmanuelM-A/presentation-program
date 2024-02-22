package com.scc210groupproject.ui.presentations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.scc210groupproject.ui.MainDisplayPanel;

public class PresentationMode extends JFrame {
    private MainDisplayPanel presentationDisplay;

    public PresentationMode(int startIndex) {
        this.setTitle("Presentation Mode");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        this.presentationDisplay = new MainDisplayPanel(super.getSize().width, super.getSize().height);
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

        PresentationManager2 presentationManager2 = new PresentationManager2(this, this.presentationDisplay, startIndex);

        this.setVisible(true);
    }
}
