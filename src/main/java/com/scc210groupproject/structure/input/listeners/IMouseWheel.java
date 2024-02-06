package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IMouseWheel extends IInputListener {
    public void mouseWheelMoved(Object target, InputState state);
}
