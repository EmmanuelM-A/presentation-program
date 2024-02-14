package com.scc210groupproject.ui;

import com.scc210groupproject.action.NewBoxAction;
import com.scc210groupproject.structure.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapesPopup {


    public static JPopupMenu popupMenu;

    public ShapesPopup(JFrame UI){

        popupMenu = new JPopupMenu("Shapes");

        JMenuItem square = new JMenuItem("Box");
        square.addActionListener(new NewBoxAction());
        popupMenu.add(square);
        
        JMenuItem circle = new JMenuItem("Circle");
        circle.addActionListener(new NewBoxAction());
        popupMenu.add(circle);
    }
}
