package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.SlideManager;

public class InsertSlideBeforeAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent discard) {
        SlideManager.slideManager.addSlideBefore();
    }

}
