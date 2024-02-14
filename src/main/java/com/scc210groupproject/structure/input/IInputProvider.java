package com.scc210groupproject.structure.input;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IInputProvider {
    public abstract InputManager getInputManager();
    
    public default void addInputListener(Object listener) {
        getInputManager().addListener(listener);
    }
    
    public default void removeInputListener(Object listener) {
        getInputManager().removeListener(listener);
    }

    public default boolean passInputEvent(Class<?> type, BaseElement currentElement, InputState state) {
        return getInputManager().controllers.get(type).passEvent(currentElement, state);
    }
}
