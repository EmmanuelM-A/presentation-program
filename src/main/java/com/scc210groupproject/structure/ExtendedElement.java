package com.scc210groupproject.structure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.border.Border;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.adjust.DragResizer;
import com.scc210groupproject.structure.adjust.IResizable;
import com.scc210groupproject.structure.anchors.AnchorManager;
import com.scc210groupproject.structure.anchors.IAnchorProvider;
import com.scc210groupproject.ui.presentations.PresentationActions;

public abstract class ExtendedElement extends BaseElement implements IResizable, IAnchorProvider {

    private PresentationActions animation = null;

    public PresentationActions getAnimation() {
        return this.animation;
    }

    public boolean hasAnimation() {
        if(this.animation == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setAnimation(PresentationActions newAnimation) {
        this.animation = newAnimation;
    }

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
        addInputListener(new DragResizer(this));
    }

    @Override
    public AnchorManager getAnchorManager() {
        return manager;
    }
    
    protected void writeSelfExtended(Writer writer) throws IOException {
        writer.writeObject("manager", manager);
        
        Point p = asComp().getLocation();
        writer.writeInt("x", p.x);
        writer.writeInt("y", p.y);

        Dimension d = asComp().getSize();
        writer.writeInt("width", d.width);
        writer.writeInt("height", d.height);

        writer.writeInt("background", asComp().getBackground().getRGB());
    }

    public void readSelfExtended(Reader reader) throws IOException {
        manager = (AnchorManager)reader.readObject("manager");

        Point p = new Point();
        p.setLocation(
            reader.readDouble("x"),
            reader.readDouble("y"));
        asComp().setLocation(p);

        Dimension d = new Dimension();
        d.setSize(
            reader.readDouble("width"),
            reader.readDouble("height"));
        asComp().setSize(d);

        asComp().setBackground(new Color(reader.readInt("background")));
    }
}
