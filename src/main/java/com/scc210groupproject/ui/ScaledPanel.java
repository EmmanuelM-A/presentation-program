package com.scc210groupproject.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class ScaledPanel extends JPanel
{
    public ScaledPanel()
    {
        super();

        super.setBackground(Color.gray);
        super.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (super.getComponentCount() < 1)
            return;

        Component content = super.getComponent(0);
        
        Dimension contentSize = content.getSize();
        Dimension containerSize = super.getSize();

        double ratioX = contentSize.getWidth() / containerSize.getWidth();
        double ratioY = contentSize.getHeight() / containerSize.getHeight();
        double maxRatio = Math.max(ratioX, ratioY);

        double scale = 1.0 / maxRatio;
        
        content.setLocation(
            (int)((containerSize.getWidth() - contentSize.getWidth() * scale) * 0.5 / scale), 
            (int)((containerSize.getHeight() - contentSize.getHeight() * scale) * 0.5 / scale));

        AffineTransform transform = new AffineTransform();
        transform.scale(scale, scale);

        ((Graphics2D)g).transform(transform);
    }
}
