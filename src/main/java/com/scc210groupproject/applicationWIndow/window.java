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
    MenuBarTabs m = new MenuBarTabs(100, 100, Color.GRAY);
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
        btn1.setBounds(0, 0, 50, 20);
        btn2.setBounds(50, 0, 50, 20);
        btn3.setBounds(100, 0, 50, 20);

        titleBar.setPreferredSize(new Dimension(1000, 120));
        titleBar.setBackground(Color.blue);
        titleBar.setLayout(new BorderLayout());

        d.setPreferredSize(new Dimension(1000, 20));
        d.setLayout(null);
        d.setBackground(Color.red);
        d.add(btn1);
        d.add(btn2);
        d.add(btn3);

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
