package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.ui.MainDisplayPanel;

public class DeleteFirstAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {
        Slide slide = MainDisplayPanel.instance.getDisplayedSlide();
        slide.getChildren().get(0).destroy();
    }

}