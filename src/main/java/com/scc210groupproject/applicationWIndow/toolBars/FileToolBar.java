package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class FileToolBar extends JToolBar {
    private Boolean isDisplayed = true;
    private final JButton newBtn, openBtn, saveBtn;

    public FileToolBar() {
        newBtn = new JButton(ToolBarOptions.NEW.getIcon());
        openBtn = new JButton(ToolBarOptions.OPEN.getIcon());
        saveBtn = new JButton(ToolBarOptions.SAVE.getIcon());

        this.add(newBtn);
        this.add(openBtn);
        this.add(saveBtn);
    }

    public Boolean getIsDisplayed() {
        return this.isDisplayed;
    }
    public void setIsDisplayed(Boolean displayed) {
        this.isDisplayed = displayed;
    }
}
