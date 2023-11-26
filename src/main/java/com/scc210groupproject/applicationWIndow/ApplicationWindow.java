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
    public ApplicationWindow() {
        setTitle("Presentation Program");
        setSize((int)size.getWidth(), (int)size.getHeight());
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());

        this.menuBarTabs = new MenuBarTabs();
        
        this.contextMenuPanel = new ContextMenuPanel(300, 100);

        this.mainDisplayPanel = new MainDisplayPanel(100, 100);
        
        this.slideManagerPanel = new SlideManagerPanel(100, 200);

        getContentPane().add(menuBarTabs, BorderLayout.NORTH);
        getContentPane().add(contextMenuPanel, BorderLayout.WEST);
        getContentPane().add(mainDisplayPanel, BorderLayout.CENTER);
        getContentPane().add(slideManagerPanel, BorderLayout.SOUTH);

        setVisible(true);

    }

}
