package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class HomeToolBar extends JToolBar {
    private Boolean isDisplayed = true;
    private final JButton btn1, btn2, btn3;

    public HomeToolBar() {
        btn1 = new JButton("BUTTON 1");
        btn2 = new JButton("BUTTON 2");
        btn3 = new JButton("BUTTON 3");

        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
    }

    public Boolean getIsDisplayed() {
        return this.isDisplayed;
    }
    public void setIsDisplayed(Boolean displayed) {
        this.isDisplayed = displayed;
    }
}
