package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IMouseMoved extends IInputListener  {
    public void mouseMoved(Object target, InputState state);
}
