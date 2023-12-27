package com.scc210groupproject.structure;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.readwrite.IJsonSerializable;
import com.scc210groupproject.structure.liveness.IUpdateListener;
import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;
import com.scc210groupproject.structure.eventListeners.IUpdateSlideListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wonge1
 * Class to hold an entire presentation
 */
public class Presentation implements IJsonSerializable, IUpdateListener {

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
     * Listner for when a slide is changed
     */
    private static List<IUpdateSlideListener> updateSlideListeners = new ArrayList<>();
    public static void addUpdateSlideListener(IUpdateSlideListener listener) { updateSlideListeners.add(listener); }
    public static void removeUpdateSlideListener(IUpdateSlideListener listener) { updateSlideListeners.remove(listener); }

    /**
     * Listner for when a slide is removed from presentation
     */
    private static List<IDiscardSlideListener> discardSlideListeners = new ArrayList<>();
    public static void addDiscardSlideListener(IDiscardSlideListener listener) { discardSlideListeners.add(listener); }
    public static void removeDiscardSlideListener(IDiscardSlideListener listener) { discardSlideListeners.remove(listener); }

    /**
     * Slides the presentation contains
     */
    private List<Slide> slides = new LinkedList<>();
    public List<Slide> getSlides() { return slides; }
    public Slide getSlideAtIndex(int i) { return slides.get(i); }
    public int getSlideCount() { return slides.size(); }

    /**
     * Default size, array of length 2 (x for pixel width, y for pixel height)
     */
    private static Dimension defaultSize = new Dimension(1600, 900);

    /**
     * Construct a presentation of one slide
     */
    private Presentation()
    {
        slides = new LinkedList<>();

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
//<<<<<<< HEAD
        //Slide slide = new Slide(defaultSize);
//=======
        Slide slide = new Slide();
        //slide.asComp().setSize(defaultSize);
//>>>>>>> e21cd65 (Layout of slide manager using Slide, Presentation and MainDisplayPanel)

// line for demo/testing
//<<<<<<< HEAD
        slide.setBackground(new Color((float)slides.size() / 10 % 1, (float)slides.size() / 10 % 1, (float)slides.size() / 10 % 1));

        SampleElement sampleElement = new SampleElement();
        sampleElement.setBackground(Color.RED);
        sampleElement.setLocation(new Point(250, 250));
        sampleElement.setSize(new Dimension(100, 50));
        slide.add(sampleElement);

        ArrowElement arrowElement = new ArrowElement(new Point(20, 20), new Point(100, 200));
        arrowElement.setArrow(ArrowElement.Side.A, true, 20, 20);
        arrowElement.setArrow(ArrowElement.Side.B, true, 30, 30);
        arrowElement.setColor(Color.GREEN);
        arrowElement.setLine(false, 5, 5);
        arrowElement.setAnchor(ArrowElement.Side.A, sampleElement.getAnchors().get(1));
        slide.add(arrowElement);

        SampleElement sampleElement2 = new SampleElement();
        sampleElement2.setBackground(Color.BLUE);
        sampleElement2.setLocation(new Point(100, 50));
        sampleElement2.setSize(new Dimension(50, 200));
        arrowElement.setAnchor(ArrowElement.Side.B, sampleElement2.getAnchors().get(1));
        slide.add(sampleElement2);

        sampleElement.setLocation(new Point(400, 400));
        sampleElement2.setSize(new Dimension(20, 10));
        arrowElement.setAnchor(ArrowElement.Side.A, sampleElement.getAnchors().get(3));
//=======
        /*slide.asComp().setBackground(new Color((float)slides.size() / 10 % 1, (float)slides.size() / 10 % 1, (float)slides.size() / 10 % 1));
        
        SampleElement sampleElement = new SampleElement();
        sampleElement.asComp().setBackground(Color.GREEN);
        slide.add(sampleElement);*/
//>>>>>>> 9349194 (Slides can be added and displayed)
// end of demo/testing code

        slides.add(slide);
        slide.addUpdateListener(this);

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

        slide.removeUpdateListener(this);
        slides.remove(slide);

        slide.destroy();

        for (IDiscardSlideListener listener : discardSlideListeners)
            listener.onDiscardSlide(index, slide);
    }

    @Override
    public void onUpdate(Object object) {
        if (object instanceof Slide) {
            Slide slide = (Slide)object;
            int index = slides.indexOf(slide);
            for (IUpdateSlideListener listener : updateSlideListeners)
                listener.onUpdateSlide(index, slide);
        }
    }

    public static Presentation createEmpty() { return new Presentation(); }

    @Override
    public void writeValue(Writer writer) throws IOException {
        writer.writeObjectList("slides", slides);
    }
    @Override
    @SuppressWarnings("unchecked")
    public void readValue(Reader reader) throws IOException {
        slides = (List<Slide>)reader.readObjectList("slides");
    }
}
