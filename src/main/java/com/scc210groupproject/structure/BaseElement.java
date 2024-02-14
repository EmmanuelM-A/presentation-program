package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.SwingUtilities;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.input.IInputProvider;
import com.scc210groupproject.structure.input.InputManager;
import com.scc210groupproject.structure.liveness.DestroyManager;
import com.scc210groupproject.structure.liveness.IDestroyListener;
import com.scc210groupproject.structure.liveness.IDestroyProvider;
import com.scc210groupproject.structure.liveness.IUpdateListener;
import com.scc210groupproject.structure.liveness.IUpdateProvider;
import com.scc210groupproject.structure.liveness.UpdateManager;
import com.scc210groupproject.structure.state.Snapshot;
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
public abstract class BaseElement implements IJsonSerializable, IUpdateProvider, IUpdateListener, IDestroyProvider, IDestroyListener, IInputProvider
{
    protected abstract void writeSelf(Writer writer) throws IOException;
    protected abstract void readSelf(Reader reader) throws IOException;

    public abstract void writeSnapshot(Snapshot snapshot);
    public abstract void readSnapshot(Snapshot snapshot);

    protected BaseElement parent = null;
    protected List<BaseElement> children = new LinkedList<>();

    private UpdateManager updateManager = new UpdateManager(this);
    private DestroyManager destroyManager = new DestroyManager(this);
    private InputManager mouseManager = new InputManager();

    @Override
    public void writeValue(Writer writer) throws IOException {
        writeSelf(writer);
        writer.writeObject("parent", parent);
        writer.writeObject("update manager", updateManager);
        writer.writeObject("destroy manager", destroyManager);
        writer.writeObjectList("children", children);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readValue(Reader reader) throws IOException {
        parent = (BaseElement)reader.readObject("parent");
        updateManager = (UpdateManager)reader.readObject("update manager");
        destroyManager = (DestroyManager)reader.readObject("destroy manager");
        children = (List<BaseElement>)reader.readObjectList("children");

        readSelf(reader);
        for (BaseElement element : children)
            processNewElement(element);
    }

    public abstract Component asComp();

    protected void processNewElement(BaseElement element) {
        Component comp = asComp();
        if (comp instanceof Container) {
            Container cont = (Container)comp;
            cont.add(element.asComp(), 0);
        }
        comp.revalidate();
    }
    protected void prepareRemoveElement(BaseElement element) {
        Component comp = asComp();
        if (comp instanceof Container) {
            Container cont = (Container)comp;
            cont.remove(element.asComp());
        }
        comp.revalidate();
    }
    public final void add(BaseElement child)
    {
        children.add(child);
        child.parent = this;

        addUpdateListener(child);
        addDestroyListener(child);

        child.addUpdateListener(this);
        child.addDestroyListener(this);
        processNewElement(child);

        notifyUpdate(null);
    }
    public final void remove(BaseElement child)
    {
        prepareRemoveElement(child);
        child.removeDestroyListener(this);
        child.removeUpdateListener(this);

        removeDestroyListener(child);
        removeUpdateListener(child);

        child.parent = null;
        children.remove(child);

        notifyUpdate(null);
    }

    public final BaseElement getParent() { return parent; }

    public final List<BaseElement> getChildren() { return Collections.unmodifiableList(children); }

    protected void destroySelf() {}
    public final void destroy() {
        destroySelf();

        for (BaseElement element : children)
            element.removeDestroyListener(this);

        notifyDestroy();
    }

    protected void processUpdate(Object object) {}
    @Override
    public final UpdateManager getUpdateManager() { return updateManager; }
    @Override
    public final void onUpdate(Object object) {
        processUpdate(object);

        if (children.contains(object))
            notifyUpdate(object);
    }

    protected void processDestroy(Object object) {}
    @Override
    public final DestroyManager getDestroyManager() { return destroyManager; }
    @Override
    public final void onDestroy(Object object) {
        processDestroy(object);

        if (object == parent)
            destroy();
        else if (children.contains(object))
            remove((BaseElement)object);
    }

    // similar to javax.swing.SwingUtilities:isDescendingFrom
    // return immediate decendent pointing to self
    private Component checkParent(Component self, Component target) {
        if (self == null)
            return null;

        Component parent = self.getParent();

        if (parent == target)
            return self;

        return checkParent(parent, target);
    }

    // mirror java.awt.Container:findComponentAt
    public BaseElement findElmentAt(Point point) {
        Component current = asComp();

        if (!current.contains(point.x, point.y))
            return null;

        if (current instanceof Container) {

            Container container = (Container)current;
            
            BaseElement result = null;
            int lowest = Integer.MAX_VALUE;

            for (BaseElement element : children) {

                Component inner = element.asComp();

                Component immediate = checkParent(inner, container);
                if (immediate == null) {
                    continue;
                }

                int zValue = container.getComponentZOrder(immediate);
                if (zValue > lowest)
                    continue;

                BaseElement contender = element.findElmentAt(
                    SwingUtilities.convertPoint(container, point, inner));
                if (contender != null) {
                    result = contender;
                    lowest = zValue;
                }
            }

            if (result != null)
                return result;
        }

        return this;
    }

    public Collection<BaseElement> getImmediateHierarchy(Point point) {
        TreeMap<Integer, BaseElement> order = new TreeMap<>();

        Component current = asComp();

        if (!current.contains(point.x, point.y))
            return null;

        if (current instanceof Container) {

            Container container = (Container)current;
            
            for (BaseElement element : children) {

                Component inner = element.asComp();


                Component immediate = checkParent(inner, container);
                if (immediate == null) {
                    continue;
                }

                if (!immediate.contains(SwingUtilities.convertPoint(container, point, inner)))
                    continue;

                int zValue = container.getComponentZOrder(immediate);
                order.put(zValue, element);
            }
        }

        return order.values();
    }

    public InputManager getMouseManager() {
        return mouseManager;
    }
}
