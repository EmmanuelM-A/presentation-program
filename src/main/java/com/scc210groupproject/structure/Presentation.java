package com.scc210groupproject.structure;

import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author wonge1
 * Class to hold an entire presentation
 */
public class Presentation implements Serializable {

    /**
     * Only one current presentation being edited
     */
    public static transient Presentation current;

    /**
     * Return the current presentation (create a new one if not available)
     * @return current presentation
     */
    public static Presentation getOrCreate()
    {
        if (current == null)
            current = new Presentation();

        return current;
    }

    /**
     * Disregard existing current presentation, create a new one and set that as current
     * @return newly created current presentation
     */
    public static Presentation createNewAsCurrent()
    {
        current = new Presentation();

        return current;
    }

    /**
     * Holds all ICreateSlideListener
     */
    private static ArrayList<ICreateSlideListener> createSlideListeners = new ArrayList<>();

    /**
     * Add a listner for when a new slide appears
     * @param listener listener to add
     */
    public static void addCreateSlideListener(ICreateSlideListener listener) { createSlideListeners.add(listener); }

    /**
     * Remove a listener for when a new slide appears
     * @param listener listener to remove
     */
    public static void removeCreateSlideListener(ICreateSlideListener listener) { createSlideListeners.remove(listener); }

    /**
     * Holds all IDiscardSlideListener
     */
    private static ArrayList<IDiscardSlideListener> discardSlideListeners = new ArrayList<>();

    /**
     * Add a listner for when a slide is removed from presentation
     * @param listener listener to add
     */
    public static void addDiscardSlideListener(IDiscardSlideListener listener) { discardSlideListeners.add(listener); }
    
    /**
     * Remove a listener for when a slide is removed from presentation
     * @param listener listener to remove
     */
    public static void removeDiscardSlideListener(IDiscardSlideListener listener) { discardSlideListeners.remove(listener); }

    /**
     * Slides the presentation contains
     */
    private ArrayList<Slide> slides;

    /**
     * Default size, array of length 2 (x for pixel width, y for pixel height)
     */
    private int[] defaultSize;

    /**
     * Construct a presentation of one slide
     */
    private Presentation()
    {
        slides = new ArrayList<>();

        defaultSize = new int[]{500, 500};

        newSlide();
    }

    /**
     * Create a new slide in this presentation
     * @return created Slide
     */
    public Slide newSlide()
    {
        Slide slide = new Slide(this, defaultSize);
        slides.add(slide);

        for (ICreateSlideListener listener : createSlideListeners)
            listener.onCreateSlide(slides.size() - 1, slide);

        return slide;
    }

    /**
     * Remove Slide from presentation
     * Must have more than 1 slide to not throw error
     * @param slide item to be removed
     */
    public void removeSlide(Slide slide)
    {
        if (slides.size() <= 1)
            throw new IllegalArgumentException("Cannot remove final slide");
        
        int index = slides.indexOf(slide);

        slides.remove(slide);

        for (IDiscardSlideListener listener : discardSlideListeners)
            listener.onDiscardSlide(index, slide);
    }

    /**
     * Get all slides in the presentation
     * @return list of all slides
     */
    public ArrayList<Slide> getSlides() { return slides; }

    /**
     * Get slide at index in the presentation
     * @param i index of slide
     * @return slide at index
     */
    public Slide getSlideAtIndex(int i) { return slides.get(i); }

    /**
     * Generate presentation after deserializing
     * @see BaseElement#generate() for the why
     */
    public void generate()
    {
        for (Slide slide : slides)
            slide.generate();
    }
}
