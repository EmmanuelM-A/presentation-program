package com.scc210groupproject.applicationWIndow;


import com.scc210groupproject.applicationWIndow.contextMenu.ContextMenuPanel;
import com.scc210groupproject.applicationWIndow.mainDisplay.MainDisplayPanel;
import com.scc210groupproject.applicationWIndow.menuBar.MenuBarTabs;
import com.scc210groupproject.applicationWIndow.slideManager.SlideManagerPanel;

import javax.swing.*;
import java.awt.*;


public class ApplicationWindow extends JFrame {
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private MenuBarTabs menuBarTabs;
    private ContextMenuPanel contextMenuPanel;
    private MainDisplayPanel mainDisplayPanel;
    private SlideManagerPanel slideManagerPanel;
    private JPanel emptySpace;

    // Colour Scheme
    private final Color backgroundColour = new Color(211, 211, 211);
    private final Color menuBarTabsColour = Color.WHITE;

    private final Color mainDisplayColour = new Color(255,255, 255);
    public ApplicationWindow() {
        setTitle("Presentation Program");
        setSize((int)size.getWidth(), (int)size.getHeight());
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout(40, 20));

        this.getContentPane().setBackground(backgroundColour);

        this.menuBarTabs = new MenuBarTabs(menuBarTabsColour);
        
        this.contextMenuPanel = new ContextMenuPanel(300, 100, Color.BLUE);

        this.mainDisplayPanel = new MainDisplayPanel(100, 100, mainDisplayColour);
        
        this.slideManagerPanel = new SlideManagerPanel(100, 200, Color.YELLOW);

        this.emptySpace = new JPanel();
        this.emptySpace.setBackground(backgroundColour);
        this.emptySpace.setPreferredSize(new Dimension(100, 100));

        this.add(menuBarTabs, BorderLayout.NORTH);
        this.add(contextMenuPanel, BorderLayout.WEST);
        this.add(mainDisplayPanel, BorderLayout.CENTER);
        this.add(slideManagerPanel, BorderLayout.SOUTH);
        this.add(emptySpace, BorderLayout.EAST);

        setVisible(true);

    }

}
