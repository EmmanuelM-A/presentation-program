package com.scc210groupproject.ui.menuBarTabs;

import com.scc210groupproject.ui.menuBarTabs.toolBars.*;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the menu toolbar where all the different tabs and their corresponding toolbars
 * are added, displayed and positioned.
 *
 * @author madukaag 
 * */
public class MenuBarTabs extends JTabbedPane {
    private final FileToolBar fileToolBar;
    private final HomeToolBar homeToolBar;
    private final InsertToolBar insertToolBar;
    private final ViewToolBar viewToolBar;
    private final ShareToolBar shareToolBar;
    private final AboutToolBar aboutToolBar;

    public static MenuBarTabs instance;

    public MenuBarTabs(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(false);

        this.fileToolBar = new FileToolBar(ToolBar.getRecentsPanel());

        this.homeToolBar = new HomeToolBar(ToolBar.getRecentsPanel());

        this.insertToolBar = new InsertToolBar(ToolBar.getRecentsPanel());

        this.viewToolBar = new ViewToolBar(ToolBar.getRecentsPanel());

        this.shareToolBar = new ShareToolBar(ToolBar.getRecentsPanel());

        this.aboutToolBar = new AboutToolBar(ToolBar.getRecentsPanel());

        super.add(this.fileToolBar);
        super.add(this.homeToolBar);
        super.add(this.insertToolBar);
        super.add(this.viewToolBar);
        super.add(this.shareToolBar);
        super.add(this.aboutToolBar);

        instance = this;
    }

    public InsertToolBar getInsertToolBar() {
        return this.insertToolBar;
    }
}
