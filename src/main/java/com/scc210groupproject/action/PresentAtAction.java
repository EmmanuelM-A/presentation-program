package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.presentations.PresentationManager;

public class PresentAtAction implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent discard){
        PresentationManager.instance.managePresentationMode(SlideManager.slideManager.getCurrentSlideIndex());
    }
}
