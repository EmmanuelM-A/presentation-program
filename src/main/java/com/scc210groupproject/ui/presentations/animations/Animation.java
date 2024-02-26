package com.scc210groupproject.ui.presentations.animations;

import java.awt.Point;

import com.scc210groupproject.structure.ExtendedElement;

public abstract class Animation {

    protected ExtendedElement selectedElement;

    public ExtendedElement getSelectedElement() {
        return this.selectedElement;
    }

    public void setSelectedElement(ExtendedElement newSelectedElement) {
        this.selectedElement = newSelectedElement;
    }

    public void doAnimation() {}

    protected void moveObject(Point startingPoint, Point targetPoint) {}

    protected Point calculateStartingPoint(ExtendedElement element) {
        return null;
    }
}
