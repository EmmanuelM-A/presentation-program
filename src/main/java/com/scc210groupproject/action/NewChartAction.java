package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.structure.Chart;


public class NewChartAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {

        SlideManager.slideManager.getCurrentSlide().add(new Chart());

    }
    
}