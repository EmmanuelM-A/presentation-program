package com.scc210groupproject.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class ShortCutsMenu extends JPopupMenu {
    public ShortCutsMenu() {
        makeMenuItem("File Tab", KeyEvent.VK_F);
        makeMenuItem("Home Tab", KeyEvent.VK_H);
        makeMenuItem("Insert Tab", KeyEvent.VK_I);
        makeMenuItem("View Tab", KeyEvent.VK_V);
        makeMenuItem("Share Tab", KeyEvent.VK_S);
        makeMenuItem("ABout Tab", KeyEvent.VK_A);

        
    }

    public void makeMenuItem(String title, KeyStroke shortcut) {
        JMenuItem item = new JMenuItem(title);

        item.setAccelerator(shortcut);

        this.add(item);
    }

    public void makeMenuItem(String title, int key) {
        JMenuItem item = new JMenuItem(title);

        item.setMnemonic(key);

        this.add(item);
    }
}
