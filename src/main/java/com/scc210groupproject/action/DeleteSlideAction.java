package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.ui.SlideManager;

public class DeleteSlideAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {
        Presentation p = Presentation.getOrCreate();
        if (p.getSlideCount() > 1)
            p.removeSlide(SlideManager.instance.getCurrentSlide());
    }

}