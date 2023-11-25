package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class ShareToolBar extends JToolBar {
    private final JButton exportBtn, importBtn, shareBtn;

    public ShareToolBar() {
        exportBtn = new JButton(ToolBarOptions.EXPORT.getIcon());
        importBtn = new JButton(ToolBarOptions.IMPORT.getIcon());
        shareBtn = new JButton(ToolBarOptions.SHARE.getIcon());

        this.setRollover(true);

        exportBtn.setText(ToolBarOptions.EXPORT.getTitle());
        exportBtn.setFocusable(false);
        exportBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        exportBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(exportBtn);

        importBtn.setText(ToolBarOptions.IMPORT.getTitle());
        importBtn.setFocusable(false);
        importBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        importBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(importBtn);

        shareBtn.setText(ToolBarOptions.SHARE.getTitle());
        shareBtn.setFocusable(false);
        shareBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        shareBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(shareBtn);
    }
}
