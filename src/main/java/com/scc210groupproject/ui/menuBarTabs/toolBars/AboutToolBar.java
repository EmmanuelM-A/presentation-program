package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * AboutToolBar
 *
 * @author madukaag
 * */
public class AboutToolBar extends ToolBar {
    /**
     * The buttons for this toolbar
     */
    private final JButton license, shortcuts, help;

    /**
     * The constructor for the AboutToolBar - Creates the file toolbar and adds all of its buttons to its toolbar
     * @param recentsPanel The recents panel on the home tab
     */
    public AboutToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        license = makeToolbarButton(GeneralButtons.LICENSE, recentsPanel);

        shortcuts = makeToolbarButton(GeneralButtons.SHORTCUTS, recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP, recentsPanel);

        this.add(license);

        separator(this);

        this.add(shortcuts);

        separator(this);

        this.add(help);
    
        this.setName("About");
        this.setFloatable(false);
    }

    /**
     * Gets the licence button from the about toolbar
     * @return JButton
     */
    public JButton getLicense() {
        return this.license;
    }

    /**
     * Gets the shorcuts button from the about toolbar
     * @return JButton
     */
    public JButton getShortcuts() {
        return this.shortcuts;
    }

    /**
     * Gets the help button from the about toolbar
     * @return JButton
     */
    public JButton getHelp() {
        return help;
    }
}
