package com.scc210groupproject.ui.menuBarTabs;

import com.scc210groupproject.ui.menuBarTabs.toolBars.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This class contains the menu toolbar where all the different tabs and their corresponding toolbars
 * are added, displayed and positioned.
 *
 * @author madukaag 
 * */
public class MenuBarTabs extends JTabbedPane {
    /**
     * Represent each toolbar in the menu bar tab
     */
    private final FileToolBar fileToolBar;
    private final HomeToolBar homeToolBar;
    private final InsertToolBar insertToolBar;
    private final ViewToolBar viewToolBar;
    private final ShareToolBar shareToolBar;
    private final AboutToolBar aboutToolBar;

    /**
     * An instance of this class
     */
    public static MenuBarTabs instance;

    /**
     * The construtor for the menu bar tabs - Adds each toolbar to the menu bar tabs
     * @param width The width of the menu bar tabs 
     * @param height The height of the menu bar tabs
     */
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

        // Sets the shortcuts for the menu bar tab

        this.setMnemonicAt(0, KeyEvent.VK_F);
        this.setMnemonicAt(1, KeyEvent.VK_H);
        this.setMnemonicAt(2, KeyEvent.VK_I);
        this.setMnemonicAt(3, KeyEvent.VK_V);
        this.setMnemonicAt(4, KeyEvent.VK_S);
        this.setMnemonicAt(5, KeyEvent.VK_A);

        instance = this;


    }

    /**
     * Get the file toolbar 
     * @return ToolBar
     */
    public FileToolBar getFileToolBar() {
        return this.fileToolBar;
    }

    /**
     * Get the home toolbar 
     * @return ToolBar
     */
    public HomeToolBar getHomeToolBar() {
        return this.homeToolBar;
    }

    /**
     * Get the insert toolbar 
     * @return ToolBar
     */
    public InsertToolBar getInsertToolBar() {
        return this.insertToolBar;
    }

    /**
     * Get the view toolbar 
     * @return ToolBar
     */
    public ViewToolBar getViewToolBar() {
        return this.viewToolBar;
    }

    /**
     * Get the share toolbar 
     * @return ToolBar
     */
    public ShareToolBar getShareToolBar() {
        return this.shareToolBar;
    }

    /**
     * Get the about toolbar 
     * @return ToolBar
     */
    public AboutToolBar getAboutToolBar() {
        return this.aboutToolBar;
    }
}
