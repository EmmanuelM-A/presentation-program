package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.contextMenu.ContextMenuPanel;
import com.scc210groupproject.applicationWIndow.menuBarTabs.MenuBarTabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class is used to test new features and components. NO CLASSES IMPORT THIS CLASS
 *
 * @author madukaag
 * */
public class Tests extends JFrame {
    JPanel sectionOne, sectionTwo, sectionThree, sectionFour;

    GridBagConstraints gbc = new GridBagConstraints();

    int gap = 6;

    private MenuBarTabs menuBarTabs;
    private ContextMenuPanel contextMenuPanel;
    private MainDisplayPanel mainDisplayPanel;
    private SlideManager slideManager;

    public Tests() {
        this.setTitle("Testing Testing");
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());

        this.contextMenuPanel = new ContextMenuPanel(300, 100, Color.WHITE);

        this.menuBarTabs = new MenuBarTabs(this, this.contextMenuPanel, 100, 100, Color.WHITE);

        this.mainDisplayPanel = new MainDisplayPanel(100, 100, Color.WHITE);

        this.slideManager = new SlideManager(this, this.mainDisplayPanel);

        /*sectionOne = new JPanel();
        sectionOne.setBackground(Color.YELLOW);

        sectionTwo = new JPanel();
        sectionTwo.setBackground(Color.GREEN);

        sectionThree = new JPanel();
        sectionThree.setBackground(Color.BLUE);*/

        sectionFour = new JPanel();
        sectionFour.setBackground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.02;
        gbc.insets = new Insets(0, 0, gap, 0);
        this.add(menuBarTabs, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.05;
        gbc.weighty = 0.94;
        gbc.insets = new Insets(gap, 0, gap, gap);
        this.add(contextMenuPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.95;
        gbc.insets = new Insets(gap, gap, gap, 0);
        this.add(mainDisplayPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 0.04;
        gbc.insets = new Insets(gap, 0, 0, 0);
        this.add(slideManager.createPresentationSlider(), gbc);

        setVisible(true);
    }

    public static void main( String[] args ) {
        new Tests();
    }
}
