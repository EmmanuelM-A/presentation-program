package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.presentations.PresentationModeTwo;
import com.scc210groupproject.ui.presentations.PresentationMode;

public class PresentAction implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent discard){
        //new PresentationModeTwo(0);
        new PresentationMode(0);

    }


}
