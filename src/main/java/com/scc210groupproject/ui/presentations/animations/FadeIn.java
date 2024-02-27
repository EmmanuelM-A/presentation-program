package com.scc210groupproject.ui.presentations.animations;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Timer;

public class FadeIn extends Animation {
    private int interval;

    private int alpha;

    private Timer timer;

    public FadeIn(int interval) {
        this.interval = interval;

        this.alpha = 0;
    }

    @Override
    public void doAnimation() {
        Color colour = selectedElement.getBackground();
        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(alpha > 255) alpha = 255;

                selectedElement.setBackground(new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), alpha));
                selectedElement.asComp().repaint();

                alpha += 15;

                if(alpha > 255) {
                    timer.purge();
                }
            }
        }, 0, interval);*/

        timer = new Timer(interval, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(alpha > 255) alpha = 255;

                selectedElement.setBackground(new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), alpha));
                selectedElement.asComp().repaint();

                alpha += 15;

                if(alpha >= 255) {
                    timer.stop();
                }
            }
        });

        timer.start();
    }
}
