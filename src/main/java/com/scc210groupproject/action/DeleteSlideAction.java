package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;
import com.scc210groupproject.ui.SlideManager;

public class DeleteSlideAction implements ActionListener, ICreateSlideListener, IDiscardSlideListener {

    private JButton button = null;

    public DeleteSlideAction() {
        Presentation.addCreateSlideListener(this);
        Presentation.addDiscardSlideListener(this);
    }

    public void setButton(JButton b) {
        button = b;
        updateButton();
    }

    private void updateButton() {
        Presentation p = Presentation.get();
        if (p == null) {
            button.setEnabled(false);
            return;
        }

        if(button != null) {
            button.setEnabled(p.getSlideCount() > 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent discard)
    {
        Presentation p = Presentation.get();
        if (p == null)
            return;

        if (p.getSlideCount() > 1)
            p.removeSlide(SlideManager.slideManager.getCurrentSlide());
    }



    @Override
    public void onDiscardSlide(int index, Slide slide) {
        updateButton();
    }

    @Override
    public void onCreateSlide(int index, Slide slide) {
        updateButton();
    }

}