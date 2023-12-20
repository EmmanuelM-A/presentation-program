package com.scc210groupproject.structure;

import javax.swing.JPanel;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wonge1
 * Uses the implementation of the BaseElement class
 * Serializable as result of inheriting BaseElement
 */
public class Slide extends BaseElement
{
    private transient JPanel panel  = new JPanel();

    @Override
    public Component asComp() { return panel; }

    @Override
    public void writeSelf(ObjectOutputStream out) throws IOException
    {
        Point p = panel.getLocation();
        out.writeInt(p.x);
        out.writeInt(p.y);

        Dimension d = panel.getSize();
        out.writeInt(d.width);
        out.writeInt(d.height);

        out.writeInt(panel.getBackground().getRGB());
    }

    @Override
    public void readSelf(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        panel = new JPanel();
        panel.setLayout(null);

        panel.setLocation(new Point(
            in.readInt(),
            in.readInt()));

        Dimension d = new Dimension(
            in.readInt(),
            in.readInt());

        panel.setSize(d);
        panel.setPreferredSize(d);
        panel.setMinimumSize(d);
        panel.setMaximumSize(d);

        panel.setBackground(new Color(in.readInt()));
    }

    @Override
    protected void processNewElement(BaseElement element) {
        panel.add(element.asComp());
        panel.validate();
    }

    @Override
    protected void prepareRemoveElement(BaseElement element) {
        panel.remove(element.asComp());
        panel.validate();
    }

    /**
     * Create an image of the Slide
     * @return Graphic2D of Slide
     */
    public BufferedImage createPreview(Dimension size)
    {
        BufferedImage original = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = original.createGraphics();
        panel.paint(graphics);
        panel.paintComponents(graphics);


        BufferedImage scaled = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        AffineTransform transform = new AffineTransform();
        transform.scale((double)size.width / (double)panel.getWidth(), (double)size.height / (double)panel.getHeight());
        AffineTransformOp operation = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

        scaled = operation.filter(original, scaled);
        return scaled;
    }
}
