package com.scc210groupproject.structure.anchors;

public interface IAnchorListener {
    public void onAnchorMoved(AnchorReference anchor);
    public void onAnchorDeleted(AnchorReference anchor);
}
