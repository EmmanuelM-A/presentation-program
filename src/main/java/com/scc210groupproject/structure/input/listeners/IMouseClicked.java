package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IMouseClicked extends IInputListener {
    public void mouseClicked(Object target, InputState state);
}
