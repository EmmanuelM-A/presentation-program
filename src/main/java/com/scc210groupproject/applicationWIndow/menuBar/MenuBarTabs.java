package com.scc210groupproject.applicationWIndow.menuBar;

import com.scc210groupproject.applicationWIndow.toolBars.*;

import javax.swing.*;

public class MenuBarTabs extends JTabbedPane {
    private final FileToolBar fileToolBar;
    private final HomeToolBar homeToolBar;
    private final InsertToolBar insertToolBar;
    private final ViewToolBar viewToolBar;
    private final ShareToolBar shareToolBar;
    private final AboutToolBar aboutToolBar;

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
