package com.scc210groupproject.structure.anchors;

import java.util.List;

public interface IAnchorProvider {
    public AnchorManager getAnchorManager();

    public default List<AnchorReference> getAnchors() {
        return getAnchorManager().getAnchors();
    }
}
