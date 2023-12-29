package com.scc210groupproject.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.scc210groupproject.structure.Slide;

public class ScaledPanel extends JPanel
{
    private BufferedImage image;
    private Point offset;

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

        if (image == null)
            return;

        ((Graphics2D)g).drawImage(image, offset.x, offset.y, null);
    }

    public void renderSlide(Slide slide) {
        if (slide == null)
            return;

        double slideRatio = (double)slide.asComp().getWidth() / (double)slide.asComp().getHeight();
        double diplayRatio = super.getWidth() / super.getHeight();

        Dimension dimension = slideRatio > diplayRatio ?
            new Dimension(super.getWidth(), (int)((double)super.getWidth() / slideRatio)) :
            new Dimension((int)((double)super.getHeight() * slideRatio), super.getHeight());

        image = slide.createPreview(dimension);
        offset = new Point(
            (super.getWidth() - dimension.width) / 2,
            (super.getHeight() - dimension.height) / 2);

        super.repaint();
    }

    public void clearRender() {
        image = null;

        super.repaint();
    }
}
