package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the main display where the slides will be viewed and altered.
 *
 * */
public class MainDisplay extends JPanel {
    public MainDisplay(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.blue);

        //this.add(new JPanel(panel, BorderLayout);
    }
}
