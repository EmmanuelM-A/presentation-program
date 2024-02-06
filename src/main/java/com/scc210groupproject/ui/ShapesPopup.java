package com.scc210groupproject.ui;

import com.scc210groupproject.action.NewBoxAction;
import com.scc210groupproject.structure.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapesPopup extends JFrame implements ActionListener{


    public static ShapesPopup shapesPopup;
    public Popup popup;

    private PopupFactory pf;

    private JFrame owner = null;

    private JPanel p2 = null;

    ShapesPopup(JFrame UI){
        owner = UI;
        JButton square = new JButton("box");
        square.addActionListener(new NewBoxAction());

        JButton close = new JButton("close");
        close.setBackground(Color.red);
        close.addActionListener(this);

        JButton circle = new JButton("circle");
        // create a class that extends extended element for the circle
        // within that draw a circle using the graphics2d
        // when the panel gets resized redraw the circle with the appropiate size

        pf = new PopupFactory();
        p2 = new JPanel(new GridLayout(5, 1, 10, 5));
        p2.add(close);
        p2.add(square);
        p2.add(circle);
        popup = pf.getPopup(owner, p2, 500, 500);

        shapesPopup = this;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        popup.hide();
        popup = pf.getPopup(owner, p2, 500, 500);
    }
}
