package com.scc210groupproject.structure.optionalAnchors;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.readwrite.IJsonSerializable;
import com.scc210groupproject.structure.CoordinateUtils;

public class AnchorReference implements IJsonSerializable {
    private Point2D.Double location;
    private List<IAnchorListener> listeners;

    private AnchorManager manager;

    protected AnchorReference(Point2D.Double location, AnchorManager manager) {
        listeners = new LinkedList<>();
        this.location = location;
        this.manager = manager;
    }

    private AnchorReference() {}

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

        out.writeInt(listeners.size());
        for (IAnchorListener listener : listeners)
            out.writeObject(listener);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        location = new Point2D.Double(in.readDouble(), in.readDouble());

        manager = (AnchorManager)in.readObject();

        int size = in.readInt();
        listeners = new ArrayList<IAnchorListener>(size);
        for (int i = 0; i < size; i++) {
            listeners.add((IAnchorListener)in.readObject());
        }
    }

    public static AnchorReference createEmpty() { return new AnchorReference(); }

    @Override
    public void writeValue(Writer writer) throws IOException {
        writer.writeDouble("x", location.x);
        writer.writeDouble("y", location.y);

        writer.writeObject("manager", manager);
        if (listeners.size() > 0)
            writer.writeObjectList("listeners", listeners);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readValue(Reader reader) throws IOException {
        location = new Point2D.Double(
            reader.readDouble("x"),
            reader.readDouble("y"));

        manager = (AnchorManager)reader.readObject("manager");
        listeners = reader.hasField("listeners") ?
            (List<IAnchorListener>)reader.readObjectList("listeners") :
            new LinkedList<>();
    }
}
