package com.scc210groupproject.structure;

import javax.swing.*;

/**
 * @author wonge1
 * Use this as a reference (dont extend from this) for elements
 */
public class TemplateElement extends BaseElement {
    /**
     * Create a Slide at specified pixel position and dimension on a slide
     * @param parent must not be null, Presentation the slide is part of
     * @param initialPosition position to create element, must be an array of 2 elements
     * @param initialDimension dimension of the element, must be an array of 2 elements
     */
    public TemplateElement(BaseElement parent, int[] initialPosition, int[] initialDimension) {
        super(initialPosition, initialDimension);
        component = new JPanel();
        update();

        this.parent = parent;
        parent.component.add(component);
    }

    /**
     * Prohibit constructor with 0 arguments
     */
    private TemplateElement() {}

    /**
     * Called when reloaded from file
     * Use this to create the component field and other setup tasks
     */
    @Override
    public void generate() {
        component = new JPanel(); //can be any JComponent
        parent.component.add(component); //add to parent's component
        update(); //apply the position and dimension to the component

        for (BaseElement element : children) {
            element.generate(); //recursive call generate on child element
        }
    }

    @Override
    public void update() {
        component.setLocation(position[0], position[1]);
        component.setSize(dimension[0], dimension[1]);

        for (BaseElement element : children) {
            element.update();
        }
    }

    /**
     * Remove component from container
     */
    @Override
    public void destroy() {
        for (BaseElement element : children) {
            element.destroy();
        }

        parent.component.remove(component);
        parent.component.validate();
    }
}
