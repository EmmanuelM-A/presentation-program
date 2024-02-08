package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.testui.MainDisplayPanel;

public class GoToSlideAction implements ActionListener {
    public int index;

    public GoToSlideAction(int index) {
        this();
        this.index = index;
    }

    private GoToSlideAction() {}

    @Override
    public void actionPerformed(ActionEvent discard) {
        MainDisplayPanel.instance.showSlideAtIndex(index);
    }


}
