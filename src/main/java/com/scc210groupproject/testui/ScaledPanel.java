package com.scc210groupproject.testui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.scc210groupproject.applicationWIndow.SlideManager;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.input.MouseEmulator;

public class ScaledPanel extends JPanel
{
    private BufferedImage image;
    private Point offset;

    private MouseEmulator mouse = new MouseEmulator();

    private Slide slide;
    private double scale;

    public ScaledPanel()
    {
        super();

        super.setBackground(Color.gray);
        super.setLayout(null);

        super.addMouseListener(mouse);
        super.addMouseMotionListener(mouse);
        super.addMouseWheelListener(mouse);
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
        this.slide = slide;

        if (slide == null)
            return;

        double slideRatio = (double)slide.asComp().getWidth() / (double)slide.asComp().getHeight();
        double diplayRatio = (double) super.getWidth() / super.getHeight();

        Dimension dimension = slideRatio > diplayRatio ?
            new Dimension(super.getWidth(), (int)((double)super.getWidth() / slideRatio)) :
            new Dimension((int)((double)super.getHeight() * slideRatio), super.getHeight());

        image = slide.createPreview(dimension);
        offset = new Point(
            (super.getWidth() - dimension.width) / 2,
            (super.getHeight() - dimension.height) / 2);
        scale = (double)slide.asComp().getWidth() / (double)dimension.width;

        mouse.setTargetSlide(slide, offset, scale);
        super.repaint();
    }

    public void clearRender() {
        image = null;
        slide = null;

        mouse.clearTargetSlide();
        super.repaint();
    }
}
