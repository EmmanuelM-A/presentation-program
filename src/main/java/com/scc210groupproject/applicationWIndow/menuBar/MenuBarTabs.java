package com.scc210groupproject.applicationWIndow.menuBar;

import com.scc210groupproject.applicationWIndow.toolBars.*;

import javax.swing.*;
import java.awt.*;

public class MenuBarTabs extends JTabbedPane {
    private final FileToolBar fileToolBar;
    private final HomeToolBar homeToolBar;
    private final InsertToolBar insertToolBar;
    private final ViewToolBar viewToolBar;
    private final ShareToolBar shareToolBar;
    private final AboutToolBar aboutToolBar;

    public MenuBarTabs(int width, int height, Color colour) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(colour);
        this.setFocusable(false);

        this.fileToolBar = new FileToolBar();
        this.homeToolBar = new HomeToolBar();
        this.insertToolBar = new InsertToolBar();
        this.viewToolBar = new ViewToolBar();
        this.shareToolBar = new ShareToolBar();
        this.aboutToolBar = new AboutToolBar();

        this.fileToolBar.setBackground(Color.WHITE);
        this.homeToolBar.setBackground(Color.WHITE);
        this.insertToolBar.setBackground(Color.WHITE);
        this.viewToolBar.setBackground(Color.WHITE);
        this.shareToolBar.setBackground(Color.WHITE);
        this.aboutToolBar.setBackground(Color.WHITE);

        super.add(this.fileToolBar);
        super.add(this.homeToolBar);
        super.add(this.insertToolBar);
        super.add(this.viewToolBar);
        super.add(this.shareToolBar);
        super.add(this.aboutToolBar);
    }
}
