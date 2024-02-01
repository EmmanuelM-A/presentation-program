package com.scc210groupproject.structure.input.adapters;

import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IKeyPressed;
import com.scc210groupproject.structure.input.listeners.IKeyReleased;
import com.scc210groupproject.structure.input.listeners.IKeyTyped;

public class KeyAdapter implements IKeyTyped, IKeyPressed, IKeyReleased {

    @Override
    public void keyReleased(Object target, InputState state) {
    }

    @Override
    public void keyPressed(Object target, InputState state) {
    }

    @Override
    public void keyTyped(Object target, InputState state) {
    }
    
}
