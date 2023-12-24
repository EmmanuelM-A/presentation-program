package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.structure.optionalAnchors.AnchorReference;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wonge1
 * Uses the implementation of the BaseElement class
 * Serializable as result of inheriting BaseElement
 */
public class Slide extends BaseElement
{
    private transient JPanel panel;

    public Slide(Dimension dimension)
    {
        this();

        setDiemension(dimension);
    }

    private Slide()
    {
        panel = new JPanel();
        panel.setLayout(null);
    }

    public void setDiemension(Dimension dimension)
    {
        panel.setSize(dimension);
        panel.setPreferredSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setMaximumSize(dimension);
    }

    public void setBackground(Color color)
    {
        panel.setBackground(color);
    }

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

        out.writeInt(updateListeners.size());
        for (IUpdateListener listener : updateListeners)
            out.writeObject(listener);
    }

    @Override
    public void readSelf(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        panel = new JPanel();
        panel.setLayout(null);

        panel.setLocation(new Point(
            in.readInt(),
            in.readInt()));

        Dimension dimension = new Dimension(
            in.readInt(),
            in.readInt());

        setDiemension(dimension);

        panel.setBackground(new Color(in.readInt()));

        int size = in.readInt();
        updateListeners = new ArrayList<IUpdateListener>(size);
        for (int i = 0; i < size; i++) {
            updateListeners.add((IUpdateListener)in.readObject());
        }
    }

    @Override
    protected void processNewElement(BaseElement element) {
        panel.add(element.asComp());

        Container container = panel.getParent();
        if (container != null) {
            container.revalidate();
            container.repaint();
        }
        else {
            panel.revalidate();
            panel.repaint();
        }
    }

    @Override
    protected void prepareRemoveElement(BaseElement element) {
        panel.remove(element.asComp());

        Container container = panel.getParent();
        if (container != null) {
            container.revalidate();
            container.repaint();
        }
        else {
            panel.revalidate();
            panel.repaint();
        }
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

    /**
     * Listner for when a slide is changed
     */
    private transient List<IUpdateListener> updateListeners = new ArrayList<>();
    public void addUpdateListener(IUpdateListener listener) { updateListeners.add(listener); }
    public void removeUpdateListener(IUpdateListener listener) { updateListeners.remove(listener); }

    @Override
    protected void notifyUpdate() {
        for (IUpdateListener listener : updateListeners)
            listener.onUpdate(this);
    }

    public interface IUpdateListener {
        public void onUpdate(Slide slide);
    }
}
