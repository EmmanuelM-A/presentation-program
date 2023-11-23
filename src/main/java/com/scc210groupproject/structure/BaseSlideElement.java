package com.scc210groupproject.structure;

import java.io.Serializable;

/**
 * @author wonge1
 * Foundational SlideElement in a Slide of a Presentation
 * Abstract class to be extended by all SlideElement
 * All modification to SlideElement has to take effect of
 * @see BaseSlideElement#update()
 */
public abstract class BaseSlideElement implements Serializable {

    /**
     * Relative position (0.0-1.0) of the element in a slide
     * position[0] is X axis
     * position[1] is Y axis
     */
    protected double[] position;

    /**
     * Relative dimension (0.0-1.0) of the element in a slide
     * dimension[0] is X axis
     * dimension[1] is Y axis
     */
    protected double[] dimension;

    /**
     * Create a BaseSlideElement at (1.0, 1.0) of dimension (0.0, 0.0)
     * Outside of slide bound
     */
    public BaseSlideElement() {
        position = new double[] { 1.0, 1.0 };
        dimension = new double[] { 0.0, 0.0 };
    }

    /**
     * Create a BaseSlideElement at specified normalized position and dimension on a slide
     * @param initialPosition position to create element, must be an array of 2 elements
     * @param initialDimension dimension of the element, must be an array of 2 elements
     */
    public BaseSlideElement(double[] initialPosition, double[] initialDimension) {
        setPosition(initialPosition);
        setDimension(initialDimension);
    }

    /**
     * Support elements to update for any reason (reposition, animation, etc.)
     */
    public abstract void update();

    /**
     * Get the normalized position of the element
     * @return array of 2 elements representing first the X and second the Y axis
     */
    public double[] getPosition() {
        return position;
    }

    /**
     * Get the normalized dimension of the element
     * @return array of 2 elements representing first the X and second the Y axis
     */
    public double[] getDimension() {
        return dimension;
    }

    /**
     * Used in concert with the update function
     * @see BaseSlideElement#update()
     * Change the stored normailized position
     * @param newPosition array of 2 elements representing first the X and second the Y axis
     */
    public void setPosition(double[] newPosition) {
        if (newPosition.length != 2) {
            throw new IllegalArgumentException("position must be array of 2 values representing x and y");
        }

        position = newPosition;
    }

    /**
     * Used in concert with the update function
     * @see BaseSlideElement#update()
     * Change the stored normalized dimension
     * @param newDimension array of 2 elements representing first the X and second the Y axis
     */
    public void setDimension(double[] newDimension) {
        if (newDimension.length != 2) {
            throw new IllegalArgumentException("dimension must be array of 2 values representing x and y");
        }

        dimension = newDimension;
    }
}
