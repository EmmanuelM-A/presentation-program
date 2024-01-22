package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseClicked extends IMouseListener {
    public void mouseClicked(MouseState state);
}
