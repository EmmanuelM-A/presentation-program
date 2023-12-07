package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class ShareToolBar extends JToolBar {
    private JButton export, importFile, share, format, help;

    public ShareToolBar() {
        this.setRollover(true);

        export = new JButton(ToolBarOptions.EXPORT.getIcon());
        export.setText(ToolBarOptions.EXPORT.getTitle());
        export.setFocusable(false);
        export.setHorizontalTextPosition(SwingConstants.CENTER);
        export.setVerticalTextPosition(SwingConstants.BOTTOM);

        importFile = new JButton(ToolBarOptions.IMPORT.getIcon());
        importFile.setText(ToolBarOptions.IMPORT.getTitle());
        importFile.setFocusable(false);
        importFile.setHorizontalTextPosition(SwingConstants.CENTER);
        importFile.setVerticalTextPosition(SwingConstants.BOTTOM);

<<<<<<< HEAD
        shareBtn.setText(ToolBarOptions.SHARE.getTitle());
        shareBtn.setFocusable(false);
        shareBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        shareBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(shareBtn);

        this.setName("Share");
        this.setFloatable(false);
=======
        share = new JButton(ToolBarOptions.SHARE.getIcon());
        share.setText(ToolBarOptions.SHARE.getTitle());
        share.setFocusable(false);
        share.setHorizontalTextPosition(SwingConstants.CENTER);
        share.setVerticalTextPosition(SwingConstants.BOTTOM);

        format = new JButton(ToolBarOptions.FORMAT.getIcon());
        format.setText(ToolBarOptions.FORMAT.getTitle());
        format.setFocusable(false);
        format.setHorizontalTextPosition(SwingConstants.CENTER);
        format.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(ToolBarOptions.HELP.getIcon());
        help.setText(ToolBarOptions.HELP.getTitle());
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
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
    }
}
