package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.structure.optionalAnchors.AnchorManager;
import com.scc210groupproject.structure.optionalAnchors.IAnchorProvider;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Dimension;
import java.awt.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wonge1
 * Use this as a reference (dont extend from this) for elements
 */
public class SampleElement extends BaseElement implements IAnchorProvider {

    private transient JPanel panel = new JPanel();

    private transient AnchorManager manager = new AnchorManager(
        this,
        new Point2D.Double(0.0, 0.5),
        new Point2D.Double(0.5, 1.0),
        new Point2D.Double(1.0, 0.5),
        new Point2D.Double(0.5, 0.0)
    );

    public void setLocation(Point p) {
        panel.setLocation(p);
        manager.onChangeSize();
    }

    public void setSize(Dimension d) {
        panel.setSize(d);
        manager.onChangeSize();
    }

    public void setBackground(Color color) {
        panel.setBackground(color);
    }

    @Override
    public Component asComp() { return panel; }

    @Override
    public void writeSelf(ObjectOutputStream out) throws IOException {
        out.writeObject(manager);

        Point p = panel.getLocation();
        out.writeDouble(p.getX());
        out.writeDouble(p.getY());

        Dimension d = panel.getSize();
        out.writeDouble(d.getWidth());
        out.writeDouble(d.getHeight());

        out.writeInt(panel.getBackground().getRGB());
    }

    @Override
    public void readSelf(ObjectInputStream in) throws IOException, ClassNotFoundException {
        panel = new JPanel();
        panel.setLayout(null);

        manager = (AnchorManager)in.readObject();

        Point p = new Point();
        p.setLocation(
            in.readDouble(),
            in.readDouble());
        panel.setLocation(p);

        Dimension d = new Dimension();
        d.setSize(
            in.readDouble(),
            in.readDouble());
        panel.setSize(d);

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

    @Override
    public AnchorManager getManager() {
        return manager;
    }
}
