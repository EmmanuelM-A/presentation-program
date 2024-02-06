package com.scc210groupproject.testui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.input.InputEmulator;

public class ScaledPanel extends JPanel
{
    private BufferedImage image;
    private Point offset;

    private InputEmulator emulator = new InputEmulator();

    private Slide slide;
    private double scale;

    public ScaledPanel()
    {
        super();

        super.setBackground(Color.gray);
        super.setLayout(null);

        super.addMouseListener(emulator);
        super.addMouseMotionListener(emulator);
        super.addMouseWheelListener(emulator);
        super.addKeyListener(emulator);
        super.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (image == null)
            return;

        ((Graphics2D)g).drawImage(image, offset.x, offset.y, null);
    }

    public void renderSlide(Slide lastestSlide) {

        if (lastestSlide == null)
            return;

        double slideRatio = (double)lastestSlide.asComp().getWidth() / (double)lastestSlide.asComp().getHeight();
        double diplayRatio = (double) super.getWidth() / super.getHeight();

        Dimension dimension = slideRatio > diplayRatio ?
            new Dimension(super.getWidth(), (int)((double)super.getWidth() / slideRatio)) :
            new Dimension((int)((double)super.getHeight() * slideRatio), super.getHeight());

        image = lastestSlide.createPreview(dimension);
        offset = new Point(
            (super.getWidth() - dimension.width) / 2,
            (super.getHeight() - dimension.height) / 2);
        scale = (double)lastestSlide.asComp().getWidth() / (double)dimension.width;

        if (slide != lastestSlide) 
            emulator.setTargetSlide(lastestSlide);
        emulator.setPositioning(offset, scale);
        super.repaint();
        slide = lastestSlide;
    }

    public void clearRender() {
        image = null;
        slide = null;

        emulator.clearTargetSlide();
        super.repaint();
    }
}
