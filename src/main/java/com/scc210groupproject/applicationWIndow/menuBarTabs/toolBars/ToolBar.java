package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import javax.swing.*;
import java.awt.*;

public abstract class ToolBar extends JToolBar {

    /**
     * Creates and returns a toolbar button with its title and icon set
     * @param title The name/title of the button
     * @param icon The button's icon
     * @return JButton
     */
    public JButton makeToolbarButton(String title, ImageIcon icon) {
        JButton button = new JButton(title, icon);

        // For button focus frame colour use Look & Feel to change its focus colour
        //button.setPreferredSize(new Dimension(54, 54));
        button.setSize(new Dimension(54, 54));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setOpaque(false);
        button.setBorderPainted(false);

        return button;
    }

    /**
     * Creates and adds a vertical separator to the given toolbar
     * @param toolBar A JToolBar
     */
    public void separator(JToolBar toolBar) {
        JSeparator separator = new JSeparator();
        // REMINDER - USE LOOK AND FEEL TO CHANGE SEPARATOR COLOUR
        separator.setMaximumSize(new Dimension(1, 60));
        separator.setOrientation(JSeparator.VERTICAL);

        toolBar.addSeparator();
        toolBar.add(separator);
        toolBar.addSeparator();
    }
}
