package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.testui.MainDisplayPanel;

public class DeleteSlideAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {
        Presentation presentation = Presentation.getOrCreate();
        Slide slide = MainDisplayPanel.instance.getDisplayedSlide(); // using test ui (everywhere)
        presentation.removeSlide(slide);
    }

}