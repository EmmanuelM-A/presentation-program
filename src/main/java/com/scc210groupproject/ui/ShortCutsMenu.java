package com.scc210groupproject.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class ShortCutsMenu extends JPopupMenu {
    public ShortCutsMenu() {
        makeMenuItem("<html><u>F</u>ile Tab</html>");
        makeMenuItem("<html><u>H</u>ome Tab</html>");
        makeMenuItem("<html><u>I</u>nsert Tab</html>");
        makeMenuItem("<html><u>V</u>iew Tab</html>");
        makeMenuItem("<html><u>S</u>hare Tab</html>");
        makeMenuItem("<html><u>A</u>bout Tab</html>");

        
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
