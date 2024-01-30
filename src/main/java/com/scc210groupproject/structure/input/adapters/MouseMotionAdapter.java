package com.scc210groupproject.structure.input.adapters;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;

public class MouseMotionAdapter implements IMouseDragged, IMouseMoved {

    @Override
    public void mouseMoved(Object target, MouseState state) {
    }

    @Override
    public void mouseDragged(Object target, MouseState state) {
    }
    
}
