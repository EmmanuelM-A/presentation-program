package com.scc210groupproject.ui.contextMenu;

import javax.swing.*;
import java.awt.*;

public abstract class ContextMenu extends JPopupMenu {
    // The default font for all menu items
    private final Font menuItemFont = new Font("Arial", Font.BOLD, 12);

    // The insets for all menu items
    private final Insets menuItemInsets = new Insets(10, 20, 10, 20);

    /**
     * Creates a menu item
     * @param title The menu item name
     * @param icon The menu item icon
     * @return JMenuItem
     */
    public JMenuItem makeMenuItem(String title, ImageIcon icon) {
        JMenuItem item = new JMenuItem(title, icon);
        item.setFont(menuItemFont);
        item.setMargin(menuItemInsets);
        item.setBackground(Color.WHITE);
        item.setForeground(Color.BLACK);
        return item;
    }
}
