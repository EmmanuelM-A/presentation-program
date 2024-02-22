package com.scc210groupproject.ui.presentations;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class PresentationModeTwo extends JFrame {

    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public PresentationModeTwo(int startIndex) {
        this.setTitle("Presentation Mode");
        this.setSize((int)size.getWidth(), (int)size.getHeight());
        //this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel presentationDisplay = new JPanel();
        presentationDisplay.setSize(new Dimension(super.getWidth(), super.getHeight()));
        presentationDisplay.setLayout(new BorderLayout());

        this.getContentPane().add(presentationDisplay);

        PresentationManager presentationManager = new PresentationManager(this, presentationDisplay, startIndex);

        setVisible(true);
    }

}
