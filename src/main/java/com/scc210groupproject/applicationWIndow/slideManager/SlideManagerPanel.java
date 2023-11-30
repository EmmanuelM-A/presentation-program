package com.scc210groupproject.applicationWIndow.slideManager;

import javax.swing.*;
import java.awt.*;

public class SlideManagerPanel extends JPanel {
    public SlideManagerPanel(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
    }
}
