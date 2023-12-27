package com.scc210groupproject.structure.optionalAnchors;

import java.util.List;

public interface IAnchorProvider {
    public AnchorManager getAnchorManager();

    public default List<AnchorReference> getAnchors() {
        return getAnchorManager().getAnchors();
    }
}
