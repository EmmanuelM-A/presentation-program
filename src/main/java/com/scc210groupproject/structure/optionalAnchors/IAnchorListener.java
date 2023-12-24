package com.scc210groupproject.structure.optionalAnchors;

public interface IAnchorListener {
    public void onAnchorMoved(AnchorReference anchor);
    public void onAnchorDeleted(AnchorReference anchor);
}
