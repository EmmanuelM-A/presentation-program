package com.scc210groupproject.applicationWIndow;


import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;
import com.scc210groupproject.applicationWIndow.toolBars.FileToolBar;
import com.scc210groupproject.applicationWIndow.toolBars.HomeToolBar;
import com.scc210groupproject.applicationWIndow.toolBars.MenuBarTabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ApplicationWindow extends JFrame {
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private MenuBarTabs menuBarTabs;
    private JPanel contextMenu, mainDisplay, slideManager;

    public ApplicationWindow() {
        setTitle("Presentation Program");
        setSize((int)size.getWidth(), (int)size.getHeight());
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());

        this.menuBarTabs = new MenuBarTabs();

        this.contextMenu = new JPanel();
        this.contextMenu.setBackground(Color.BLUE);
        this.contextMenu.setPreferredSize(new Dimension(300, 100));

        this.mainDisplay = new JPanel();
        this.mainDisplay.setBackground(Color.RED);
        this.mainDisplay.setPreferredSize(new Dimension(100, 100));

        this.slideManager = new JPanel();
        this.slideManager.setBackground(Color.ORANGE);
        this.slideManager.setPreferredSize(new Dimension(100, 200));

        getContentPane().add(menuBarTabs, BorderLayout.NORTH);
        getContentPane().add(contextMenu, BorderLayout.WEST);
        getContentPane().add(mainDisplay, BorderLayout.CENTER);
        getContentPane().add(slideManager, BorderLayout.SOUTH);

        setVisible(true);

    }

}
