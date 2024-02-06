package com.scc210groupproject.structure.input;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.input.listeners.IMultSelect;
import com.scc210groupproject.structure.input.listeners.IMultiDrag;
import com.scc210groupproject.structure.input.listeners.IKeyPressed;
import com.scc210groupproject.structure.input.listeners.IKeyReleased;
import com.scc210groupproject.structure.input.listeners.IKeyTyped;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.structure.input.listeners.IMouseWheel;
import com.scc210groupproject.structure.input.listeners.IMultRelease;

public class InputManager {
    public HashMap<Class<?>, InputController> controllers;

    public InputManager() {
        controllers = new HashMap<>();
        
        controllers.put(IMouseEntered.class, new InputController(IMouseEntered.class));
        controllers.put(IMouseExited.class, new InputController(IMouseExited.class));
        
        controllers.put(IMouseDragged.class, new InputController(IMouseDragged.class));
        controllers.put(IMouseMoved.class, new InputController(IMouseMoved.class));

        controllers.put(IMouseClicked.class, new InputController(IMouseClicked.class));
        controllers.put(IMousePressed.class, new InputController(IMousePressed.class));
        controllers.put(IMouseReleased.class, new InputController(IMouseReleased.class));

        controllers.put(IMouseWheel.class, new InputController(IMouseWheel.class));

        controllers.put(IKeyTyped.class, new InputController(IKeyTyped.class));
        controllers.put(IKeyPressed.class, new InputController(IKeyPressed.class));
        controllers.put(IKeyReleased.class, new InputController(IKeyReleased.class));

        controllers.put(IMultSelect.class, new InputController(IMultSelect.class));
        controllers.put(IMultRelease.class, new InputController(IMultRelease.class));
        controllers.put(IMultiDrag.class, new InputController(IMultiDrag.class));
    }

    public static class InputController {
        private Class<?> type;

        public InputController(Class<?> type) {
            this.type = type;
        }

        private List<Object> listeners = new LinkedList<>();
    
        public void addListener(Object listener) {
            listeners.add(listener);
        }
    
        public void removeListener(Object listener) {
            listeners.remove(listener);
        }
    
        public void passEvent(BaseElement element, InputEmulator.InputState state) {
            if (listeners.size() > 0) {
                for (Object l : listeners)
                    try {
                        type.getDeclaredMethods()[0].invoke(l, element, state);
                    } catch (IllegalAccessException | SecurityException e) {
                        throw new IllegalArgumentException("provided type is not a IMouseListener");
                    } catch (InvocationTargetException e) {
                        throw new IllegalArgumentException("\ncheck method used for " + type.getName() + "\nIf you are using DragResizer, make sure the Element it is attached to implements IResizable");
                    }
            }
            else {
                BaseElement parent = element.getParent();
                if (parent != null)
                    parent.getMouseManager().controllers.get(type).passEvent(parent, state);
            }
        }
    }

    public void addListener(Object listener) {

        //interested in runtime class (which we can get all the implemented interface)
        Class<?> c = listener.getClass();

        for (Map.Entry<Class<?>, InputController> controller : controllers.entrySet()) {
            
            if (controller.getKey().isAssignableFrom(c))
                controller.getValue().addListener(listener);
        }
    }
    
    public void removeListener(Object listener) {

        //interested in runtime class (which we can get all the implemented interface)
        Class<?> c = listener.getClass();

        for (Map.Entry<Class<?>, InputController> controller : controllers.entrySet()) {
            if (controller.getKey().isAssignableFrom(c))
                controller.getValue().removeListener(listener);
        }
    }
}
