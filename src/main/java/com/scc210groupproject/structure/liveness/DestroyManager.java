package com.scc210groupproject.structure.liveness;

import java.io.IOException;
import java.util.LinkedList;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.readwrite.IJsonSerializable;

public class DestroyManager implements IJsonSerializable {

    private IDestroyProvider owner;
    public DestroyManager(IDestroyProvider owner) {
        this.owner = owner;
    }

    private LinkedList<IDestroyListener> listeners = new LinkedList<>();
    public void addListener(IDestroyListener listener) { listeners.add(listener); }
    public void removeListener(IDestroyListener listener) { listeners.remove(listener); }

    @SuppressWarnings("unchecked")
    public void notifyDestroy() {
        for (IDestroyListener listener : (LinkedList<IDestroyListener>)listeners.clone())
            listener.onDestroy(owner);
    }

    private DestroyManager() {}
    public static DestroyManager createEmpty() { return new DestroyManager(); }

    @Override
    public void writeValue(Writer writer) throws IOException {
        if (!IJsonSerializable.class.isAssignableFrom(owner.getClass()))
            throw new IllegalArgumentException("DestroyManager with non IJsonSerializable owner must not be serialized");
        writer.writeObject("owner", (IJsonSerializable)owner);
        writer.writeObjectList("listeners", listeners, true);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readValue(Reader reader) throws IOException {
        owner = (IDestroyProvider)reader.readObject("owner");
        listeners = (LinkedList<IDestroyListener>)reader.readObjectList("listeners");
    }
}
