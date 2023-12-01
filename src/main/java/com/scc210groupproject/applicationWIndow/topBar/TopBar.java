package com.scc210groupproject.applicationWIndow.topBar;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    JButton saveBtn, undoBtn, redoBtn, btn4, btn5, btn6;
    public TopBar(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        this.saveBtn = new JButton("S");
        this.undoBtn = new JButton("U");
        this.redoBtn = new JButton("R");

        this.saveBtn.setBounds(0, 0, 50, 20);
        this.undoBtn.setBounds(50, 0, 50, 20);
        this.redoBtn.setBounds(100, 0, 50, 20);

        this.add(saveBtn);
        this.add(undoBtn);
        this.add(redoBtn);
    }
}
