package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseEntered extends IMouseListener {
    public void mouseEntered(Object target, MouseState state);
}
