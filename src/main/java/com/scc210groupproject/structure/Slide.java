package com.scc210groupproject.structure;

import javax.swing.*;
import java.awt.*;

/**
 * @author wonge1
 * Uses the implementation of the BaseElement class
 * Serializable as result of inheriting BaseElement
 */
public class Slide extends BaseElement {
    private Presentation specialParent;

    /**
     * Create a Slide at specified pixel position and dimension on a slide
     * @param parent must not be null, Presentation the slide is part of
     * @param initialPosition position to create element, must be an array of 2 elements
     * @param initialDimension dimension of the element, must be an array of 2 elements
     */
    public Slide(Presentation parent, int[] initialPosition, int[] initialDimension) {
        super(initialPosition, initialDimension);
        component = new JPanel();
        update();

        specialParent = parent;
        specialParent.getContainer().add(component);
    }

    /**
     * Prohibit constructor with 0 arguments
     */
    private Slide() {}

    /**
     * Called when created
     * Called when reloaded from file
     * Use this to create the component field and other setup tasks
     */
    @Override
    public void generate() {
        component = new JPanel();
        update();

        specialParent.getContainer().add(component);

        for (BaseElement element : children) {
            element.generate();
        }
    }

    /**
     * Remove component from container
     */
    @Override
    public void destroy() {
        super.destroy();

        specialParent.getContainer().remove(component);
        specialParent.getContainer().validate();
    }
}
