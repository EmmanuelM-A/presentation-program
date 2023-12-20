package com.scc210groupproject.structure;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wonge1
 * Use this as a reference (dont extend from this) for elements
 */
public class SampleElement extends BaseElement
{
    private transient JPanel panel = new JPanel();

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
    }

    @Override
    public void readSelf(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        panel = new JPanel();
        panel.setLayout(null);

        panel.setLocation(new Point(
            in.readInt(),
            in.readInt()));


        panel.setSize(new Dimension(
            in.readInt(),
            in.readInt()));

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

}
