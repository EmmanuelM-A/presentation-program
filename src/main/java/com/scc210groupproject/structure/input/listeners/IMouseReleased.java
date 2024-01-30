package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseReleased extends IMouseListener  {
    public void mouseReleased(Object target, MouseState state);
}
