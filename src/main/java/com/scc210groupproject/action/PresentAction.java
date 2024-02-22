package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.presentations.PresentationModeOne;
import com.scc210groupproject.ui.presentations.PresentationModeTwo;

public class PresentAction implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent discard){
        
        // Needs to be implemented
        //new PresentationModeOne();
        new PresentationModeTwo(0);

    }


}
