package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.optionalAnchors.AnchorManager;
import com.scc210groupproject.structure.optionalAnchors.IAnchorProvider;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Dimension;
import java.awt.Component;

import java.io.IOException;

/**
 * @author wonge1
 * Use this as a reference (dont extend from this) for elements
 */
public class SampleElement extends BaseElement implements IAnchorProvider {

    private JPanel panel = new JPanel();

    private AnchorManager manager = new AnchorManager(
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

    //public static SampleElement createEmpty() { return new SampleElement(); }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        // writer.writeObject("manager", manager);

        // Point p = panel.getLocation();
        // writer.writeInt("x", p.x);
        // writer.writeInt("y", p.y);

        // Dimension d = panel.getSize();
        // writer.writeInt("width", d.width);
        // writer.writeInt("height", d.height);

        // writer.writeInt("background", panel.getBackground().getRGB());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        // panel = new JPanel();
        // panel.setLayout(null);

        // manager = (AnchorManager)reader.readObject("manager");

        // Point p = new Point();
        // p.setLocation(
        //     reader.readDouble("x"),
        //     reader.readDouble("y"));
        // panel.setLocation(p);

        // Dimension d = new Dimension();
        // d.setSize(
        //     reader.readDouble("width"),
        //     reader.readDouble("height"));
        // panel.setSize(d);

        // panel.setBackground(new Color(reader.readInt("background")));
    }

    // @Override
    // protected void processNewElement(BaseElement element) {
    //     panel.add(element.asComp());
    //     panel.validate();
    // }

    // @Override
    // protected void prepareRemoveElement(BaseElement element) {
    //     panel.remove(element.asComp());
    //     panel.validate();
    // }

    @Override
    public AnchorManager getAnchorManager() {
        return manager;
    }
}
