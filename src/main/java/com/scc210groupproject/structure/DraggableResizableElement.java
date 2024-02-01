package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.anchors.AnchorManager;
import com.scc210groupproject.structure.anchors.IAnchorProvider;

@java.lang.Deprecated
public class DraggableResizableElement extends BaseElement implements IAnchorProvider, IResizable
{
    protected JPanel panel;

    public DraggableResizableElement()
    {
        super();

        panel = new JPanel(new BorderLayout());
        panel.setSize(400, 400);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setOpaque(false);

        super.addInputListener(new DragResizer());
    }

    private AnchorManager manager = new AnchorManager(
        this,
        new Point2D.Double(0.0, 0.5),
        new Point2D.Double(0.5, 1.0),
        new Point2D.Double(1.0, 0.5),
        new Point2D.Double(0.5, 0.0)
    );

    @Override
    public void setLocation(Point p)
    {
        panel.setLocation(p);
        super.notifyUpdate(this);
        manager.onChangeSize();
    }

    @Override
    public void setSize(Dimension d)
    {
        panel.setSize(d);
        super.notifyUpdate(this);
        manager.onChangeSize();
    }

    public void setBackground(Color color)
    {
        panel.setBackground(color);
    }

    @Override
    protected void writeSelf(Writer writer) throws IOException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeSelf'");
    }

    @Override
    protected void readSelf(Reader reader) throws IOException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSelf'");
    }

    @Override
    public Component asComp()
    {
        return panel;
    }

    @Override
    public AnchorManager getAnchorManager() {
        return manager;
    }

    @Override
    public Point getLocation() {
        return panel.getLocation();
    }

    @Override
    public Dimension getSize() {
        return panel.getSize();
    }

    @Override
    public void setBorder(Border b) {
        panel.setBorder(b);
        super.notifyUpdate(this);
    }

    @Override
    public BaseElement asBaseElement() {
        return this;
    }
}
