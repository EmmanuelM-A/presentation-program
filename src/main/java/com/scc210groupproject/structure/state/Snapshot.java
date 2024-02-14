package com.scc210groupproject.structure.state;

import java.util.HashMap;

public class Snapshot {
    private HashMap<String, Object> referenceTable = new HashMap<>();

    public void addEntry(String name, Object value) {
        referenceTable.put(name, value);
    }

    public Object readEntry(String name) {
        return referenceTable.get(name);
    }
}
