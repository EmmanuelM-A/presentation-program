package com.scc210groupproject.structure.adjust;

import java.util.HashSet;

import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.state.SnapshotManager;

public class MultiController {
    
    private static HashSet<IMultiMover> selected = new HashSet<>();

    public static void clearAll() {

        @SuppressWarnings("unchecked")
        HashSet<IMultiMover> copy = (HashSet<IMultiMover>)selected.clone();
        selected.clear();

        for (IMultiMover mover : copy)
            mover.evaluateState(false);
    }
    
    public static void add(IMultiMover mover) {
        selected.add(mover);
    }

    public static void remove(IMultiMover mover) {
        selected.remove(mover);
    }

    public static boolean hasMultiple() {
        return selected.size() > 1;
    }

    public static boolean contains(IMultiMover mover) {
        return selected.contains(mover);
    }

    public static void moveAll(InputState state) {
        for (IMultiMover mover : selected)
            mover.move(state);
    }

    public static void endMove() {
        SnapshotManager.saveState();
    }

    public static void evaluateAll(boolean stage) {
        for (IMultiMover mover : selected)
            mover.evaluateState(stage);
    }
}
