package com.scc210groupproject.ui;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Settings extends JFrame {

    /**
     * By default the colour scheme for the program is set to light mode
     */
    private boolean isLightMode = true;

    public static Settings instance;
    
    public Settings() {
        instance = this;
    }

    public void createSettingsWindow() {
        this.setTitle("Settings");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(211, 211, 211));

        this.setVisible(true);
    }

    public void createSettingsOptions() {
        /*
         * Settings Options:
         * - dark mode
         * - light mode
         * - slides manuplation:
         *      - display new slides added
         *      - 
         * - 
         */
    }

    public boolean getIsLightMode() {
        return this.isLightMode;
    }
}