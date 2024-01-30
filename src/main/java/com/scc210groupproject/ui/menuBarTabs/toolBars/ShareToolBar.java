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

        export = makeToolbarButton(GeneralButtons.EXPORT.getTitle(), GeneralButtons.EXPORT.getIcon(), null, recentsPanel);

        importFile = makeToolbarButton(GeneralButtons.IMPORT.getTitle(), GeneralButtons.IMPORT.getIcon(), null, recentsPanel);

        format = makeToolbarButton(GeneralButtons.FORMAT.getTitle(), GeneralButtons.FORMAT.getIcon(), null, recentsPanel);

        share = makeToolbarButton(GeneralButtons.SHARE.getTitle(), GeneralButtons.SHARE.getIcon(), null, recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), null,  recentsPanel);

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
