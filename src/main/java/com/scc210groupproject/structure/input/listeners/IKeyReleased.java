package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IKeyReleased extends IInputListener {
    public void keyReleased(Object target, InputState state);
}
