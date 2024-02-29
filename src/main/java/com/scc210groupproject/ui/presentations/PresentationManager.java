package com.scc210groupproject.ui.presentations;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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

    /**
     * The presention mode display panel
     */
    private PresentationDisplayPanel presentationDisplay;

    /**
     * An instance of this class
     */
    public static PresentationManager instance;

    /**
     * The constructor for the Presentatiobn Manager
     * @param frame The UI Frame
     * @param presentationDisplay The presentation display
     * @param startIndex The slide to present from
     */
    public PresentationManager(JFrame frame) {
        this.presentationDisplay = new PresentationDisplayPanel();

        this.frame = frame;

        instance = this;
    }

    public void managePresentationMode(int startIndex) {
        this.currentImageIndex = startIndex;

        PresentationMode.instance.createAndShowUI(presentationDisplay);

        this.slidesToPresent = populteSlidesToPresent(SlideManager.slideManager.getSlideImages());

        presentationDisplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextSlideOnClick(e);
            }
        });

        this.presentationDisplay.setInputState(false);

        // Next slide to present displayed on right arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
            put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "next");
        presentationDisplay.getActionMap().put("next", nextSlideOnClick);

        // Previous slide to present displayed on left arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "previous");
        presentationDisplay.getActionMap().put("previous", prevSlideOnClick);

        displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
    }


    public PresentationDisplayPanel getPresentationDisplay() {
        return this.presentationDisplay;
    }

    public LinkedList<SlideImage> populteSlidesToPresent(LinkedList<SlideImage> list) {
        LinkedList<SlideImage> slideImages = new LinkedList<>();

        for(int i = 0; i < list.size(); i++) {
            SlideImage slideImage = new SlideImage(list.get(i).getSlide(), presentationDisplay);

            slideImages.add(slideImage);
        }

        return slideImages;
    }

    /**
     * Display a slide image to the display
     * @param slideToDisplay The slide to add/draw onto the display
     * @param display The display that slide images will be added/drawn on
     */
    public void displaySlide(SlideImage slideToDisplay, PresentationDisplayPanel display) {
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
    
    /**
     * Allows the user to move onto the next slide on mouse click or exit presentation mode on double click
     * @param e The mouse event
     */
    public void nextSlideOnClick(MouseEvent e) {
        // Makes sure that the left mouse is clicked
        if(SwingUtilities.isLeftMouseButton(e)) {
            runActions(currentImageIndex);
            // Move to the next slide or exit the presentation mode on double click
            if(currentImageIndex < (slidesToPresent.size() - 1)) {
                currentImageIndex++;
                displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
            } else /*if (e.getClickCount() == 2 && !e.isConsumed())*/{
                //e.consume();
                this.frame.dispose();
            }
        }

        System.out.println(currentImageIndex);
    }

    /**
     * Used to go to the previous slide in presentation mode on the click of the left arrow button
     */
    Action prevSlideOnClick = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            runActions(currentImageIndex);            

            // Move to the previous slide
            if(currentImageIndex > 0) {
                currentImageIndex--;
                displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
            }
        }
    };

    /**
     * Used to go to the next slide in presentation mode on the click of the right arrow button
     */
    Action nextSlideOnClick = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            runActions(currentImageIndex);

            /**
             * Go to the next slide or exit presentation mode
             */
            if(currentImageIndex < (slidesToPresent.size() - 1)) {
                currentImageIndex++;
                displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
            } else {
                frame.dispose();
            }
        }
    };

    /**
     * Runs the elements' animations if there are elements present on the slide
     */
    public void runActions(int index) {
        List<PresentationActions> actions = this.slidesToPresent.get(index).getSlide().getActions();

        //System.out.println(actions);
        // Run the element animations if there are elements present on the screen
        if(actions.size() != 0) {
            for(PresentationActions action: actions) {
                action.doAction();
            }
        }
    }
}
