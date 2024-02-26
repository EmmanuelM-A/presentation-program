package com.scc210groupproject.ui.presentations;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

import com.itextpdf.kernel.pdf.annot.da.ExtendedAnnotationFont;
import com.scc210groupproject.structure.ExtendedElement;
import com.scc210groupproject.structure.ExtendedElement;
import com.scc210groupproject.ui.SlideImage;
import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.presentations.animations.Animation;

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
     * The presention mode display
     */
    private PresentationDisplayPanel presentationDisplay;

    private ExtendedElement selectedElement = null;

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

    public ExtendedElement getSelectedElement() {
        return this.selectedElement;
    }

    public PresentationDisplayPanel getPresentationDisplay() {
        return this.presentationDisplay;
    }

    public void setSelectedElement(ExtendedElement newSelectedElement) {
        this.selectedElement = (ExtendedElement) newSelectedElement;
        //System.out.println("The selected element is " + newSelectedElement);
    }

    public LinkedList<SlideImage> populteSlidesToPresent(LinkedList<SlideImage> list) {
        LinkedList<SlideImage> slideImages = new LinkedList<>();

        for(int i = 0; i < list.size(); i++) {
            SlideImage slideImage = new SlideImage(list.get(i).getSlide(), presentationDisplay);

            slideImages.add(slideImage);
        }

        return slideImages;
    }

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

    public void nextSlideOnClick(MouseEvent e) {
        //runAnimations();
        if(currentImageIndex < (slidesToPresent.size() - 1)) {
            currentImageIndex++;
            displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
        } else if (e.getClickCount() == 2 && !e.isConsumed()){
            e.consume();
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
            //runAnimations();
            ArrayList<Animation> animations = presentationDisplay.getCurrentSlideImage().getSlide().getElementAnimations();

                // Check if there are elements present
                if(animations.size() != 0) {
                    // If so run each element's animation if they have one until all have run 
                    for(Animation animation : animations) {
                        animation.doAnimation();
                    }
                }
            /*if(currentImageIndex < (slidesToPresent.size() - 1)) {
                ArrayList<Animation> animations = presentationDisplay.getCurrentSlideImage().getSlide().getElementAnimations();

                // Check if there are elements present
                if(animations.size() != 0) {
                    // If so run each element's animation if they have one until all have run 
                    for(Animation animation : animations) {
                        animation.doAnimation();
                    }
                }
                currentImageIndex++;
                //runAnimations();
                displaySlide(slidesToPresent.get(currentImageIndex), presentationDisplay);
            } else {
                frame.dispose();
            }*/
        }
    };

    public void runAnimations() {
        // Get elements on the current slide being presented
        ArrayList<Animation> animations = presentationDisplay.getCurrentSlideImage().getSlide().getElementAnimations();

        // Check if there are elements present
        if(animations.size() != 0) {
            // If so run each element's animation if they have one until all have run 
            for(Animation animation : animations) {
                animation.doAnimation();
            }
        }
    }
}
