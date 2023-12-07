package com.scc210groupproject.applicationWIndow.topBar;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    private JButton saveBtn, undoBtn, redoBtn;
    public TopBar(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        this.saveBtn = new JButton("S");
        this.saveBtn.setBounds(0, 0, 50, 20);
        this.saveBtn.setFocusable(false);

        this.undoBtn = new JButton("U");
        this.undoBtn.setBounds(50, 0, 50, 20);
        this.undoBtn.setFocusable(false);

        this.redoBtn = new JButton("R");
        this.redoBtn.setBounds(100, 0, 50, 20);
        this.redoBtn.setFocusable(false);

        this.add(saveBtn);
        this.add(undoBtn);
        this.add(redoBtn);
    }
}
