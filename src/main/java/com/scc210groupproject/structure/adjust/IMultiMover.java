package com.scc210groupproject.structure.adjust;

import com.scc210groupproject.structure.input.InputEmulator.InputState;

public interface IMultiMover {
    public void move(InputState state);
    public void evaluateState(boolean inSelectionStage);
}
