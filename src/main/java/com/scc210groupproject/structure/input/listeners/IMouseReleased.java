package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IMouseReleased extends IInputListener  {
    public void mouseReleased(Object target, InputState state);
}
