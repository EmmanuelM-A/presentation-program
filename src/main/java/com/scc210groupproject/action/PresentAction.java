package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.PresentationMode;

public class PresentAction implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent discard){
        
        // Needs to be implemented
        new PresentationMode();

    }


}
