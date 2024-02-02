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
    private JButton license, shortcuts, help;
    public AboutToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        license = makeToolbarButton(GeneralButtons.LICENSE.getTitle(), GeneralButtons.LICENSE.getIcon(), GeneralButtons.LICENSE.getEvent(), recentsPanel);

        shortcuts = makeToolbarButton(GeneralButtons.SHORTCUTS.getTitle(), GeneralButtons.SHORTCUTS.getIcon(), GeneralButtons.SHORTCUTS.getEvent(), recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), GeneralButtons.HELP.getEvent(), recentsPanel);

        this.add(license);

        separator(this);

        this.add(shortcuts);

        separator(this);

        this.add(help);
    
        this.setName("About");
        this.setFloatable(false);
    }
}
