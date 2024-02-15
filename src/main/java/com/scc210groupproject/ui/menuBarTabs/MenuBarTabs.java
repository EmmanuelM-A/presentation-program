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
    public final InsertToolBar insertToolBar;
    private final ViewToolBar viewToolBar;
    private final ShareToolBar shareToolBar;
    private final AboutToolBar aboutToolBar;

    public MenuBarTabs(JFrame frame, ContextMenuPanel contextMenuPanel, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(false);

        this.fileToolBar = new FileToolBar(ToolBar.getRecentsPanel());

        this.homeToolBar = new HomeToolBar(ToolBar.getRecentsPanel());

        this.insertToolBar = new InsertToolBar(frame, contextMenuPanel, ToolBar.getRecentsPanel());

        this.viewToolBar = new ViewToolBar(ToolBar.getRecentsPanel());

        this.shareToolBar = new ShareToolBar(ToolBar.getRecentsPanel());

        this.aboutToolBar = new AboutToolBar(ToolBar.getRecentsPanel());

        super.add(this.fileToolBar);
        super.add(this.homeToolBar);
        super.add(this.insertToolBar);
        super.add(this.viewToolBar);
        super.add(this.shareToolBar);
        super.add(this.aboutToolBar);
    }
}
