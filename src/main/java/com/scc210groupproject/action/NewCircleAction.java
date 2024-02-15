package com.scc210groupproject.action;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.CircleElement;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.ui.MainDisplayPanel;

public class NewCircleAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent discard)
    {
        Slide slide = MainDisplayPanel.instance.getCurrentSlideImage().getSlide();
        Dimension area = slide.asComp().getSize();

        CircleElement element = new CircleElement();

        element.setBackground(new Color(
            (int)(Math.random() * 255), 
            (int)(Math.random() * 255), 
            (int)(Math.random() * 255)));

        int minSide = area.width < area.height ? area.width : area.height;
        int size = (int)(Math.random() * minSide / 2) + minSide / 2;
        element.setSize(new Dimension(size, size));

        element.setLocation(new Point(
            (int)(Math.random() * (area.width - size)),
            (int)(Math.random() * (area.height - size))));

        slide.add(element);
    }

}