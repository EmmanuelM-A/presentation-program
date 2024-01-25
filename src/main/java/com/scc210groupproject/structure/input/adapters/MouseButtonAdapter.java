package com.scc210groupproject.structure.input.adapters;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;

public class MouseButtonAdapter implements IMouseClicked, IMousePressed, IMouseReleased {

    @Override
    public void mouseReleased(Object target, MouseState state) {
    }

    @Override
    public void mousePressed(Object target, MouseState state) {
    }

    @Override
    public void mouseClicked(Object target, MouseState state) {
    }
    
}
