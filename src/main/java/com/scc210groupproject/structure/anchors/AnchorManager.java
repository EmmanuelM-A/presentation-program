package com.scc210groupproject.structure.anchors;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.readwrite.IJsonSerializable;
import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.liveness.IDestroyListener;

public class AnchorManager implements IJsonSerializable, IDestroyListener {

    protected BaseElement element;
    protected List<AnchorReference> anchors;

    public AnchorManager(BaseElement ownerElement, Point2D.Double... relativeSpaceAnchors) {
        if (!(ownerElement instanceof IAnchorProvider))
            throw new IllegalArgumentException("ownerElement must implement IAnchorProvider");

        element = ownerElement;
        element.addDestroyListener(this);

        anchors = new ArrayList<AnchorReference>(relativeSpaceAnchors.length);
        for (int i = 0; i < relativeSpaceAnchors.length; i++)
            anchors.add(new AnchorReference(relativeSpaceAnchors[i], this));
    }

    private AnchorManager() {}

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

    public static AnchorManager createEmpty() { return new AnchorManager(); }

    @Override
    public void writeValue(Writer writer) throws IOException {
        writer.writeObject("element", element);
        writer.writeObjectList("anchors", anchors);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readValue(Reader reader) throws IOException {
        element = (BaseElement)reader.readObject("element");
        anchors = (List<AnchorReference>)reader.readObjectList("anchors");
    }

    @Override
    public void onDestroy(Object object) {
        for (AnchorReference anchor : anchors)
            anchor.onAnchorDeleted();

        element = null;
        anchors = null;
    }
}
