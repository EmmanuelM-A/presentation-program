package com.scc210groupproject.structure.liveness;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.readwrite.IJsonSerializable;

public class UpdateManager implements IJsonSerializable {

    private IUpdateProvider owner;
    public UpdateManager(IUpdateProvider owner) {
        this.owner = owner;
    }

    private List<IUpdateListener> listeners = new LinkedList<>();
    public void addListener(IUpdateListener listener) { listeners.add(listener); }
    public void removeListener(IUpdateListener listener) { listeners.remove(listener); }

    public void notifyUpdate(Object nullablePreviousSource) {
        for (IUpdateListener listener : listeners)
            if (listener != nullablePreviousSource)
                listener.onUpdate(owner);
    }

    private UpdateManager() {}
    public static UpdateManager createEmpty() { return new UpdateManager(); }

    @Override
    public void writeValue(Writer writer) throws IOException {
        if (!IJsonSerializable.class.isAssignableFrom(owner.getClass()))
            throw new IllegalArgumentException("UpdateManager with non IJsonSerializable owner must not be serialized");
        writer.writeObject("owner", (IJsonSerializable)owner);
        writer.writeObjectList("listeners", listeners, true);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readValue(Reader reader) throws IOException {
        owner = (IUpdateProvider)reader.readObject("owner");
        listeners = (List<IUpdateListener>)reader.readObjectList("listeners");
    }
}
