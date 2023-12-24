package com.scc210groupproject.structure.optionalAnchors;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.scc210groupproject.structure.BaseElement;

public class AnchorManager implements Serializable {

    protected BaseElement element;
    protected ArrayList<AnchorReference> anchors;

    public AnchorManager(BaseElement ownerElement, Point2D.Double... relativeSpaceAnchors) {
        if (!(ownerElement instanceof IAnchorProvider))
            throw new IllegalArgumentException("ownerElement must implement IAnchorProvider");

        element = ownerElement;

        anchors = new ArrayList<AnchorReference>(relativeSpaceAnchors.length);
        for (int i = 0; i < relativeSpaceAnchors.length; i++)
            anchors.add(new AnchorReference(relativeSpaceAnchors[i], this));
    }

    public void onChangeSize() {
        for (AnchorReference anchor : anchors)
            anchor.onAnchorMoved();
    }

    public AnchorReference addAnchor(Point2D.Double newPosition) {
        AnchorReference addition = new AnchorReference(newPosition, this);
        anchors.add(addition);
        return addition;
    }

    public void replaceAnchor(AnchorReference anchor, Point2D.Double newPosition) {
        anchor.updateLocation(newPosition);;
        anchor.onAnchorMoved();
    }

    public void removeAnchor(AnchorReference anchor) {
        anchor.onAnchorDeleted();
        anchors.remove(anchor);
    }

    protected List<AnchorReference> getAnchors() {
        return Collections.unmodifiableList(anchors);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(element);
        out.writeObject(anchors);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        element = (BaseElement)in.readObject();
        anchors = (ArrayList<AnchorReference>)in.readObject();
    }
}
