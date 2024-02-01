package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IKeyTyped extends IInputListener {
    public void keyTyped(Object target, InputState state);
}
