package com.scc210groupproject.action;

import com.scc210groupproject.ui.ShapesPopup;
import com.scc210groupproject.ui.UIFrame;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapesAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent discard) {

        Point location = UIFrame.instance.getLocationOnScreen();
        ShapesPopup.popupMenu.show(UIFrame.instance, location.x, location.y);

    }
}
