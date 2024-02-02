package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IMouseDragged extends IInputListener {
    public void mouseDragged(Object target, InputState state);
}
