package com.scc210groupproject.homeDisplay;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeContentPanel extends JPanel {
    private JPanel mainPanel, buttonPanel;
    private JButton presetDirectoryButton;

    public HomeContentPanel() {

        mainPanel = new JPanel();
        buttonPanel = new JPanel();
        presetDirectoryButton = new JButton("Change Theme Directory");

        this.add(mainPanel);
        this.add(buttonPanel);
        this.add(presetDirectoryButton);

        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
    }
}
