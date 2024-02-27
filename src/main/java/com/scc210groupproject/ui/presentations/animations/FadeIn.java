package com.scc210groupproject.ui.presentations.animations;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.scc210groupproject.ui.presentations.PresentationActions;

public class FadeIn extends PresentationActions {
    private int interval;

    private int alpha;

    private Timer timer;

    public FadeIn(int interval) {
        this.interval = interval;

        this.alpha = 0;
    }

    @Override
    public void doAction() {
        // Get the colour of the element selected
        Color colour = selectedElement.getBackground();

        timer = new Timer(interval, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                selectedElement.setBackground(new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), alpha));
                selectedElement.asComp().repaint();

                alpha += 5;

                if(alpha >= 255) {
                    timer.stop();
                }

                if(alpha >= 255) alpha = 255;
            }
        });

        timer.start();
    }
}
