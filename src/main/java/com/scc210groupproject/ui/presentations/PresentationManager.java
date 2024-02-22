package com.scc210groupproject.ui.presentations;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.SlideImage;
import com.scc210groupproject.ui.SlideManager;

public class PresentationManager {
    /**
     * The list of the slide images to present 
     */
    private LinkedList<SlideImage> slidesToPresent;

    /**
     * The index of the current slide image being presented
     */
    private int currentImageIndex;

    /**
     * The ui frame 
     */
    private JFrame frame;

    private MainDisplayPanel presentationDisplay;

    public PresentationManager(JFrame frame, MainDisplayPanel presentationDisplay, int startIndex) {
        this.presentationDisplay = presentationDisplay;

        this.slidesToPresent = populteSlidesToPresent(SlideManager.slideManager.getSlideImages());

        this.currentImageIndex = startIndex;

        this.frame = frame;

        presentationDisplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextSlideOnClick();
            }
        });

        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowActivated(WindowEvent arg0) {}

            @Override
            public void windowClosed(WindowEvent arg0) { presentationDisplay.setInputState(true); }

            @Override
            public void windowClosing(WindowEvent arg0) {}

            @Override
            public void windowDeactivated(WindowEvent arg0) {}

            @Override
            public void windowDeiconified(WindowEvent arg0) {}

            @Override
            public void windowIconified(WindowEvent arg0) { }

            @Override
            public void windowOpened(WindowEvent arg0) { presentationDisplay.setInputState(false); }
            
        });

        // Next slide to present displayed on right arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
            put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "next");
        presentationDisplay.getActionMap().put("next", nextSlideOnClick);

        // Previous slide to present displayed on left arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "previous");
        presentationDisplay.getActionMap().put("previous", prevSlideOnClick);

        displaySlide(slidesToPresent.get(startIndex), presentationDisplay);
    }

    public LinkedList<SlideImage> populteSlidesToPresent(LinkedList<SlideImage> list) {
        LinkedList<SlideImage> slideImages = new LinkedList<>();

        for(int i = 0; i < list.size(); i++) {
            SlideImage slideImage = new SlideImage(list.get(i).getSlide(), presentationDisplay);

            slideImages.add(slideImage);
        }

        return slideImages;
    }

    public void displaySlide(SlideImage slideToDisplay, MainDisplayPanel display) {
        // Set the slideImage on the main display
        display.setCurrentSlideImage(slideToDisplay);
        // Remove previous slide displayed
        display.removeAll();
        // Display new slide
        display.add(slideToDisplay);
        // Repaint display
        display.revalidate();
        display.repaint();
        // Update slideImage dimension on frame resize
        display.updateBufferedSlideImage();
    }

    public void nextSlideOnClick() {
        if(currentImageIndex < (slidesToPresent.size() - 1)) {
            currentImageIndex++;
            displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
        } else {
            this.frame.dispose();
        }
    }

    Action prevSlideOnClick = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(currentImageIndex > 0) {
                currentImageIndex--;
                displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
            }
        }
    };

    Action nextSlideOnClick = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(currentImageIndex < (slidesToPresent.size() - 1)) {
                currentImageIndex++;
                displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
            } else {
                frame.dispose();
            }
        }
    };

    // Check if slide has actions
    // If so get those actions
    // On click run first action is actions
    // then incrment to the next action
    // If run again, run the next action in actions
    // keep track of current action
    public void runAction() {
        if(presentationDisplay.getCurrentSlideImage().getActions().size() > 0) {
            for(Integer action : presentationDisplay.getCurrentSlideImage().getActions()) {
                System.out.println("Action " + action + " completed!");
            }
        }
    }
}
