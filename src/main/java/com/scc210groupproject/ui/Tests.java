package com.scc210groupproject.ui;

import com.scc210groupproject.structure.liveness.IUpdateListener;
import com.scc210groupproject.structure.liveness.IUpdateProvider;
import com.scc210groupproject.structure.liveness.UpdateManager;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.menuBarTabs.MenuBarTabs;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to test new features and components. NO CLASSES IMPORT THIS CLASS
 *
 * @author madukaag
 * */
public class Tests extends JFrame implements IUpdateListener, IUpdateProvider {    
    GridBagConstraints gbc = new GridBagConstraints();

    int gap = 6;

    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public Tests() {

        this.setTitle("Testing Testing");
        this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());

        /*
         * Application Window Components and their position on the frame
         * */

        // The custom title bar
        TitleBar.instance = new TitleBar(this);
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 0.01;
            add(TitleBar.instance.createTitleBar(), gbc);
        }

        // The Toolbar tabs
        {
            MenuBarTabs menuBarTabs = new MenuBarTabs(0, 40);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 0.14;
            gbc.insets = new Insets(0, 0, gap, 0);
            add(menuBarTabs, gbc);
        }

        // The section where the context menus will be positioned
        ContextMenuPanel contextMenuPanel = new ContextMenuPanel(0, 0);
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 0.25;
            gbc.weighty = 0.77;
            gbc.insets = new Insets(gap, 0, gap, gap);
            add(contextMenuPanel, gbc);
        }

        // Main display panel
        MainDisplayPanel mainDisplayPanel = new MainDisplayPanel(0, 0);
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 0.75;
            gbc.weighty = 0.77;
            gbc.insets = new Insets(gap, gap, gap, 0);
            add(mainDisplayPanel, gbc);
        }

        // The Slide viewer panel
        SlideManager.slideManager = new SlideManager(mainDisplayPanel);
        {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 0.03;
            gbc.insets = new Insets(gap, 0, 0, 0);
            add(SlideManager.slideManager.createPresentationSlider(), gbc);
        }

        this.setVisible(true);
    }

    public static void main(String args[]) {
        new Tests();
    }

    @Override
    public UpdateManager getUpdateManager() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUpdateManager'");
    }

    @Override
    public void onUpdate(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onUpdate'");
    }
}



