package com.scc210groupproject.action;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.BoxElement;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.ui.MainDisplayPanel;

public class NewBoxAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent discard)
    {
        Slide slide = MainDisplayPanel.instance.getCurrentSlideImage().getSlide();
        Dimension area = slide.asComp().getSize();

        BoxElement element = new BoxElement();

        element.setBackground(new Color(
            (int)(Math.random() * 255), 
            (int)(Math.random() * 255), 
            (int)(Math.random() * 255)));

        Dimension dimension = new Dimension(
            (int)(Math.random() * area.width / 2) + area.width / 2, 
            (int)(Math.random() * area.height / 2) + area.height / 2);
        element.setSize(dimension);

        element.setLocation(new Point(
            (int)(Math.random() * (area.width - dimension.width)),
            (int)(Math.random() * (area.height - dimension.height))));

        slide.add(element);
    }

}