package com.scc210groupproject.structure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.border.Border;

import com.scc210groupproject.structure.anchors.AnchorManager;
import com.scc210groupproject.structure.anchors.IAnchorProvider;

public abstract class ExtendedElement extends BaseElement implements IResizable, IAnchorProvider {

    protected AnchorManager manager = new AnchorManager(
        this,
        new Point2D.Double(0.0, 0.5),
        new Point2D.Double(0.5, 1.0),
        new Point2D.Double(1.0, 0.5),
        new Point2D.Double(0.5, 0.0)
    );

    @Override
    public Point getLocation() {
        return asComp().getLocation();
    }

    @Override
    public Dimension getSize() {
        return asComp().getSize();
    }

    public Color getBackground() {
        return asComp().getBackground();
    }

    @Override
    public void setLocation(Point p) {
        asComp().setLocation(p);
        manager.onChangeSize();
        super.notifyUpdate(this);
    }

    @Override
    public void setSize(Dimension d) {
        asComp().setSize(d);
        manager.onChangeSize();
        super.notifyUpdate(this);
    }

    @Override
    public void setBorder(Border b) {
        ((JComponent)asComp()).setBorder(b);
        super.notifyUpdate(this);
    }

    public void setBackground(Color color) {
        asComp().setBackground(color);
        super.notifyUpdate(this);
    }

    @Override
    public BaseElement asBaseElement() {
        return this;
    }

    public ExtendedElement() {
        addMouseListener(new DragResizer());
    }

    @Override
    public AnchorManager getAnchorManager() {
        return manager;
    }
}
