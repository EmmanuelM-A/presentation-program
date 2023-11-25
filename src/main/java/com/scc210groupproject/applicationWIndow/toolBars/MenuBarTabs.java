package com.scc210groupproject.applicationWIndow.toolBars;

import javax.swing.*;

public class MenuBarTabs extends JTabbedPane {
    private FileToolBar fileToolBar;

    private HomeToolBar homeToolBar;
    private InsertToolBar insertToolBar;
    private ViewToolBar viewToolBar;
    private ShareToolBar shareToolBar;
    private AboutToolBar aboutToolBar;

    public MenuBarTabs() {
        this.fileToolBar = new FileToolBar();
        this.homeToolBar = new HomeToolBar();
        this.insertToolBar = new InsertToolBar();
        this.viewToolBar = new ViewToolBar();
        this.shareToolBar = new ShareToolBar();
        this.aboutToolBar = new AboutToolBar();

        this.addTab("File", this.fileToolBar);
        this.addTab("Home", this.homeToolBar);
        this.addTab("Insert", this.insertToolBar);
        this.addTab("View", this.viewToolBar);
        this.addTab("Share", this.shareToolBar);
        this.addTab("About", this.aboutToolBar);
    }
}
