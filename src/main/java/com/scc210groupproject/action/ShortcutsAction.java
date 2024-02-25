package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.ShortCutsMenu;

public class ShortcutsAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent discard) {
        new ShortCutsMenu();
    }
}
