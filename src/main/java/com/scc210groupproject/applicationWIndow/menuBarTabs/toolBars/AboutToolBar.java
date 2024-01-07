package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * AboutToolBar
 *
 * @author madukaag
 * */
public class AboutToolBar extends ToolBar {
    private JButton license, shortcuts, help;
    public AboutToolBar() {
        this.setRollover(true);

        license = makeToolbarButton(GeneralButtons.LICENSE.getTitle(), GeneralButtons.LICENSE.getIcon());

        shortcuts = makeToolbarButton("Short Cuts", null);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon());

        this.add(license);
        this.addSeparator();

        this.add(shortcuts);
        this.addSeparator();

        this.add(help);
    
        this.setName("About");
        this.setFloatable(false);
    }
}
