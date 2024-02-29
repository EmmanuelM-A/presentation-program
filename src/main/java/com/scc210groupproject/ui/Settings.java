package com.scc210groupproject.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
// import javax.swing.JLabel;
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
     * Determines the max number of buttons that  cna be stored in the recents section
     */
    private int maxRecentsCount = 10;

    /**
     * Accessibility Controls- Read text on hover, Highlight text on hover, Contrasting colours
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
        JCheckBox toggleLightMode = makeCheckBox("Toggle Dark Mode", 30, 30, 200, 40, toggleLightModeAction);
        settingsWindowJPanel.add(toggleLightMode); 

        JCheckBox displayNewSlides = makeCheckBox("Should new slides added be displayed?", 30, 70, 300, 40, displayNewSlideAction);
        displayNewSlides.setSelected(true);
        settingsWindowJPanel.add(displayNewSlides);

        // JLabel accessibility = new JLabel("Accessibility");
        // settingsWindowJPanel.add(accessibility);

        // JLabel readText = new JLabel("Read text on hover");

        // JLabel highlightText = new JLabel("Highlight text on hover");

        // JLabel contrastingColours = new JLabel("Set the colour scheme to contrasting colours");

        // JLabel defaultSettings = new JLabel("Default Settings");


        return settingsWindowJPanel;
    }

    //////////// GETTERS ////////////

    public boolean getIsLightMode() {
        return this.isLightMode;
    }

    public boolean getDisplayNewSlides() {
        return this.displayNewSlide;
    }

    public int getMaxRecentsCount() {
        return this.maxRecentsCount;
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

    public void setMaxRecentsCount(int newMax) {
        this.maxRecentsCount = newMax;
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

    public JCheckBox makeCheckBox(String title, int x, int y, int width, int height, AbstractAction action) {
        JCheckBox cb = new JCheckBox("<html>" + title + "</html>");
        cb.setBounds(x, y, width, height);
        cb.setFocusPainted(false);
        cb.setFont(new Font("Cambria", Font.BOLD, 13));
        cb.setBackground(Color.WHITE);
        cb.addActionListener(action);

        return cb;
    }

    public void toggleDisplayNewSlides() {
        if(SlideManager.slideManager.getDisplayNewSlides())
            SlideManager.slideManager.setDisplayNewSlides(false);
        else
            SlideManager.slideManager.setDisplayNewSlides(true);
    }

    public void changeMaxRecentsCount() {

    }

    AbstractAction toggleLightModeAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox cb = (JCheckBox) e.getSource();
            
            if(cb.isSelected()) {

                System.out.println("Dark Mode Activated!");
            } else {

                System.out.println("Light Mode Activated!");
            }
        }
    };

    AbstractAction displayNewSlideAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox cb = (JCheckBox) e.getSource();

            if(cb.isSelected() == false) {
                toggleDisplayNewSlides();
                System.out.println("New slides added will not be displayd!");
            } else {
                toggleDisplayNewSlides();
                System.out.println("New slides added will be displayed!");
            }
        }
    };
}