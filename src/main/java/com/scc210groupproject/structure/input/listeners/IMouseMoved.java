package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseMoved extends IMouseListener  {
    public void mouseMoved(MouseState state);
}