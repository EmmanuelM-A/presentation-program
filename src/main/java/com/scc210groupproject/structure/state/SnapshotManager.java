package com.scc210groupproject.structure.state;

import java.util.Stack;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.liveness.IDestroyListener;

public class SnapshotManager {

    private static class Entry implements IDestroyListener {
        public BaseElement element;
        public Snapshot snapshot;

        public Entry(BaseElement element, Snapshot snapshot) {
            this.element = element;
            this.snapshot = snapshot;

            element.addDestroyListener(this);
        }

        public void discard() {
            element.removeDestroyListener(this);
        }

        @Override
        public void onDestroy(Object object) {
            steps.remove(this);
        }
    }

    private static Stack<Entry> steps = new Stack<>();
    private static int count = 0;
    private static int max = 250;

    public static boolean saveState(BaseElement element) {
        Snapshot snapshot = new Snapshot();
        element.writeSnapshot(snapshot);

        System.out.println(element);

        if (count >= max) {
            Entry expired = steps.removeLast();
            expired.discard();
        }
        else
            count++;
        steps.push(new Entry(element, snapshot));

        return true;
    }

    public static boolean restoreState() {
        if (count <= 0)
            return false;
        count--;
        Entry entry = steps.pop();
        System.out.println(entry.element);
        
        entry.element.readSnapshot(entry.snapshot);

        return true;
    }
}
