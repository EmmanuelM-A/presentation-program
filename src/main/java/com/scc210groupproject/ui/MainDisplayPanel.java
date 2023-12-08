package com.scc210groupproject.ui;

import javax.swing.*;

import java.awt.*;

import com.scc210groupproject.structure.*;
import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;

public class MainDisplayPanel extends JPanel implements IChangePresentationListener, IDiscardSlideListener {

    public static MainDisplayPanel instance;

    private ScaledPanel scaledPanel;

    public MainDisplayPanel()
    {
        super();

        super.setLayout(new GridLayout(0, 1));
        super.setBackground(Color.BLACK);

        scaledPanel = new ScaledPanel();
        super.add(scaledPanel);

        Presentation.addChangePresentationListener(this);
        Presentation.addDiscardSlideListener(this);

        instance = this;
    }

    public void showSlideAtIndex(int i)
    {
        showSlide(Presentation.get().getSlideAtIndex(i));
    }

    public void showSlide(Slide slide)
    {
        scaledPanel.removeAll();
        scaledPanel.add(slide.asComp());
        scaledPanel.repaint();
        scaledPanel.validate();
    }

    @Override
    public void onChangePresentation(Presentation current, Presentation discarded)
    {
        showSlideAtIndex(0);
    }

    @Override
    public void onDiscardSlide(int index, Slide slide)
    {
        if (slide.asComp() == scaledPanel.getComponent(0))
            showSlideAtIndex(index >= Presentation.get().getSlideCount() ? index - 1 : index);
    }
}
