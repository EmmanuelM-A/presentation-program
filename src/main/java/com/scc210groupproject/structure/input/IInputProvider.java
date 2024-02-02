package com.scc210groupproject.structure.input;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IInputProvider {
    public abstract InputManager getMouseManager();
    
    public default void addInputListener(Object listener) {
        getMouseManager().addListener(listener);
    }
    
    public default void removeMouseListener(Object listener) {
        getMouseManager().removeListener(listener);
    }

    public default void passMouseEvent(Class<?> type, BaseElement currentElement, InputState state) {
        getMouseManager().controllers.get(type).passEvent(currentElement, state);
    }
}
