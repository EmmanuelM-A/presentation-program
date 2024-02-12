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
    
    private static ContextMenuPanel instance = null;

    private static Object currentMenuOwner = null;

    public static void removeOwner(Object owner) {
        if (owner != currentMenuOwner)
            return;
        
        instance.removeAll();
        instance.revalidate();
    }

    public static void setMenu(Object owner, ContextMenu menu) {
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

    public void setPanel()
    {


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
