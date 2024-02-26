package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.scc210groupproject.ui.ShortCutsMenu;
import com.scc210groupproject.ui.menuBarTabs.MenuBarTabs;

import javax.swing.*;

public class ShortcutsAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent discard) {
        JButton shortcuts = MenuBarTabs.instance.getAboutToolBar().getShortcuts();
        shortcuts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    ShortCutsMenu shortcutsPopMenu = new ShortCutsMenu();
                    shortcutsPopMenu.show(shortcuts, 2, 76);
                }
            }
        });
    }
}
