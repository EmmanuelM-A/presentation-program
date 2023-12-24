package com.scc210groupproject.structure.optionalAnchors;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.scc210groupproject.structure.CoordinateUtils;

public class AnchorReference implements Serializable {
    private transient Point2D.Double location;
    private transient ArrayList<IAnchorListener> listeners;

    private transient AnchorManager manager;

    protected AnchorReference(Point2D.Double location, AnchorManager manager) {
        this();
        this.location = location;
        this.manager = manager;
    }

    private AnchorReference() {
        listeners = new ArrayList<>();
    }

    public Point getCoordInSlide() {
        return CoordinateUtils.convertRelativeToSlideSpace(location, manager.element);
    }

    public void addListener(IAnchorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IAnchorListener listener) {
        listeners.remove(listener);
    }

    protected void updateLocation(Point2D.Double location) {
        this.location = location;
    }

    protected void onAnchorMoved() {
        for (IAnchorListener listener : listeners)
            listener.onAnchorMoved(this);
    }

    protected void onAnchorDeleted() {
        for (IAnchorListener listener : listeners)
            listener.onAnchorDeleted(this);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeDouble(location.x);
        out.writeDouble(location.y);
        out.writeObject(manager);
        out.writeObject(listeners);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        location = new Point2D.Double(in.readDouble(), in.readDouble());
        manager = (AnchorManager)in.readObject();
        listeners = (ArrayList<IAnchorListener>)in.readObject();
    }
}
