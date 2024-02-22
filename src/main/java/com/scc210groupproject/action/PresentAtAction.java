package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.presentations.PresentationMode;

public class PresentAtAction implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent discard){
        
        // Needs to be Implemented
        //new PresentationModeOne();
        new PresentationMode(SlideManager.slideManager.getCurrentSlideIndex());
    }

}
