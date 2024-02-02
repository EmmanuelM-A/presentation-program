package com.scc210groupproject.structure.input.adapters;

import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;

public class MouseMotionAdapter implements IMouseDragged, IMouseMoved {

    @Override
    public void mouseMoved(Object target, InputState state) {
    }

    @Override
    public void mouseDragged(Object target, InputState state) {
    }
    
}
