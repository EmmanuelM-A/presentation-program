package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * ShareToolBar
 *
 * @author madukaag
 * */
public class ShareToolBar extends JToolBar {
    private JButton export, importFile, share, format, help;

    public ShareToolBar() {
        this.setRollover(true);

        export = new JButton(GeneralButtons.EXPORT.getIcon());
        export.setText(GeneralButtons.EXPORT.getTitle());
        export.setFocusable(false);
        export.setHorizontalTextPosition(SwingConstants.CENTER);
        export.setVerticalTextPosition(SwingConstants.BOTTOM);

        importFile = new JButton(GeneralButtons.IMPORT.getIcon());
        importFile.setText(GeneralButtons.IMPORT.getTitle());
        importFile.setFocusable(false);
        importFile.setHorizontalTextPosition(SwingConstants.CENTER);
        importFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        share = new JButton(GeneralButtons.SHARE.getIcon());
        share.setText(GeneralButtons.SHARE.getTitle());
        share = new JButton(GeneralButtons.SHARE.getIcon());
        share.setText(GeneralButtons.SHARE.getTitle());
        share.setFocusable(false);
        share.setHorizontalTextPosition(SwingConstants.CENTER);
        share.setVerticalTextPosition(SwingConstants.BOTTOM);

        format = new JButton(GeneralButtons.FORMAT.getIcon());
        format.setText(GeneralButtons.FORMAT.getTitle());
        format.setFocusable(false);
        format.setHorizontalTextPosition(SwingConstants.CENTER);
        format.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(GeneralButtons.HELP.getIcon());
        help.setText(GeneralButtons.HELP.getTitle());
        help.setFocusable(false);
        help.setHorizontalTextPosition(SwingConstants.CENTER);
        help.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(export);
        this.add(importFile);
        this.add(share);
        this.addSeparator();

        this.add(format);
        this.addSeparator();

        this.add(help);

        this.setName("Share");
        this.setFloatable(false);

    }
}
