package com.scc210groupproject.structure;

import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wonge1
 * Class to hold an entire presentation
 */
public class Presentation implements Serializable {

    /**
     * Only one current presentation being edited
     */
    private static Presentation current = null;
    public static Presentation get() { return current; }
    public static void set(Presentation presentation)
    {
        Presentation last = current;

        current = presentation;

        {
            for (IChangePresentationListener listener : changePresentationListeners)
                listener.onChangePresentation(current, last);
        }

        for (int i = 0; i < current.slides.size(); i++)
        {
            for (ICreateSlideListener listener : createSlideListeners)
                listener.onCreateSlide(i, current.slides.get(i));
        }
    }

    /**
     * Return the current presentation (create a new one if not available)
     * @return current presentation
     */
    public static Presentation getOrCreate()
    {
        if (current == null)
            set(new Presentation());

        return current;
    }

    /**
     * Disregard existing current presentation, create a new one and set that as current
     * @return newly created current presentation
     */
    public static Presentation createNewAsCurrent()
    {
        set(new Presentation());

        return current;
    }

    /**
     * Listner for when the current presentation is changed
     */
    private static List<IChangePresentationListener> changePresentationListeners = new ArrayList<>();
    public static void addChangePresentationListener(IChangePresentationListener listener) { changePresentationListeners.add(listener); }
    public static void removeChangePresentationListener(IChangePresentationListener listener) { changePresentationListeners.remove(listener); }

    /**
     * Listner for when a new slide appears
     */
    private static List<ICreateSlideListener> createSlideListeners = new ArrayList<>();
    public static void addCreateSlideListener(ICreateSlideListener listener) { createSlideListeners.add(listener); }
    public static void removeCreateSlideListener(ICreateSlideListener listener) { createSlideListeners.remove(listener); }

    /**
     * Listner for when a slide is removed from presentation
     */
    private static List<IDiscardSlideListener> discardSlideListeners = new ArrayList<>();
    public static void addDiscardSlideListener(IDiscardSlideListener listener) { discardSlideListeners.add(listener); }
    public static void removeDiscardSlideListener(IDiscardSlideListener listener) { discardSlideListeners.remove(listener); }

    /**
     * Slides the presentation contains
     */
    private transient List<Slide> slides = new ArrayList<>();
    public List<Slide> getSlides() { return slides; }
    public Slide getSlideAtIndex(int i) { return slides.get(i); }
    public int getSlideCount() { return slides.size(); }

    /**
     * Default size, array of length 2 (x for pixel width, y for pixel height)
     */
    private static Dimension defaultSize = new Dimension(500, 500);

    /**
     * Construct a presentation of one slide
     */
    private Presentation()
    {
        slides = new ArrayList<>();

        newSlide(false);
    }

    /**
     * Create a new slide in this presentation
     * @return created Slide
     */
    public Slide newSlide()
    {
        return newSlide(true);
    }
    private Slide newSlide(boolean notify)
    {
        Slide slide = new Slide();
        slide.asComp().setSize(defaultSize);

// line for demo/testing
        slide.asComp().setBackground(new Color((float)slides.size() / 10 % 1, (float)slides.size() / 10 % 1, (float)slides.size() / 10 % 1));
        
        SampleElement sampleElement = new SampleElement();
        sampleElement.asComp().setBackground(Color.GREEN);
        slide.add(sampleElement);
// end of demo/testing code

        slides.add(slide);

        if (notify)
        {
            for (ICreateSlideListener listener : createSlideListeners)
                listener.onCreateSlide(slides.size() - 1, slide);
        }

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

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.writeObject(slides);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        slides = (ArrayList)in.readObject();
    }
}
