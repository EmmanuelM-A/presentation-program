package com.scc210groupproject.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PresentationMode extends JFrame /*implements ActionListener*/ {
    /*
     * Open a new frame
     * copy the functionality of the main display panel but have so it covers the whole screen instead
     * each slide is assigned a list of actions
     * actions are the events that happen to the slide like animations or transisitios
     * next slide and previous slide buttons and arrow keys as well
     * if there are no actions for the current slide move onto the next
     * 
     */

    private ArrayList<SlideImage> slidesToPresent;

    private int currentSlidePresentedIndex = 0;

    private JPanel presentionModePanel = new JPanel(new BorderLayout());

    public PresentationMode() {
        this.setTitle("Presentation Mode");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        presentionModePanel.setPreferredSize(new Dimension(super.getWidth(), super.getHeight()));
        presentionModePanel.setBackground(Color.BLUE);

        populateSlidesToPresent();

        //presentionModePanel.add(slidesToPresent.get(currentSlidePresentedIndex), BorderLayout.CENTER);

        //MainDisplayPanel presentationDisplay = MainDisplayPanel.instance;

        //presentationDisplay.setPreferredSize(new Dimension(super.getWidth(), super.getHeight()));

        //presentionModePanel

        /*this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextSlideOnClick();
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
        });*/

        this.add(presentionModePanel);

        //this.add(slidesToPresent.get(currentSlidePresentedIndex));
        
        //this.add(presentationDisplay);

        setVisible(true);
    }

    /*
     * Present At:
     * - Display presentation mode
     * - 
     */

    public void nextSlideOnClick() {
        //SlideManager.slideManager.showNextSlide();
        if(currentSlidePresentedIndex <= SlideManager.slideManager.getSlideCount()) {
            this.removeAll();

            currentSlidePresentedIndex++;

            this.add(this.slidesToPresent.get(currentSlidePresentedIndex));
        }

    }

    public void previousSlideOnClick() {

    }

    public void runAction() {

    }

    private void populateSlidesToPresent() {
        this.slidesToPresent = new ArrayList<>();

        for(int i = 0; i < SlideManager.slideManager.getSlideCount(); i++) {
            SlideImage slideToPresent = SlideManager.slideManager.getSlideImageAt(i);

            slideToPresent.setPreferredSize(new Dimension(presentionModePanel.getWidth(), presentionModePanel.getHeight()));

            this.slidesToPresent.add(slideToPresent);
        }
    }
}
