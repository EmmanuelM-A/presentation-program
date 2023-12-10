package com.scc210groupproject.applicationWIndow.mainDisplay;

import javax.swing.*;
import java.awt.*;

public class MainDisplay extends JPanel {
    public MainDisplay(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

    }
}
