package com.scc210groupproject.structure;

import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public abstract class BaseElement implements Serializable
{
    protected abstract void writeSelf(ObjectOutputStream out) throws IOException;
    protected abstract void readSelf(ObjectInputStream in) throws IOException, ClassNotFoundException;

    protected BaseElement parent = null;
    protected List<BaseElement> children = new ArrayList<>();
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        writeSelf(out);
        
        out.writeObject(children);
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        readSelf(in);
        
        children = (ArrayList)in.readObject();

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
    }
    public final void remove(BaseElement child)
    {
        prepareRemoveElement(child);

        child.parent = null;
        children.remove(child);
    }
}
