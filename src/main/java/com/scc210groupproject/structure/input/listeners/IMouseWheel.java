package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseWheel extends IMouseListener {
    public void mouseWheelMoved(MouseState state);
}
