package com.scc210groupproject.structure;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author wonge1
 * Foundational SlideElement in a Slide of a Presentation
 * Abstract class to be extended by all SlideElement
 * All modification to SlideElement has to take effect of
 * @see BaseElement#update()
 * For fields not to be serialized (saved to file),
 * Mark them as transient
 * @see BaseElement#component
 */
public abstract class BaseElement implements Serializable {

    /**
     * Pixel position of the element in a slide
     * position[0] is X axis
     * position[1] is Y axis
     */
    protected int[] position;

    /**
     * Pixel dimension of the element in a slide
     * dimension[0] is X axis
     * dimension[1] is Y axis
     */
    protected int[] dimension;

    /**
     * Component field to hold any type of Swing Component (TextField, Button, etc.)
     * Use instanceof and casting to convert it back to the desired class when extending this class
     * Transient, so it does not get serialized
     */
    protected transient JComponent component;

    /**
     * Element above this element hierarchy wise (i.e. blocks in a data structure representation)
     * Use instanceof and casting to convert it back to the desired class when extending this class
     * Can be null
     */
    protected BaseElement parent;

    /**
     * Any elements under this element hierarchy wise (i.e. blocks in a data structure representation)
     * Use instanceof and casting to convert it back to the desired class when extending this class
     * Can be empty
     */
    protected ArrayList<BaseElement> children;

    /**
     * Create a BaseSlideElement at (1.0, 1.0) of dimension (0.0, 0.0)
     * Outside of slide bound
     * remember to call generate for your implementation
     */
    public BaseElement() {
        this(new int[] { 1, 1 }, new int[] { 0, 0 });
    }

    /**
     * Create a BaseSlideElement at specified pixel position and dimension on a slide
     * remember to call generate for your implementation
     * @see BaseElement#generate()
     * @param initialPosition position to create element, must be an array of 2 elements
     * @param initialDimension dimension of the element, must be an array of 2 elements
     */
    public BaseElement(int[] initialPosition, int[] initialDimension) {
        this(null, initialPosition, initialDimension);
    }

    /**
     * Create a BaseSlideElement at specified pixel position and dimension on a slide with Parent
     * remember to call generate for your implementation
     * @param initialParent parent of this element
     * @param initialPosition position to create element, must be an array of 2 elements
     * @param initialDimension dimension of the element, must be an array of 2 elements
     */
    public BaseElement(BaseElement initialParent, int[] initialPosition, int[] initialDimension) {
        setPosition(initialPosition);
        setDimension(initialDimension);
        parent = initialParent;
        children = new ArrayList<>();
    }

    /**
     * Called when created
     * Called when reloaded from file
     * Use this to create the component field and other setup tasks
     * @see Slide#generate() for the most simplistic implementation
     * Typically you would also want to add the component to the parent's component
     * Something like this:
     * if (parent != null)
     *     parent.component.add(component);
     */
    public abstract void generate();

    /**
     * Support elements to update for any reason (reposition, animation, etc.)
     * Parent's component has dimensions in pixel cord
     * If another Element is a child of an Element
     * then call update on the child Element(s)
     * This is a basic implementation (used by Slide), in your actual implementation
     * you might want to set the position of the childElements before updating them
     * @see Slide
     * @see JComponent#getSize()
     */
    public void update() {
        component.setLocation(position[0], position[1]);
        component.setSize(dimension[0], dimension[1]);

        for (BaseElement element : children) {
            element.update();
        }
    }

    /**
     * Get the pixel position of the element
     * @return array of 2 elements representing first the X and second the Y axis
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * Get the pixel dimension of the element
     * @return array of 2 elements representing first the X and second the Y axis
     */
    public int[] getDimension() {
        return dimension;
    }

    /**
     * Used in concert with the update function
     * @see BaseElement#update()
     * Change the stored pixel position
     * @param newPosition array of 2 elements representing first the X and second the Y axis
     */
    public void setPosition(int[] newPosition) {
        if (newPosition.length != 2) {
            throw new IllegalArgumentException("position must be array of 2 values representing x and y");
        }

        position = newPosition;
    }

    /**
     * Used in concert with the update function
     * @see BaseElement#update()
     * Change the stored pixel dimension
     * @param newDimension array of 2 elements representing first the X and second the Y axis
     */
    public void setDimension(int[] newDimension) {
        if (newDimension.length != 2) {
            throw new IllegalArgumentException("dimension must be array of 2 values representing x and y");
        }

        dimension = newDimension;
    }

    /**
     * Add element to slide
     * @see BaseElement#children
     * @param target item immediately child of the Slide
     */
    public void addElement(BaseElement target) {
        children.add(target);
    }

    /**
     * Check if element is a child in the entire hierarchy of the Slide, provide the parent of the element if found
     * @param target item to check for
     * @return immediate parent of the element if found, false otherwise
     */
    public BaseElement hasElementInHierarchy(BaseElement target) {
        if (children.contains(target)) {
            return this;
        }

        for (BaseElement element : children) {
            BaseElement result = element.hasElementInHierarchy(target);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * Remove an element from the direct child list
     * @param target item to remove
     */
    public void removeElement(BaseElement target) {
        children.remove(target);
    }

    /**
     * Return parent element (if any)
     * @return BaseElement, use instanceof and casting to get expected type, null if none
     */
    public BaseElement getParent() { return parent; }

    /**
     * Return child elements (if any)
     * @return ArrayList of BaseElement, use instanceof and casting to get expected type, maybe empty
     */
    public ArrayList<BaseElement> getChildren() { return  children; }

    /**
     * Check if a position is within the bounds of this element
     * @param evaluatePosition array of 2 elements representing first the X and second the Y axis
     * @return first BaseElement in the hierarchy (downwards) which it is in the bounds of
     */
    public BaseElement withinBoundOfHierarchy(int[] evaluatePosition) {
        if (evaluatePosition.length != 2) {
            throw new IllegalArgumentException("position must be array of 2 values representing x and y");
        }

        if (component.contains(evaluatePosition[0], evaluatePosition[1])) {
            return this;
        }


        for (BaseElement element : children) {
            BaseElement result = element.withinBoundOfHierarchy(evaluatePosition);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /**
     * Clean up anything when the element is destroyed (call recursively to child elements)
     */
    public void destroy() {
        for (BaseElement element : children) {
            element.destroy();
        }
    }
}
