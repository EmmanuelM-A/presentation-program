package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.contextMenu.ContextMenuPanel;
import com.scc210groupproject.applicationWIndow.menuBarTabs.MenuBarTabs;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the JFrame and adds the components onto the frame.
 */
public class ApplicationWindow extends JFrame {
    /*
     * Gets the dimensions of the screen the program is run on. Allows for the program dimensions
     * to be set to the size of the screen no matter the computer.
     * */
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    /*
     * Application Window Components
     * */
    private TitleBar customTitleBar;
    private MenuBarTabs menuBarTabs;
    private ContextMenuPanel contextMenuPanel;
    //private MainDisplay mainDisplay;
    private MainDisplayPanel mainDisplayPanel;
    private SlideManager slideManager;
    private SlideManager2 slideManager2;

    public ApplicationWindow() {
        this.setTitle("Presentation Program");
        //this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout(40, 20));
        this.getContentPane().setBackground(new Color(211, 211, 211));

        //this.customTitleBar = new TitleBar(this); // Instantiates the class and adds a custom title bar to the frame

        this.contextMenuPanel = new ContextMenuPanel(300, 100, Color.WHITE);

        this.menuBarTabs = new MenuBarTabs(this, this.contextMenuPanel, 100, 100, Color.WHITE);

        this.mainDisplayPanel = new MainDisplayPanel(100, 100, Color.WHITE);

        this.slideManager2 = new SlideManager2(this, this.mainDisplayPanel); // Instantiates the class and adds the slide manager (presentation slider) to the screen

        /*
         * The adds and positions each panel/section/element/component onto the window
         * */
        this.add(menuBarTabs, BorderLayout.NORTH);
        this.add(contextMenuPanel, BorderLayout.WEST);
        this.add(mainDisplayPanel, BorderLayout.CENTER);

        this.setVisible(true);

    }
}
