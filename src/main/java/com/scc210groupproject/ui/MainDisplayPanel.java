package com.scc210groupproject.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.scc210groupproject.structure.*;
import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;
import com.scc210groupproject.structure.eventListeners.IUpdateSlideListener;

public class MainDisplayPanel extends JPanel implements IChangePresentationListener, IUpdateSlideListener, IDiscardSlideListener {

    public static MainDisplayPanel instance;

    private ScaledPanel scaledPanel;
    private Slide currentSilde;

    public MainDisplayPanel()
    {
        super();

        super.setLayout(new GridLayout(0, 1));
        super.setBackground(Color.BLACK);

        scaledPanel = new ScaledPanel();
        super.add(scaledPanel);

        currentSilde = null;

        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scaledPanel.renderSlide(currentSilde);
            }
        });

        Presentation.addChangePresentationListener(this);
        Presentation.addUpdateSlideListener(this);
        Presentation.addDiscardSlideListener(this);

        instance = this;
    }

    public void showSlideAtIndex(int i)
    {
        currentSilde = Presentation.get().getSlideAtIndex(i);

        scaledPanel.renderSlide(currentSilde);
    }

    @Override
    public void onChangePresentation(Presentation current, Presentation discarded) {
        if (current != null && current.getSlideCount() > 0) {
            showSlideAtIndex(0);
            return;
        }

        currentSilde = null;
        scaledPanel.clearRender();
    }


    @Override
    public void onUpdateSlide(int index, Slide slide) {
        if (slide == currentSilde)
            scaledPanel.renderSlide(slide);
    }

    @Override
    public void onDiscardSlide(int index, Slide slide) {
        if (slide == currentSilde)
            showSlideAtIndex(index >= Presentation.get().getSlideCount() ? index - 1 : index);
    }

    public Slide getDisplayedSlide() {
        return currentSilde;
    }
}
