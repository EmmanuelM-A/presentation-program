package com.scc210groupproject.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class ScaledPanel extends JPanel
{
    private Dimension contentSize;

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (contentSize == null)
            return;

        Dimension containerSize = super.getSize();
        double ratioX = contentSize.getWidth() / containerSize.getWidth();
        double ratioY = contentSize.getHeight() / containerSize.getHeight();
        double maxRatio = Math.max(ratioX, ratioY);

        double scale = 1.0 / maxRatio;

        AffineTransform transform = new AffineTransform();
        transform.scale(scale, scale);

        ((Graphics2D)g).transform(transform);
    }

    public void setContentSize(Dimension size)
    {
        contentSize = size;

        this.repaint();
    }
}
