package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * ShareToolBar
 *
 * @author madukaag
 * */
public class ShareToolBar extends ToolBar {
    private JButton export, importFile, share, format, help;

    public ShareToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        export = makeToolbarButton(GeneralButtons.EXPORT, recentsPanel);

        importFile = makeToolbarButton(GeneralButtons.IMPORT, recentsPanel);

        format = makeToolbarButton(GeneralButtons.FORMAT, recentsPanel);

        share = makeToolbarButton(GeneralButtons.SHARE, recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP,  recentsPanel);

        this.add(export);
        this.add(importFile);
        this.add(share);

        separator(this);

        this.add(format);

        separator(this);

        this.add(help);

        this.setName("Share");
        this.setFloatable(false);

    }
}
