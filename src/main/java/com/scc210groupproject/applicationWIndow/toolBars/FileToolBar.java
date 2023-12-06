package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class FileToolBar extends JToolBar {
    private final JButton newBtn, openBtn, saveBtn;

    public FileToolBar() {
        newBtn = new JButton(ToolBarOptions.NEW.getIcon());
        openBtn = new JButton(ToolBarOptions.OPEN.getIcon());
        saveBtn = new JButton(ToolBarOptions.SAVE.getIcon());

        this.setRollover(true);

        newBtn.setText("NEW");
        newBtn.setFocusable(false);
        newBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        newBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(newBtn);

        openBtn.setText("OPEN");
        openBtn.setFocusable(false);
        openBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        openBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(openBtn);

        saveBtn.setText("SAVE");
        saveBtn.setFocusable(false);
        saveBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        saveBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(saveBtn);

        this.setName("File");
        this.setFloatable(false);
    }
}
