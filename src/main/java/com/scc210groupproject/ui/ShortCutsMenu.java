package com.scc210groupproject.ui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class ShortCutsMenu extends JPopupMenu {
    public ShortCutsMenu() {
        makeMenuItem("<html><u>F</u>ile Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_F,
        InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>H</u>ome Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_H,
        InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>I</u>nsert Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_I,
        InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>V</u>iew Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_V,
        InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>S</u>hare Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_S,
        InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html><u>A</u>bout Tab</html>", KeyStroke.getKeyStroke(KeyEvent.VK_A,
        InputEvent.ALT_DOWN_MASK));

        makeMenuItem("----------------------------------------"); 
        // Sectioning the menu so that it's clearer to read

        // makeMenuItem("<html><u>S</u>ave</html>", KeyStroke.getKeyStroke(KeyEvent.VK_S,
        // InputEvent.ALT_DOWN_MASK));
        // makeMenuItem("<html><u>L</u>oad</html>", KeyStroke.getKeyStroke(KeyEvent.VK_L,
        // InputEvent.ALT_DOWN_MASK));
        // makeMenuItem("<html><u>E</u>xport</html>", KeyStroke.getKeyStroke(KeyEvent.VK_E,
        // InputEvent.ALT_DOWN_MASK));
        // makeMenuItem("<html><u>P</u>rint</html>", KeyStroke.getKeyStroke(KeyEvent.VK_P,
        // InputEvent.ALT_DOWN_MASK));
        // makeMenuItem("<html><u>T</u>heme</html>", KeyStroke.getKeyStroke(KeyEvent.VK_T,
        // InputEvent.ALT_DOWN_MASK));
        // makeMenuItem("<html><u>N</u>ew slide</html>", KeyStroke.getKeyStroke(KeyEvent.VK_N,
        // InputEvent.ALT_DOWN_MASK));

        // makeMenuItem("----------------------------------------"); 

        // makeMenuItem("<html><u>T</u>ext</html>", KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        // makeMenuItem("<html><u>I</u>mage</html>", KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        // makeMenuItem("<html><u>V</u>ideo</html>", KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        // makeMenuItem("<html><u>C</u>hart</html>", KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        // makeMenuItem("<html><u>A</u>udio</html>", KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        // makeMenuItem("<html><u>S</u>hape</html>", KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        // makeMenuItem("<html><u>L</u>ine</html>", KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));

        // makeMenuItem("----------------------------------------"); 

        makeMenuItem("<html>Present from Beginning</html>", KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
        makeMenuItem("<html>Present from Current</html>", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));

        
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
