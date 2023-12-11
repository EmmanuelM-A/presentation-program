package com.scc210groupproject.applicationWIndow.contextMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class is the where the context menus will be added - STILL IN PROGRESS
 *
 * @author madukaag
 * */
public class ContextMenuPanel extends JPanel {
    public ContextMenuPanel(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
    }

}
