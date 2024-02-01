package com.scc210groupproject.ui;

import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.menuBarTabs.MenuBarTabs;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame
{
    /*
     * Gets the dimensions of the screen the program is run on. Allows for the program dimensions
     * to be set to the size of the screen no matter the computer.
     * */
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public UIFrame()
    {
        this.setTitle("Presentation Program");
        //this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(211, 211, 211));

        GridBagConstraints gbc = new GridBagConstraints();
        int gap = 6;

        /*
         * Application Window Components and their position on the frame
         * */
        /*JPanel top = new JPanel(new BorderLayout());
        top.setPreferredSize(new Dimension(2000, 150));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.01;
        gbc.insets = new Insets(0, 0, gap, 0);
        this.add(top, gbc);

        // Instantiates the class and adds a custom title bar to the frame. REQUIRES A BIT OF REPOSITIONING TO DISPLAY
        //TitleBar customTitleBar = new TitleBar();
        top.add(TitleBar.createTitleBar(this), BorderLayout.NORTH);*/

        MenuBarTabs menuBarTabs = new MenuBarTabs(this, null, 0, 40, Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(0, 0, gap, 0);
        this.add(menuBarTabs, gbc);

        ContextMenuPanel contextMenuPanel = new ContextMenuPanel(0, 0, Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.79;
        gbc.insets = new Insets(gap, 0, gap, gap);
        this.add(contextMenuPanel, gbc);

        MainDisplayPanel mainDisplayPanel = new MainDisplayPanel(0, 0, Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.8;
        gbc.insets = new Insets(gap, gap, gap, 0);
        this.add(mainDisplayPanel, gbc);

        SlideManager.slideManager = new SlideManager(mainDisplayPanel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 0.01;
        gbc.insets = new Insets(gap, 0, 0, 0);
        this.add(SlideManager.slideManager.createPresentationSlider(), gbc);


        setVisible(true);
    }
}
