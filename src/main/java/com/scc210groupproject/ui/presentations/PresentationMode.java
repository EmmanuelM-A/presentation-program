package com.scc210groupproject.ui.presentations;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class PresentationMode extends JFrame {
    private PresentationDisplayPanel presentationDisplay;

    private int startIndex;

    private JFrame frame;

    public static PresentationMode instance;

    public PresentationMode() {
        //this.presentationDisplay = new PresentationDisplayPanel();
        frame = this;
        instance = this;
    }

    public void createAndShowUI(PresentationDisplayPanel presentationDisplay) {
        this.setTitle("Presentation Mode");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        //this.startIndex = startIndex;

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

        this.setVisible(true);
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public JFrame getFrame() {
        return frame;
    }

    public PresentationDisplayPanel getPresentationDisplay() {
        return this.presentationDisplay;
    }

    public void setPresentationDisplay(PresentationDisplayPanel setPresentatioDisplay) {
        this.presentationDisplay = setPresentatioDisplay;
    }

    /*public void setStartIndex(int newStartIndex) {
        this.startIndex = newStartIndex;
    }*/
}
