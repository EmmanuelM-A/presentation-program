package com.scc210groupproject.applicationWIndow.contextMenu;

import javax.swing.*;
import java.awt.*;

public class ContextMenuPanel extends JPanel {
    public ContextMenuPanel(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
    }
}
