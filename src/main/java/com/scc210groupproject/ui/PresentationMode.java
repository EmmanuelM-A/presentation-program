package com.scc210groupproject.ui;


import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.scc210groupproject.action.NextSlideAction;
import com.scc210groupproject.action.PrevSlideAction;

public class PresentationMode extends JFrame {
    /*
     * Open a new frame
     * copy the functionality of the main display panel but have so it covers the whole screen instead
     * each slide is assigned a list of actions
     * actions are the events that happen to the slide like animations or transisitios
     * next slide and previous slide buttons and arrow keys as well
     * if there are no actions for the current slide move onto the next
     * 
     */

    MainDisplayPanel presentationDisplay = MainDisplayPanel.instance;

    public PresentationMode() {
        this.setTitle("Presentation Mode");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        presentationDisplay.setPreferredSize(new Dimension(super.getWidth(), super.getHeight()));

        presentationDisplay.setInputState(false);

        presentationDisplay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new NextSlideAction();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
            }
        });

        // Next slide displayed on right arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
            put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "next");
        presentationDisplay.getActionMap().put("next", new NextSlideAction());

        // Previous slide displayed on left arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "previous");
        presentationDisplay.getActionMap().put("previous", new PrevSlideAction());
        
        this.add(presentationDisplay);

        setVisible(true);
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            presentationDisplay.setInputState(true);
        }
    }

    /*
     * Present At:
     * - Display presentation mode
     * - 
     */

    public void nextSlideOnClick() {
        SlideManager.slideManager.showNextSlide();
    }

    public void prevSlideOnClick() {
        SlideManager.slideManager.showPrevSlide();
    }

    /*
     * Check if slide has actions
     * If so get those actions
     * On click run first action is actions
     * then incrment to the next action
     * If run again, run the next action in actions
     * keep track of current action
     */
    public void runAction() {
        if(presentationDisplay.getCurrentSlideImage().getActions().size() > 0) {
            for(Integer action : presentationDisplay.getCurrentSlideImage().getActions()) {
                System.out.println("Action " + action + " completed!");
            }
        }
    }

}
