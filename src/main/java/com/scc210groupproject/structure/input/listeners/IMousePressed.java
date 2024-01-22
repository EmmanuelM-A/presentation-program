package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMousePressed extends IMouseListener  {
    public void mousePressed(MouseState state);
}
