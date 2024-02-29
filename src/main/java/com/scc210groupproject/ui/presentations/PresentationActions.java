package com.scc210groupproject.ui.presentations;

import com.scc210groupproject.structure.ExtendedElement;

public abstract class PresentationActions {
    protected ExtendedElement selectedElement;

    public ExtendedElement getSelectedElement() {
        return this.selectedElement;
    }

    public void setSelectedElement(ExtendedElement newSelectedElement) {
        this.selectedElement = newSelectedElement;
    }

    public void doAction() {}
}
