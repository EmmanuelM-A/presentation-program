package com.scc210groupproject.applicationWIndow.mainDisplay;

import javax.swing.*;
import java.awt.*;

public class MainDisplayPanel extends JPanel {
    private MainDisplay mainDisplay;
    public MainDisplayPanel(int width, int height, Color colour) {
        mainDisplay = new MainDisplay();

        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        //this.add(this.mainDisplay, BorderLayout.CENTER);
    }
}
