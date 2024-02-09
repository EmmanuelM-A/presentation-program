package com.scc210groupproject.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Settings extends JFrame {

    /**
     * By default the colour scheme for the program is set to light mode
     */
    private boolean isLightMode = true;

    /**
     * New slides added by defualt should be displyed
     */
    private boolean displayNewSlide = true;

    /**
     * Accessibility Constrols- Read text on hover, Highlight text on hover, Contrasting colours
     */

    /**
     * Determines if text should be read on hover
     */
    private boolean readTextOnHover = false;

    /**
     * Determines if text should be highlighted on hover
     */
    private boolean highlighTextOnHover = false;

    /**
     * Determines if the current colour scheme should consist of contrasting colours
     */
    private boolean contrastingColours = false;

    /**
     * Determines if changes made will actually be applied to the program
     */
    private boolean applyChanges = false;

    /**
     * A public instance of this class
     */
    public static Settings instance;
    

    public Settings() {
        instance = this;
    }

    /**
     * Creates the settings window frame
     */
    public void createSettingsWindow() {
        this.setTitle("Settings");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);

        this.add(createSettingsOptions());

        this.setVisible(true);
    }

    /**
     * Creates the settings window jpanel along with all its required buttons
     * @return JPanel
     */
    public JPanel createSettingsOptions() {
        // Settings JPanel
        JPanel settingsWindowJPanel = new JPanel();
        settingsWindowJPanel.setBackground(Color.WHITE);
        settingsWindowJPanel.setLayout(null);

        // Settings options
        JCheckBox toggleLightMode = new JCheckBox("<html>Toggle light mode</html>");
        toggleLightMode.setBounds(50, 50, 200, 40);
        settingsWindowJPanel.add(toggleLightMode); 


        JCheckBox displayNewSlides = new JCheckBox("Display new slides added");
        displayNewSlides.setBounds(50, 90, 200, 40);
        settingsWindowJPanel.add(displayNewSlides);

        JLabel accessibility = new JLabel("Accessibility");
        settingsWindowJPanel.add(accessibility);

        JLabel readText = new JLabel("Read text on hover");

        JLabel highlightText = new JLabel("Highlight text on hover");

        JLabel contrastingColours = new JLabel("Set the colour scheme to contrasting colours");

        JLabel defaultSettings = new JLabel("Default Settings");


        return settingsWindowJPanel;
    }

    //////////// GETTERS ////////////

    public boolean getIsLightMode() {
        return this.isLightMode;
    }

    public boolean getDisplayNewSlides() {
        return this.displayNewSlide;
    }

    public boolean getReadTextOnHover() {
        return this.readTextOnHover;
    }

    public boolean getHighlightTextOnHover() {
        return this.highlighTextOnHover;
    }

    public boolean getContrastingColours() {
        return this.contrastingColours;
    }

    public boolean getApplyChanges() {
        return this.applyChanges;
    }

    //////////// SETTERS ////////////

    public void setIsLightMode(boolean truthy) {
        this.isLightMode = truthy;
    }

    public void setDisplayNewSlides(boolean truthy) {
        this.displayNewSlide = truthy;
    }

    public void setReadTextOnHover(boolean truthy) {
        this.readTextOnHover = truthy;
    }

    public void setHighlightTextOnHover(boolean truthy) {
        this.highlighTextOnHover = truthy;
    }

    public void setContrastingColours(boolean truthy) {
        this.contrastingColours = truthy;
    }

    public void setApplyChanges(boolean truthy) {
        this.applyChanges = truthy;
    }

    //////////// METHODS ////////////


}