package com.scc210groupproject.ui;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.liveness.IUpdateListener;
import com.scc210groupproject.structure.liveness.IUpdateProvider;
import com.scc210groupproject.structure.liveness.UpdateManager;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.helper.ColourPalette;
import com.scc210groupproject.ui.menuBarTabs.MenuBarTabs;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame implements IUpdateProvider
{
    public static UIFrame instance;

    public UpdateManager manager = new UpdateManager(this);

    /*
     * Gets the dimensions of the screen the program is run on. Allows for the program dimensions
     * to be set to the size of the screen no matter the computer.
     * */
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    public UIFrame()
    {
        instance = this;

        this.setTitle("Presentation Program");
        this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1080, 720));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());

        // this is for windows os where there is a tendency to create any java frame below the minimum size 
        this.setMinimumSize(new Dimension(1100, 800));

        UIFrame self = this;
        this.addUpdateListener(new IUpdateListener() {
            @Override
            public void onUpdate(Object object) {
                self.getContentPane().setBackground(UIManager.getColor("Main.Base"));
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        int gap = 6;

        /*
        * Settings
        */
        Settings.instance = new Settings();

        ColourPalette.instance = new ColourPalette();

        /*
         * Application Window Components and their position on the frame
         * */

        MenuBarTabs.instance = new MenuBarTabs(0, 40);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(0, 0, gap, 0);
        this.add(MenuBarTabs.instance, gbc);

        ContextMenuPanel contextMenuPanel = new ContextMenuPanel(0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.79;
        gbc.insets = new Insets(gap, 0, gap, gap);
        this.add(contextMenuPanel, gbc);

        MainDisplayPanel.instance = new MainDisplayPanel(0, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.8;
        gbc.insets = new Insets(gap, gap, gap, 0);
        this.add(MainDisplayPanel.instance, gbc);

        SlideManager.slideManager = new SlideManager(MainDisplayPanel.instance);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 0.01;
        gbc.insets = new Insets(gap, 0, 0, 0);
        this.add(SlideManager.slideManager.createPresentationSlider(), gbc);

        new ShapesPopup();

        setVisible(true);

        notifyUpdate(this);

        Presentation.createNewAsCurrent();
    }

    @Override
    public UpdateManager getUpdateManager() {
        return manager;
    }
}
