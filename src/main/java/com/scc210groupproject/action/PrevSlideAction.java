package com.scc210groupproject.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.scc210groupproject.ui.SlideManager;

public class PrevSlideAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {        
        SlideManager.slideManager.showPrevSlide();

    }
}
