package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IMousePressed extends IInputListener  {
    public void mousePressed(Object target, InputState state);
}
