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

        this.fileToolBar = new FileToolBar();
        this.fileToolBar.setBackground(Color.WHITE);

        this.homeToolBar = new HomeToolBar();
        this.homeToolBar.setBackground(Color.WHITE);

        this.insertToolBar = new InsertToolBar(frame, contextMenuPanel);
        this.insertToolBar.setBackground(Color.WHITE);

        this.viewToolBar = new ViewToolBar();
        this.viewToolBar.setBackground(Color.WHITE);

        this.shareToolBar = new ShareToolBar();
        this.shareToolBar.setBackground(Color.WHITE);

        this.aboutToolBar = new AboutToolBar();
        this.aboutToolBar.setBackground(Color.WHITE);

        super.add(this.fileToolBar);
        super.add(this.homeToolBar);
        super.add(this.insertToolBar);
        super.add(this.viewToolBar);
        super.add(this.shareToolBar);
        super.add(this.aboutToolBar);
    }
}
