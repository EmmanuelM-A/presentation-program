package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.menuBar.MenuBarTabs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class window extends JFrame {
    JPanel titleBar = new JPanel();
    JPanel d = new JPanel();

    JPanel r = new JPanel();
    JPanel L = new JPanel();

    JButton btn1, btn2, btn3, btn4, btn5, btn6;
    MenuBarTabs m = new MenuBarTabs(Color.GRAY);
    public window() {
        setTitle("Presentation Program");
        //setSize((int)size.getWidth(), (int)size.getHeight());
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());

        btn1 = new JButton("SAVE");
        btn2 = new JButton("UNDO");
        btn3 = new JButton("REDO");
        btn4 = new JButton("MINI");
        btn5 = new JButton("FULL");
        btn6 = new JButton("EXIT");

        titleBar.setPreferredSize(new Dimension(1000, 140));
        titleBar.setBackground(Color.blue);
        titleBar.setLayout(new BorderLayout());

        d.setPreferredSize(new Dimension(1000, 40));
        d.setBackground(Color.red);

        m.setPreferredSize(new Dimension(1000, 100));
        titleBar.add(d, BorderLayout.NORTH);
        titleBar.add(m, BorderLayout.SOUTH);

        add(titleBar, BorderLayout.NORTH);
        setVisible(true);
    }

    public static void main(String[] args)  {
        new window();
    }
}
