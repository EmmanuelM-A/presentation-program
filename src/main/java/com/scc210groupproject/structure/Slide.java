package com.scc210groupproject.structure;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author wonge1
 * Uses the implementation of the BaseElement class
 * Serializable as result of inheriting BaseElement
 */
public class Slide extends BaseElement {

    /**
     * Create a Slide at specified pixel position and dimension on a slide
     * @param parent must not be null, Presentation the slide is part of
     * @param initialPosition position to create element, must be an array of 2 elements
     * @param initialDimension dimension of the element, must be an array of 2 elements
     */
    public Slide(Presentation parent, int[] initialDimension) {
        super(new int[]{0, 0}, initialDimension);
        component = new JPanel();
        component.setBackground(Color.green);
        update();
    }

    /**
     * Prohibit constructor with 0 arguments
     */
    private Slide() {}

    /**
     * Called when reloaded from file
     * Use this to create the component field and other setup tasks
     */
    @Override
    public void generate() {
        component = new JPanel();
        update();

        for (BaseElement element : children) {
            element.generate();
        }
    }

    /**
     * Update size of self and sub element
     */
    @Override
    public void update() {
        component.setLocation(position[0], position[1]);
        component.setSize(dimension[0], dimension[1]);
        component.setPreferredSize(new Dimension(dimension[0], dimension[1]));
        component.revalidate();

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
    }

    /**
     * Create an image of the Slide
     * @return Graphic2D of Slide
     */
    public Graphics2D createPreview()
    {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        component.paint(graphics);

        return(graphics);
    }
}
