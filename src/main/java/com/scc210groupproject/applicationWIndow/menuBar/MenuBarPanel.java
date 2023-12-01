package com.scc210groupproject.applicationWIndow.menuBar;

import com.scc210groupproject.applicationWIndow.topBar.TopBar;

import javax.swing.*;
import java.awt.*;

public class MenuBarPanel extends JPanel {
    private TopBar topBar;
    private MenuBarTabs menuBarTabs;

    // Colour Scheme
    private final Color menuBarTabsColour = Color.WHITE;
    public MenuBarPanel(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        this.topBar = new TopBar(100, 20, new Color(211, 211, 211));

        this.menuBarTabs = new MenuBarTabs(100, 100, menuBarTabsColour);

        this.add(topBar, BorderLayout.NORTH);
        this.add(menuBarTabs, BorderLayout.SOUTH);
    }
}
