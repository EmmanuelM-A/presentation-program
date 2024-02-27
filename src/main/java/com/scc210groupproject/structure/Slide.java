package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.ui.presentations.PresentationActions;
import com.scc210groupproject.ui.presentations.transistions.Transition;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wonge1
 * Uses the implementation of the BaseElement class
 * Serializable as result of inheriting BaseElement
 */
public class Slide extends BaseElement
{
    private JPanel panel;

    /**
     * Represent the current transition applied to this slide
     */
    private Transition transition;

    /**
     * Get the transition that has been applied to this slide
     * @return Transition
     */
    public Transition getTransition() {
        return this.transition;
    }

    /**
     * Sets the slide transition to a new value
     * @param newTransition The new transition for this slide 
     */
    public void setTransition(Transition newTransition) {
        this.transition = newTransition;
    }

    private List<PresentationActions> actions;

    public List<PresentationActions> getActions() {
        return this.actions;
    }

    public Slide(Dimension dimension) {
        this();

        setDimension(dimension);
    }

    Slide() {
        panel = new JPanel();
        panel.setEnabled(true);
        panel.setLayout(null);
        actions = new LinkedList<>();
        transition = null;
    }

    public void setDimension(Dimension dimension) {
        panel.setSize(dimension);
        panel.setPreferredSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setMaximumSize(dimension);
    }

    public void setBackground(Color color) {
        panel.setBackground(color);
    }

    @Override
    public Component asComp() { return panel; }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        Point p = panel.getLocation();
        writer.writeInt("x", p.x);
        writer.writeInt("y", p.y);

        Dimension d = panel.getSize();
        writer.writeInt("width", d.width);
        writer.writeInt("height", d.height);

        writer.writeInt("background", panel.getBackground().getRGB());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        panel.setLocation(new Point(
            reader.readInt("x"),
            reader.readInt("y")));

        Dimension dimension = new Dimension(
            reader.readInt("width"),
            reader.readInt("height"));

        setDimension(dimension);

        panel.setBackground(new Color(reader.readInt("background")));
    }

    public static Slide createEmpty() { return new Slide(); }

    /**
     * Create an image of the Slide
     * @return Graphic2D of Slide
     */
    public BufferedImage createPreview(Dimension size) {
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
