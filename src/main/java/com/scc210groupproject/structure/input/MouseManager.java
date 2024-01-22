package com.scc210groupproject.structure.input;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.structure.input.listeners.IMouseWheel;

public class MouseManager {
    public HashMap<Class<?>, MouseController> controllers;

    public MouseManager(BaseElement element) {
        controllers = new HashMap<>();
        
        controllers.put(IMouseEntered.class, new MouseController(IMouseEntered.class, element));
        controllers.put(IMouseExited.class, new MouseController(IMouseExited.class, element));
        
        controllers.put(IMouseDragged.class, new MouseController(IMouseDragged.class, element));
        controllers.put(IMouseMoved.class, new MouseController(IMouseMoved.class, element));

        controllers.put(IMouseClicked.class, new MouseController(IMouseClicked.class, element));
        controllers.put(IMousePressed.class, new MouseController(IMousePressed.class, element));
        controllers.put(IMouseReleased.class, new MouseController(IMouseReleased.class, element));

        controllers.put(IMouseWheel.class, new MouseController(IMouseWheel.class, element));
    }

    public static class MouseController {
        private BaseElement element;
        private Class<?> type;

        public MouseController(Class<?> type, BaseElement element) {
            this.element = element;
            this.type = type;
        }

        private List<Object> listeners = new LinkedList<>();
    
        public void addListener(Object listener) {
            listeners.add(listener);
        }
    
        public void removeListener(Object listener) {
            listeners.remove(listener);
        }
    
        public void passEvent(MouseEmulator.MouseState state) {
            if (listeners.size() > 0) {
                for (Object l : listeners)
                    try {
                        type.getDeclaredMethods()[0].invoke(l, state);
                    } catch (IllegalAccessException | InvocationTargetException | SecurityException e) {
                        throw new IllegalArgumentException("provided type is not a IMouseListener");
                    }
            }
            else {
                BaseElement parent = element.getParent();
                if (parent != null)
                    parent.getMouseManager().controllers.get(type).passEvent(state);
            }
        }
    }

    public void addListener(Object listener) {

        //interested in runtime class (which we can get all the implemented interface)
        Class<?> c = listener.getClass();

        for (Map.Entry<Class<?>, MouseController> controller : controllers.entrySet()) {
            if (controller.getKey().isAssignableFrom(c))
                controller.getValue().addListener(listener);
        }
    }
    
    public void removeListener(Object listener) {

        //interested in runtime class (which we can get all the implemented interface)
        Class<?> c = listener.getClass();

        for (Map.Entry<Class<?>, MouseController> controller : controllers.entrySet()) {
            if (controller.getKey().isAssignableFrom(c))
                controller.getValue().removeListener(listener);
        }
    }
}
