package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.contextMenu.ContextMenuPanel;
import com.scc210groupproject.applicationWIndow.menuBarTabs.MenuBarTabs;
import com.scc210groupproject.structure.Slide;

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
    private MainDisplayPanel mainDisplayPanel;

    /*public ApplicationWindow() {
        this.setTitle("Presentation Program");
        this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1000, 700));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout(10, 10));
        this.getContentPane().setBackground(new Color(211, 211, 211));

        // Instantiates the class and adds a custom title bar to the frame. REQUIRES A BIT OF REPOSITIONING TO DISPLAY
        //this.customTitleBar = new TitleBar(this);

        this.contextMenuPanel = new ContextMenuPanel(300, 100, Color.WHITE);

        this.menuBarTabs = new MenuBarTabs(this, this.contextMenuPanel, 100, 100, Color.WHITE);

        this.mainDisplayPanel = new MainDisplayPanel(100, 100, Color.WHITE);

<<<<<<< HEAD
        SlideManager.slideManager = new SlideManager(this.mainDisplayPanel);
=======
        this.slideManager = new SlideManager(this.mainDisplayPanel, this);
>>>>>>> 7b4e027 (Slides are now being painted onto the main display instead of being added)

        //SlideImage slideImage = new SlideImage(slideManager.getCurrentSlide());

        /*
         * The adds and positions each panel/section/element/component onto the window
         * */
       /* this.add(menuBarTabs, BorderLayout.NORTH);

        this.add(contextMenuPanel, BorderLayout.WEST);

        this.add(mainDisplayPanel, BorderLayout.CENTER);

        SlideManager.slideManager.createPresentationSlider();

        //this.add(slideManager.createPresentationSlider(), BorderLayout.SOUTH);

        //mainDisplayPanel.add(slideImage);

        this.setVisible(true);

    }*/

    GridBagConstraints gbc = new GridBagConstraints();

    int gap = 6;

    public ApplicationWindow() {
        this.setTitle("Presentation Program");
        this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(211, 211, 211));

        this.contextMenuPanel = new ContextMenuPanel(0, 0, Color.WHITE);

        this.menuBarTabs = new MenuBarTabs(this, this.contextMenuPanel, 0, 0, Color.WHITE);

        this.mainDisplayPanel = new MainDisplayPanel(0, 0, Color.WHITE);

        //this.slideManager = new SlideManager(this.mainDisplayPanel, this);
        SlideManager.slideManager = new SlideManager(this.mainDisplayPanel, this);

        // Instantiates the class and adds a custom title bar to the frame. REQUIRES A BIT OF REPOSITIONING TO DISPLAY
        //this.customTitleBar = new TitleBar(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(0, 0, gap, 0);
        this.add(menuBarTabs, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.79;
        gbc.insets = new Insets(gap, 0, gap, gap);
        this.add(contextMenuPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.8;
        gbc.insets = new Insets(gap, gap, gap, 0);
        this.add(mainDisplayPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 0.01;
        gbc.insets = new Insets(gap, 0, 0, 0);
        this.add(SlideManager.slideManager.createPresentationSlider(), gbc);

        setVisible(true);

    }
}
