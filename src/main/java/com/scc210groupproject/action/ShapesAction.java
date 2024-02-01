package com.scc210groupproject.action;

import com.scc210groupproject.ui.ShapesPopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapesAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent _) {

        ShapesPopup.shapesPopup.popup.show();

    }
}
