package com.scc210groupproject.ui.contextMenu;

import javax.swing.*;

import java.awt.*;

public class ContextMenuPanel extends JPanel {
    
    public static ContextMenuPanel instance = null;

    public static ContextMenu menu = null;

    public static Object currentMenuOwner = null;

    public static void removeOwner(Object owner) {
        if (owner != currentMenuOwner)
            return;
        
        instance.removeAll();
        instance.revalidate();
    }

    public static void setMenu(Object owner, ContextMenu menu) {
        ContextMenuPanel.menu = menu;
        currentMenuOwner = owner;

        instance.removeAll();

        if (menu != null)
            instance.add(menu);
        
        instance.revalidate();
    }

    public ContextMenuPanel(int width, int height) {
        instance = this;

        Dimension fixed = new Dimension(width, height);
        this.setPreferredSize(fixed);
        this.setMaximumSize(fixed);
        this.setLayout(new GridLayout(0, 1));

        this.revalidate();
    }

    public static JPanel contextMenuPanel = new JPanel(new BorderLayout());

    public ContextMenuPanel(JPanel contextMenu) {
        contextMenuPanel.add(contextMenu, BorderLayout.CENTER);
    }
}
