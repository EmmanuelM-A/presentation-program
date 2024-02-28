package com.scc210groupproject.ui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class ShortCutsMenu extends JPopupMenu {
    public ShortCutsMenu() {
        makeMenuItem("<html><u>F</u>ile Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>H</u>ome Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>I</u>nsert Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>V</u>iew Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>S</u>hare Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>A</u>bout Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
    }

    public void makeMenuItem(String title, KeyStroke shortcut) {
        JMenuItem item = new JMenuItem(title);

        item.setAccelerator(shortcut);

        this.add(item);
    }


    public void makeMenuItem(String title) {
        JMenuItem item = new JMenuItem(title);

        this.add(item);
    }
}
