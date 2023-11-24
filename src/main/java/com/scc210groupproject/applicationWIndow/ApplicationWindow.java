package com.scc210groupproject.applicationWindow;


import javax.swing.*;
import java.awt.*;


public class ApplicationWindow extends JFrame {
    final private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public ApplicationWindow() {
        setTitle("Presentation Program");
        //setSize((int)size.getWidth(), (int)size.getHeight());
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu homeMenu = new JMenu("Home");
        JMenu insertMenu = new JMenu("Insert");
        JMenu shareMenu = new JMenu("Share");
        JMenu viewMenu = new JMenu("View");
        JMenu aboutMenu = new JMenu("About");

        menuBar.add(fileMenu);
        menuBar.add(homeMenu);
        menuBar.add(insertMenu);
        menuBar.add(shareMenu);
        menuBar.add(viewMenu);
        menuBar.add(aboutMenu);

        this.setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        JButton newBtn = new JButton("NEW");
        JButton openBtn = new JButton("OPEN");
        JButton saveBtn = new JButton("SAVE");
        //JButton btn = new JButton("");

        toolBar.add(newBtn);
        toolBar.add(openBtn);
        toolBar.add(saveBtn);

        this.getContentPane().add(toolBar, BorderLayout.NORTH);

        setVisible(true);
    }
}
