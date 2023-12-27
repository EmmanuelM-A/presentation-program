package com.scc210groupproject.structure.liveness;

public interface IDestroyProvider {
    public abstract DestroyManager getDestroyManager();
    public default void notifyDestroy() { getDestroyManager().notifyDestroy(); }
    public default void addDestroyListener(IDestroyListener listener) { getDestroyManager().addListener(listener); }
    public default void removeDestroyListener(IDestroyListener listener) { getDestroyManager().removeListener(listener); }
}
