package com.scc210groupproject.structure.input.listeners;

import com.scc210groupproject.structure.input.MouseEmulator.MouseState;

public interface IMouseExited extends IMouseListener  {
    public void mouseExited(Object target, MouseState state);
}
