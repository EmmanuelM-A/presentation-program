package com.scc210groupproject.ui.presentations.animations;

import java.awt.Point;

import javax.swing.JPanel;

import com.scc210groupproject.structure.ExtendedElement;

public abstract class Animation {

    protected ExtendedElement selectedElement;

    protected JPanel display;

    public ExtendedElement getSelectedElement() {
        return this.selectedElement;
    }

    public JPanel getDisplay() {
        return this.display;
    }

    public void setDisplay(JPanel display) {
        this.display = display;
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
