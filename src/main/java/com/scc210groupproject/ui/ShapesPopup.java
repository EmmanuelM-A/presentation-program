package com.scc210groupproject.ui;

import com.scc210groupproject.action.NewBoxAction;
import com.scc210groupproject.action.NewCircleAction;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class ShapesPopup {


    public static JPopupMenu popupMenu;

    public ShapesPopup() {

        popupMenu = new JPopupMenu("Shapes");

        JMenuItem square = new JMenuItem("Box");
        square.addActionListener(new NewBoxAction());
        popupMenu.add(square);
        
        JMenuItem circle = new JMenuItem("Circle");
        circle.addActionListener(new NewCircleAction());
        popupMenu.add(circle);
    }
}
