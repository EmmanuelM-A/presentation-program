package com.scc210groupproject.structure;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author wonge1
 * Class to hold an entire presentation
 */
public class Presentation implements Serializable {

    /**
     * Slides the presentation contains
     */
    private ArrayList<Slide> slides;

    /**
     * Padding between Slides
     */
    private int padding;

    /**
     * Default size, array of length 2 (x for pixel width, y for pixel height)
     */
    private int[] defaultSize;

    /**
     * JPanel of the Presentation (Slides will be added to this)
     */
    private transient JPanel container;

    /**
     * Calculate the total length of the presentation by slides and padding
     * @return total length in pixels
     */
    private int totalLength() {
        int sum = 0;

        for (Slide slide : slides) {
            sum += slide.getDimension()[0];
        }

        sum += padding * (slides.size() - 1);

        return sum;
    }

    /**
     * Calculate the max height of the presentation by the tallest slide
     * @return max height in pixels
     */
    private int maxHeight() {
        int max = 0;

        for (Slide slide : slides) {
            int height = slide.getDimension()[1];
            if (height > max) {
                max = height;
            }
        }

        return max;
    }

    /**
     * Create a new slide in this presentation
     * @return created Slide
     */
    public Slide newSlide() {

        int[] position = new int[] { totalLength() + padding, 0 };
        Slide slide = new Slide(this, position, defaultSize);

        slides.add(slide);
        container.setSize(totalLength(), maxHeight());
        container.validate();

        return slide;
    }

    /**
     * Remove Slide from presentation
     * @param slide item to be removed
     */
    public void removeSlide(Slide slide) {
        slides.remove(slide);
        slide.destroy();
    }

    /**
     * Get all slides in the presentation
     * @return list of all slides
     */
    public ArrayList<Slide> getSlides() { return slides; }

    /**
     * Generate presentation after deserializing
     * @see BaseElement#generate() for the why
     */
    public void generate() {
        container = new JPanel();

        for (Slide slide : slides) {
            slide.generate();
        }

        container.setSize(totalLength(), maxHeight());
    }

    /**
     * Construct a presentation of one slide
     */
    public Presentation() {
        slides = new ArrayList<>();
        padding = 0;
        defaultSize = new int[]{500, 500};
        container = new JPanel();

        newSlide();
    }

    /**
     * Return the container for all slides
     * @return container
     */
    public Container getContainer() { return container; }
}
