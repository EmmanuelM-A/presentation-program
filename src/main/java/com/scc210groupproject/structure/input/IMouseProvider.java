package com.scc210groupproject.structure.input;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseProvider {
    public abstract MouseManager getMouseManager();
    
    public default void addMouseListener(Object listener) {
        getMouseManager().addListener(listener);
    }
    
    public default void removeMouseListener(Object listener) {
        getMouseManager().removeListener(listener);
    }

    public default void passMouseEvent(Class<?> type, MouseState state) {
        getMouseManager().controllers.get(type).passEvent(state);
    }
}
