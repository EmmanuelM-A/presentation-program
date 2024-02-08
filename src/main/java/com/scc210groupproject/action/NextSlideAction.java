package com.scc210groupproject.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.scc210groupproject.ui.SlideManager;

public class NextSlideAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        // SlideManager.instance.getIndex();

        // get the slide manager instance
        // get index from slide manager
        // do bound checks
        // move functionality
        
        SlideManager.instance.showNextSlide();

    }
}
