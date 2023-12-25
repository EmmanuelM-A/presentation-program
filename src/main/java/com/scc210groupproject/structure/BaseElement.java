package com.scc210groupproject.structure;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.readwrite.IJsonSerializable;

/**
 * @author wonge1
 * Foundational SlideElement in a Slide of a Presentation
 * Abstract class to be extended by all SlideElement
 * All modification to SlideElement has to take effect of
 * @see BaseElement#update()
 * For fields not to be serialized (saved to file),
 * Mark them as transient
 * @see BaseElement#component
 */
public abstract class BaseElement implements IJsonSerializable
{
    protected abstract void writeSelf(Writer writer) throws IOException;
    protected abstract void readSelf(Reader reader) throws IOException;

    protected transient BaseElement parent = null;
    protected transient List<BaseElement> children = new ArrayList<>();

    @Override
    public void writeValue(Writer writer) throws IOException {
        writeSelf(writer);
        writer.writeObject("parent", parent);
        writer.writeObjectList("children", children);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readValue(Reader reader) throws IOException {
        readSelf(reader);
        parent = (BaseElement)reader.readObject("parent");
        children = (List<BaseElement>)reader.readObjectList("children");
        for (BaseElement element : children)
            processNewElement(element);
    }

    public abstract Component asComp();

    protected abstract void processNewElement(BaseElement element);
    protected abstract void prepareRemoveElement(BaseElement element);
    public final void add(BaseElement child)
    {
        children.add(child);
        child.parent = this;

        processNewElement(child);

        notifyUpdate();
    }
    public final void remove(BaseElement child)
    {
        prepareRemoveElement(child);

        child.parent = null;
        children.remove(child);

        notifyUpdate();
    }

    protected void notifyUpdate() {
        if (parent != null)
            parent.notifyUpdate();
    }
}
