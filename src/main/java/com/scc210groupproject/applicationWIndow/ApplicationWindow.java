package com.scc210groupproject.applicationWIndow;


import javax.swing.*;
import java.awt.*;


public class ApplicationWindow extends JFrame {
    public ApplicationWindow() {
        setTitle("Presentation Program");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new FlowLayout());

        JMenuBar toolBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu homeMenu = new JMenu("Home");
        JMenu insertMenu = new JMenu("Insert");
        JMenu shareMenu = new JMenu("Share");
        JMenu viewMenu = new JMenu("View");
        JMenu aboutMenu = new

        toolBar.add(aMenu);
        toolBar.add(bMenu);
        toolBar.add(cMenu);

        this.setJMenuBar(toolBar);
        setVisible(true);
    }
}
