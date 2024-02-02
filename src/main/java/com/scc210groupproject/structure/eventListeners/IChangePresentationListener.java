package com.scc210groupproject.structure.eventListeners;

import com.scc210groupproject.structure.Presentation;

public interface IChangePresentationListener {
    public void onChangePresentation(Presentation current, Presentation discarded);
}
