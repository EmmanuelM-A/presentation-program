package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseDragged extends IMouseListener {
    public void mouseDragged(MouseState state);
}
