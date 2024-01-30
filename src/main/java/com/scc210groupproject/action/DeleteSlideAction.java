package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.testui.MainDisplayPanel;
import com.scc210groupproject.ui.SlideManager;

public class DeleteSlideAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {
        Presentation.getOrCreate().removeSlide(SlideManager.instance.getCurrentSlide());
    }

}