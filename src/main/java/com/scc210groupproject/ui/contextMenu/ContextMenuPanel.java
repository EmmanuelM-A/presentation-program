package com.scc210groupproject.ui.contextMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the where the context menus will be added - STILL IN PROGRESS
 *
 * @author madukaag
 * */
public class ContextMenuPanel extends JPanel implements ActionListener {
    public ContextMenuPanel(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
