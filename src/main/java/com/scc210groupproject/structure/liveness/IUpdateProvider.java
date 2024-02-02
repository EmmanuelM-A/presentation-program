package com.scc210groupproject.structure.liveness;

public interface IUpdateProvider {
    public abstract UpdateManager getUpdateManager();
    public default void notifyUpdate(Object nullablePreviousSource) { getUpdateManager().notifyUpdate(nullablePreviousSource); }
    public default void addUpdateListener(IUpdateListener listener) { getUpdateManager().addListener(listener); }
    public default void removeUpdateListener(IUpdateListener listener) { getUpdateManager().removeListener(listener); }
}
