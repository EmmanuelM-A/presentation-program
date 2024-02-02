package com.scc210groupproject.ui.menuBarTabs;

import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
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

    public MenuBarTabs(JFrame frame, ContextMenuPanel contextMenuPanel, int width, int height, Color colour) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(colour);
        this.setFocusable(false);

        this.fileToolBar = new FileToolBar(ToolBar.getRecentsPanel());
        this.fileToolBar.setBackground(colour);

        this.homeToolBar = new HomeToolBar(ToolBar.getRecentsPanel());
        this.homeToolBar.setBackground(colour);

        this.insertToolBar = new InsertToolBar(frame, contextMenuPanel, ToolBar.getRecentsPanel());
        this.insertToolBar.setBackground(colour);

        this.viewToolBar = new ViewToolBar(ToolBar.getRecentsPanel());
        this.viewToolBar.setBackground(colour);

        this.shareToolBar = new ShareToolBar(ToolBar.getRecentsPanel());
        this.shareToolBar.setBackground(colour);

        this.aboutToolBar = new AboutToolBar(ToolBar.getRecentsPanel());
        this.aboutToolBar.setBackground(colour);

        super.add(this.fileToolBar);
        super.add(this.homeToolBar);
        super.add(this.insertToolBar);
        super.add(this.viewToolBar);
        super.add(this.shareToolBar);
        super.add(this.aboutToolBar);
    }
}
