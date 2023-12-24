package com.scc210groupproject.action;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.SampleElement;
import com.scc210groupproject.ui.MainDisplayPanel;

public class NewBoxAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {
        SampleElement sampleElement = new SampleElement();
        sampleElement.setBackground(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
        sampleElement.setLocation(new Point((int)(Math.random() * 400), (int)(Math.random() * 400)));
        sampleElement.setSize(new Dimension((int)(Math.random() * 75) + 25, (int)(Math.random() * 75) + 25));

        Presentation.getOrCreate().getSlideAtIndex(MainDisplayPanel.instance.getDisplayedIndex()).add(sampleElement);
    }

}