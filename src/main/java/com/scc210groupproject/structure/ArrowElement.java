package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.adjust.IMultiMover;
import com.scc210groupproject.structure.adjust.MultiController;
import com.scc210groupproject.structure.anchors.AnchorReference;
import com.scc210groupproject.structure.anchors.IAnchorListener;
import com.scc210groupproject.structure.anchors.IAnchorProvider;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.structure.state.SnapshotManager;
import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.contextMenu.ArrowContextMenu;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.Component;
import java.awt.Cursor;
import java.io.IOException;
import java.util.Collection;

/**
 * @author wonge1
 * Element to draw arrow with varying size and style
 */
public class ArrowElement extends BaseElement implements IAnchorListener {
    public static enum Side { A, B }

    private ArrowPanel panel;

    private AnchorReference anchorA;
    private AnchorReference anchorB;

    public ArrowElement(Point start, Point end) {
        this();

        panel.pointA = start;
        panel.pointB = end;
        panel.reposition();
    }

    private ArrowElement() {
        panel = new ArrowPanel();
        addInputListener(new ArrowMover(this));
    }

    public void setPoint(Side side, Point newPosition) {
        switch (side) {
            case A:
                panel.pointA = newPosition;
                if (anchorA != null) {
                    anchorA.removeListener(this);
                    anchorA = null;
                }
                break;

            case B:
                panel.pointB = newPosition;
                if (anchorB != null) {
                    anchorB.removeListener(this);
                    anchorB = null;
                }
                break;
        }

        panel.reposition();
    }

    public Point getPoint(Side side) {
        switch (side) {
            case A:
                return panel.pointA;

            case B:
                return panel.pointB;

            default:
                return null;
        }

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

    public static ArrowElement createEmpty() { return new ArrowElement(); }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        writer.writeObject("anchor A", anchorA);
        writer.writeObject("anchor B", anchorB);

        writer.writeInt("point A X", panel.pointA.x);
        writer.writeInt("point A Y", panel.pointA.y);

        writer.writeInt("point B X", panel.pointB.x);
        writer.writeInt("point B Y", panel.pointB.y);

        writer.writeBoolean("arrow on A", panel.arrowOnA);
        writer.writeDouble("arrow Width A", panel.arrowWidthA);
        writer.writeDouble("arrow Length A", panel.arrowLengthA);

        writer.writeBoolean("arrow on B", panel.arrowOnB);
        writer.writeDouble("arrow Width B", panel.arrowWidthB);
        writer.writeDouble("arrow Length B", panel.arrowLengthB);

        writer.writeBoolean("line Solid", panel.lineSolid);
        writer.writeFloat("line Dash Length", panel.lineDashLength);
        writer.writeFloat("line Width", panel.lineWidth);

        writer.writeInt("color", panel.color.getRGB());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {

        anchorA = (AnchorReference)reader.readObject("anchor A");
        anchorB = (AnchorReference)reader.readObject("anchor B");
         
        Point pointA = new Point();
        pointA.setLocation(reader.readInt("point A X"), reader.readInt("point A Y"));
        panel.pointA = pointA;

        Point pointB = new Point();
        pointB.setLocation(reader.readInt("point B X"), reader.readInt("point B Y"));
        panel.pointB = pointB;

        panel.arrowOnA = reader.readBoolean("arrow on A");
        panel.arrowWidthA = reader.readDouble("arrow Width A");
        panel.arrowLengthA = reader.readDouble("arrow Length A");

        panel.arrowOnB = reader.readBoolean("arrow on B");
        panel.arrowWidthB = reader.readDouble("arrow Width B");
        panel.arrowLengthB = reader.readDouble("arrow Length B");

        panel.lineSolid = reader.readBoolean("line Solid");
        panel.lineDashLength = reader.readFloat("line Dash Length");
        panel.lineWidth = reader.readFloat("line Width");

        panel.color = new Color(reader.readInt("color"));

        panel.reposition();
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
        private Polygon triangle;
        private Polygon box;
        private AffineTransform transform;

        private Shape triangleA = null;
        private Shape triangleB = null;
        private Shape boxLine = null;
        private Shape circle = null;

        protected Point pointA = new Point();
        protected Point pointB = new Point();

        protected boolean highlightA = false;
        protected boolean arrowOnA = true;
        protected double arrowWidthA = 10.0;
        protected double arrowLengthA = 10.0;

        protected boolean highlightB = false;
        protected boolean arrowOnB = true;
        protected double arrowWidthB = 10.0;
        protected double arrowLengthB = 10.0;

        protected boolean lineSolid = false;
        protected float lineDashLength = 10.0f;
        protected float lineWidth = 5f;

        protected Color color = Color.BLACK;

        private ArrowPanel() {
            super();

            triangle = new Polygon();
            triangle.addPoint(0, 1);
            triangle.addPoint(-1, 0);
            triangle.addPoint(1, 0);

            box = new Polygon();
            box.addPoint(-1, 0);
            box.addPoint(1, 0);
            box.addPoint(1, 1);
            box.addPoint(-1, 1);
            
            circle = new Ellipse2D.Double(1.0, 1.0, 1.0, 1.0);

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
        public boolean contains(int x, int y) {
            if (triangleA != null && triangleA.contains(x, y))
                return true;

            if (triangleB != null && triangleB.contains(x, y))
                return true;

            if (boxLine != null && boxLine.contains(x, y))
                return true;

            return false;
        }

        private Shape getTransformed(Shape original, double tx, double ty, double r, double sx, double sy) {
            transform.setToScale(sx, sy);
            Shape scaled = transform.createTransformedShape(original);
            transform.setToRotation(r);
            Shape rotated = transform.createTransformedShape(scaled);
            transform.setToTranslation(tx, ty);
            return transform.createTransformedShape(rotated);
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

            double xA, yA;
            if (arrowOnA) {
                xA = xLocalA + xNormal * arrowLengthA;
                yA = yLocalA + yNormal * arrowLengthA;
                length -= arrowLengthA;
            }
            else {
                xA = xLocalA;
                yA = yLocalA;
            }

            double xB, yB;
            if (arrowOnB) {
                xB = xLocalB - xNormal * arrowLengthB;
                yB = yLocalB - yNormal * arrowLengthB;
                length -= arrowLengthB;
            }
            else {
                xB = xLocalB;
                yB = yLocalB;
            }

            Graphics2D g2d = (Graphics2D)g.create();

            g2d.setColor(color);

            if (arrowOnA)
            {
                triangleA = getTransformed(triangle, xA, yA, radian + Math.PI, arrowWidthA / 2.0, arrowLengthA);

                g2d.fill(triangleA);
            }
            else
                triangleA = null;

            if (arrowOnB)
            {
                triangleB = getTransformed(triangle, xB, yB, radian, arrowWidthB / 2.0, arrowLengthB);

                g2d.fill(triangleB);
            }
            else
                triangleB = null;

            {
                BasicStroke stroke = lineSolid ?
                    new BasicStroke((float)lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0) :
                    new BasicStroke((float)lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{ lineDashLength }, 0);
                Line2D line = new Line2D.Double(xA, yA, xB, yB);

                boxLine = getTransformed(box, xA, yA, radian, lineWidth / 2.0, length);

                g2d.setStroke(stroke);
                g2d.draw(line);
            }

            {
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke((float)2.0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0));

                if (highlightA) {
                    if (triangleA != null)
                        g2d.draw(triangleA);
                    else {
                        Shape transformed = getTransformed(circle, xA, yA, 0.0, 10.0, 10.0);
                        g2d.draw(transformed);
                    }
                }
                
                if (highlightB) {
                    if (triangleB != null)
                        g2d.draw(triangleB);
                    else {
                        Shape transformed = getTransformed(circle, xB, yB, 0.0, 10.0, 10.0);
                        g2d.draw(transformed);
                    }
                }
            }

            g2d.dispose();
        }
    }

    public static class ArrowMover implements  IMousePressed, IMouseReleased, IMouseMoved, IMouseDragged, IMouseEntered, IMouseExited, IMouseClicked, IMultiMover {

        private ArrowElement element;
        private boolean targetSideA = false;
        private boolean targetSideB = false;
        private double snapDistance = 10;

        private boolean saveIfMove = false;

        public ArrowMover(ArrowElement element) {
            this.element = element;
        }

        private BaseElement getAnchorProvider(ArrowElement arrow, Point location) {
            Collection<BaseElement> hierarchy = arrow.parent.getImmediateHierarchy(location);

            for (BaseElement element : hierarchy) {
                if (element == arrow)
                    continue;

                if (!IAnchorProvider.class.isAssignableFrom(element.getClass()))
                    continue;

                return element;
            }
            
            return null;
        }
        
        @Override
        public void mouseExited(Object target, InputState state) {
            MainDisplayPanel.instance.setCursor(Cursor.getDefaultCursor());
            MultiController.evaluateAll(state.isShiftDown());
        }

        @Override
        public void mouseDragged(Object target, InputState state) {
            MultiController.moveAll(state);
        }

        @Override
        public void mouseMoved(Object target, InputState state) {
            MainDisplayPanel.instance.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }

        @Override
        public void mousePressed(Object target, InputState state) {
            if (!state.isShiftDown() && !MultiController.contains(this))
                MultiController.clearAll();
            MultiController.add(this);

            Point mouseLocation = state.getLocationInSlide();

            ArrowElement arrow = (ArrowElement)target;
            double distanceA = mouseLocation.distance(arrow.getPoint(Side.A));
            double distanceB = mouseLocation.distance(arrow.getPoint(Side.B));

            if (!state.isShiftDown()) {
                targetSideA = false;
                targetSideB = false;
            }

            if (distanceA < distanceB) {
                targetSideA = true;
                arrow.panel.highlightA = true;
            }
            else {
                targetSideB = true;
                arrow.panel.highlightB = true;
            }
            
            arrow.panel.repaint();
            arrow.notifyUpdate(arrow);

            ContextMenuPanel.setMenu(new ArrowContextMenu());
        }

        @Override
        public void move(InputState state) {
            if (saveIfMove) {
                SnapshotManager.saveState();
                saveIfMove = false;
            }

            if (targetSideA && targetSideB || MultiController.hasMultiple())
                moveBoth(state);
            else if (targetSideA)
                moveSide(state, Side.A);
            else if (targetSideB)
                moveSide(state, Side.B);

            element.notifyUpdate(element);
        }

        private void moveBoth(InputState state) {
            Dimension delta = state.getMouseDelta();

            if (element.anchorA == null && targetSideA) {
                Point original = element.getPoint(Side.A);
                element.setPoint(Side.A, new Point(
                    original.x + delta.width,
                    original.y + delta.height
                ));
            }
            
            if (element.anchorB == null && targetSideB) {
                Point original = element.getPoint(Side.B);
                element.setPoint(Side.B, new Point(
                    original.x + delta.width,
                    original.y + delta.height
                ));
            }
        }

        private void moveSide(InputState state, Side side) {

            Point location = state.getLocationInSlide();
            BaseElement target = getAnchorProvider(element, location);
            if (target == null)
                element.setPoint(side, location);
            else {
                IAnchorProvider provider = (IAnchorProvider)target;

                AnchorReference found = null;
                double minDistance = Double.MAX_VALUE;

                for (AnchorReference anchor : provider.getAnchors()) {
                    double distance = location.distance(anchor.getCoordInSlide());
                    if (distance < minDistance) {
                        found = anchor;
                        minDistance = distance;
                    }
                }

                if (found == null)
                    element.setPoint(side, location);

                if (minDistance * MainDisplayPanel.instance.getInputEmulator().getScale() > snapDistance)
                    element.setPoint(side, location);

                element.setAnchor(side, found);
            }
        }

        @Override
        public void setSaveIfMove(boolean state) {
            saveIfMove = state;
        }

        @Override
        public void mouseClicked(Object target, InputState state) {
            // not used, here to block message being taken by another element
        }

        @Override
        public void mouseEntered(Object target, InputState state) {
            // not used, here to block message being taken by another element
        }

        @Override
        public void mouseReleased(Object target, InputState state) {
            // not used, here to block message being taken by another element
        }

        @Override
        public void evaluateState(boolean inSelectionStage) {

            if (!MultiController.contains(this)) {
                element.panel.highlightA = false;
                element.panel.highlightB = false;
                element.notifyUpdate(element);
            }
            else if (!MultiController.hasMultiple() && !inSelectionStage) {
                element.panel.highlightA = false;
                element.panel.highlightB = false;
                element.notifyUpdate(element);
            }

        }
    }
}
