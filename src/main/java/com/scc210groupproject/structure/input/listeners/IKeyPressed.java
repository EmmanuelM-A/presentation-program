package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IKeyPressed extends IInputListener {
    public void keyPressed(Object target, InputState state);
}
