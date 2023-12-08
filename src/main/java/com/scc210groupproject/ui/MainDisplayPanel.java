package com.scc210groupproject.ui;

import javax.swing.*;

import java.awt.*;

import com.scc210groupproject.structure.*;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;

public class MainDisplayPanel extends JPanel implements ICreateSlideListener {

    public static MainDisplayPanel instance;

    private ScaledPanel scaledPanel;

    public MainDisplayPanel()
    {
        super();

        super.setLayout(new GridLayout(0, 1));
        super.setBackground(Color.BLACK);

        scaledPanel = new ScaledPanel();
        super.add(scaledPanel);

        Presentation.addCreateSlideListener(this);

        instance = this;
    }

    public void showSlideAtIndex(int i)
    {
        showSlideAtIndex(Presentation.current.getSlideAtIndex(i));
    }

    public void showSlideAtIndex(Slide slide)
    {
        JComponent component = slide.getComponent();

        scaledPanel.removeAll();
        scaledPanel.add(component);
        scaledPanel.validate();

        scaledPanel.setContentSize(component.getSize());
    }

    @Override
    public void onCreateSlide(int index, Slide slide) {
        showSlideAtIndex(slide);
    }
}
