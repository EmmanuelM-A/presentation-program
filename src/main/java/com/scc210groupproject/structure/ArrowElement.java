package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.structure.optionalAnchors.IAnchorListener;
import com.scc210groupproject.structure.optionalAnchors.AnchorReference;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wonge1
 * Element to draw arrow with varying size and style
 */
public class ArrowElement extends BaseElement implements IAnchorListener {
    public static enum Side { A, B }

    private transient ArrowPanel panel;

    private transient AnchorReference anchorA;
    private transient AnchorReference anchorB;

    public ArrowElement(Point start, Point end) {
        this();

        panel.pointA = start;
        panel.pointB = end;
        panel.reposition();
    }

    private ArrowElement() {
        panel = new ArrowPanel();
    }

    public void setPoint(Side side, Point newPoisition) {
        switch (side) {
            case A:
                panel.pointA = newPoisition;
                if (anchorA != null) {
                    anchorA.removeListener(this);
                    anchorA = null;
                }
                break;

            case B:
                panel.pointB = newPoisition;
                if (anchorB != null) {
                    anchorB.removeListener(this);
                    anchorB = null;
                }
                break;
        }

        panel.reposition();
    }

    public void toggleArrow(Side side, boolean enabled) {
        switch (side) {
            case A:
                panel.arrowOnA = enabled;
                break;

            case B:
                panel.arrowOnB = enabled;
                break;
        }
    }

    public void setArrow(Side side, boolean enabled, double arrowWidth, double arrowLength) {
        toggleArrow(side, enabled);

        switch (side) {
            case A:
                panel.arrowWidthA = arrowWidth;
                panel.arrowLengthA = arrowLength;
                break;

            case B:
                panel.arrowWidthB = arrowWidth;
                panel.arrowLengthB = arrowLength;
                break;
        }

        panel.reposition();
    }

    public void setLine(boolean isSolid, float width, float dotLength) {
        panel.lineSolid = isSolid;
        panel.lineWidth = width;
        panel.lineDashLength = dotLength;

        panel.reposition();
    }

    public void setColor(Color color) {
        panel.color = color;
    }

    public void setAnchor(Side side, AnchorReference newAnchor) {
        switch (side) {
            case A:
                if (anchorA != null)
                    anchorA.removeListener(this);
                anchorA = newAnchor;
                if (anchorA != null) {
                    panel.pointA = anchorA.getCoordInSlide();
                    panel.reposition();
                    anchorA.addListener(this);
                }
                break;

            case B:
                if (anchorB != null)
                    anchorB.removeListener(this);
                anchorB = newAnchor;
                if (anchorB != null) {
                    panel.pointB = anchorB.getCoordInSlide();
                    panel.reposition();
                    anchorB.addListener(this);
                }
                break;
        }
    }

    @Override
    public Component asComp() { return panel; }

    @Override
    public void writeSelf(ObjectOutputStream out) throws IOException {

        out.writeObject(anchorA);
        out.writeObject(anchorB);

        out.writeInt(panel.pointA.x);
        out.writeInt(panel.pointA.y);

        out.writeInt(panel.pointB.x);
        out.writeInt(panel.pointB.y);

        out.writeBoolean(panel.arrowOnA);
        out.writeDouble(panel.arrowWidthA);
        out.writeDouble(panel.arrowLengthA);

        out.writeBoolean(panel.arrowOnB);
        out.writeDouble(panel.arrowWidthB);
        out.writeDouble(panel.arrowLengthB);

        out.writeBoolean(panel.lineSolid);
        out.writeFloat(panel.lineDashLength);
        out.writeFloat(panel.lineWidth);

        out.writeInt(panel.color.getRGB());
    }

    @Override
    public void readSelf(ObjectInputStream in) throws IOException, ClassNotFoundException {
        panel = new ArrowPanel();

        anchorA = (AnchorReference)in.readObject();
        anchorB = (AnchorReference)in.readObject();

        Point pointA = new Point();
        pointA.setLocation(in.readInt(), in.readInt());
        panel.pointA = pointA;

        Point pointB = new Point();
        pointB.setLocation(in.readInt(), in.readInt());
        panel.pointB = pointB;

        panel.arrowOnA = in.readBoolean();
        panel.arrowWidthA = in.readDouble();
        panel.arrowLengthA = in.readDouble();

        panel.arrowOnB = in.readBoolean();
        panel.arrowWidthB = in.readDouble();
        panel.arrowLengthB = in.readDouble();

        panel.lineSolid = in.readBoolean();
        panel.lineDashLength = in.readFloat();
        panel.lineWidth = in.readFloat();

        panel.color = new Color(in.readInt());

        panel.reposition();
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
    public void onAnchorMoved(AnchorReference anchor) {
        if (anchor == anchorA)
            panel.pointA = anchor.getCoordInSlide();
        else if (anchor == anchorB)
            panel.pointB = anchor.getCoordInSlide();

        panel.reposition();
    }

    @Override
    public void onAnchorDeleted(AnchorReference anchor) {
        if (anchor == anchorA)
            anchorA = null;
        else if (anchor == anchorB)
            anchorB = null;
    }

    public static class ArrowPanel extends JPanel {
        private transient Polygon triangle;
        private transient AffineTransform transform;

        protected transient Point pointA = new Point();
        protected transient Point pointB = new Point();

        protected transient boolean arrowOnA = true;
        protected transient double arrowWidthA = 10.0;
        protected transient double arrowLengthA = 10.0;

        protected transient boolean arrowOnB = true;
        protected transient double arrowWidthB = 10.0;
        protected transient double arrowLengthB = 10.0;

        protected transient boolean lineSolid = false;
        protected transient float lineDashLength = 10.0f;
        protected transient float lineWidth = 5f;

        protected transient Color color = Color.BLACK;

        private ArrowPanel() {
            super();

            triangle = new Polygon();
            triangle.addPoint(0, 1);
            triangle.addPoint(-1, 0);
            triangle.addPoint(1, 0);

            transform = new AffineTransform();

            super.setLayout(null);
            super.setOpaque(false);
        }

        private void reposition() {
            double offset = Math.max(Math.max(arrowWidthA / 2.0, arrowWidthB / 2.0), (double)lineWidth);

            Point anchor = new Point();
            anchor.setLocation(
                Math.min(pointA.getX(), pointB.getX()) - offset,
                Math.min(pointA.getY(), pointB.getY()) - offset);

            Dimension dimension = new Dimension();
            dimension.setSize(
                Math.max(pointA.getX(), pointB.getX()) + offset - anchor.getX(),
                Math.max(pointA.getY(), pointB.getY()) + offset - anchor.getY());

            super.setLocation(anchor);
            super.setSize(dimension);

            super.repaint();
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Point anchor = super.getLocation();

            double xLocalA = pointA.x - anchor.x;
            double xLocalB = pointB.x - anchor.x;

            double yLocalA = pointA.y - anchor.y;
            double yLocalB = pointB.y - anchor.y;

            double xOffset = xLocalB - xLocalA;
            double yOffset = yLocalB - yLocalA;

            double length = Math.sqrt(xOffset * xOffset + yOffset * yOffset);
            double radian = Math.atan2(yOffset, xOffset) - Math.PI / 2.0;

            double xNormal = xOffset / length;
            double yNormal = yOffset / length;

            double xA = arrowOnA ?
                xLocalA + xNormal * arrowLengthA :
                xLocalA;
            double yA = arrowOnA ?
                yLocalA + yNormal * arrowLengthA :
                yLocalA;

            double xB = arrowOnB ?
                xLocalB - xNormal * arrowLengthB :
                xLocalB;
            double yB = arrowOnB ?
                yLocalB - yNormal * arrowLengthB :
                yLocalB;

            Graphics2D g2d = (Graphics2D)g.create();

            g2d.setColor(color);

            if (arrowOnA)
            {
                transform.setToScale(arrowWidthA / 2.0, arrowLengthA);
                Shape scaled = transform.createTransformedShape(triangle);
                transform.setToRotation(radian + Math.PI);
                Shape rotated = transform.createTransformedShape(scaled);
                transform.setToTranslation(xA, yA);
                Shape arrow = transform.createTransformedShape(rotated);

                g2d.fill(arrow);
            }

            if (arrowOnB)
            {
                transform.setToScale(arrowWidthB / 2.0, arrowLengthB);
                Shape scaled = transform.createTransformedShape(triangle);
                transform.setToRotation(radian);
                Shape rotated = transform.createTransformedShape(scaled);
                transform.setToTranslation(xB, yB);
                Shape arrow = transform.createTransformedShape(rotated);

                g2d.fill(arrow);
            }

            {
                BasicStroke stroke = lineSolid ?
                    new BasicStroke((float)lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0) :
                    new BasicStroke((float)lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{ lineDashLength }, 0);
                Line2D line = new Line2D.Double(xA, yA, xB, yB);

                g2d.setStroke(stroke);
                g2d.draw(line);
            }

            g2d.dispose();
        }
    }

}
