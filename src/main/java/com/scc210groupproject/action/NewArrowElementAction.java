package com.scc210groupproject.action;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.structure.ArrowElement;

public class NewArrowElementAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e)
    {
        ArrowElement arrowElement = new ArrowElement(new Point(20, 20), new Point(100, 200));
        arrowElement.setArrow(ArrowElement.Side.A, true, 20, 20);
        arrowElement.setArrow(ArrowElement.Side.B, true, 30, 30);
        arrowElement.setColor(Color.GREEN);
        arrowElement.setLine(false, 5, 5);
        MainDisplayPanel.instance.getCurrentSlideImage().getSlide().add(arrowElement);
    }
    
}
