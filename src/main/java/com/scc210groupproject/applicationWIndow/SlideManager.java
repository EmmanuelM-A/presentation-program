package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * This class handles the displaying and transversing of slides on the main display and on the presentation slider.
 * IMPORTANT NOTES:
 * - FOR NOW THE SLIDES ARE REPRESENTED AS JPANELS RATHER THAN INSTANCES OF THE SLIDE CLASS WILL CHANGE WHEN FUNCTIONALITY IS COMPLETE.
 * - SLIDES ARE ADDED DIRECTLY TO THE APPLICATION FRAME CAN BE CHANGED LATER TO BE ADDED TO MAIN DISPLAY.
 * - ON LOAD OF THE PROGRAM THERE IS ONE SLIDE PRESENT SLIDE 1
 *
 * @author madukaag
 * */
public class SlideManager {
    private JPanel slide = new JPanel(); // Represent slides
    private LinkedList<JPanel> slides; // Where slides are stored in order
    private LinkedList<JButton> presentationSliderSlides; // This list represents the slides on the presentation slider
    private int currentSlide; // The current slide on
    private int currentSlideIndex; // The current index of the slide
    private JPanel firstSlide; // This slide is always present on load
    private JScrollPane presentationSlider; // The alternate display where all slides can be displayed, selected and scrolled through
    private JPanel sliderPanel;
    private JFrame applicationFrame; // The application window
    public SlideManager(final JFrame frame) {
        this.slides = new LinkedList<>();
        this.presentationSliderSlides = new LinkedList<>();
        this.currentSlide = 1; // The current slide being displayed
        this.currentSlideIndex = 0; // The index of the current slide being displayed
        this.applicationFrame = frame;
        this.sliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.presentationSlider = new JScrollPane(sliderPanel);

        addFirstSlide();

        /*
         * The JPanel that contains the components that make up the presentation slider:
         * previous slide button, next slide button, main view (the actual presentation slider) and
         * the bottom section which will hold a bunch of buttons.
         * */
        JPanel presentationSliderPanel = new JPanel(new BorderLayout());
        presentationSliderPanel.setPreferredSize(new Dimension(1000, 180));
        presentationSliderPanel.setBackground(Color.YELLOW);

        /*
         * Previous slide button
         * */
        JButton prevSlide = new JButton("<");
        prevSlide.setPreferredSize(new Dimension(50, 160));
        prevSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showPrevSlide();
            }
        });

        /*
         * Next slide button
         * */
        JButton nextSlide = new JButton(">");
        nextSlide.setPreferredSize(new Dimension(50, 160));
        nextSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showNextSlide();
            }
        });

        /*
         * This is where the actual presentation slider will go
         * */
        this.sliderPanel.setBackground(Color.green);

        this.presentationSlider.setPreferredSize(new Dimension(1000, 160));
        this.presentationSlider.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        /*
         * Bottom Section of the presentation slider where buttons such as newSlide, deleteSlide,
         * etc. will be placed. As well as a number of slides display.
         * */
        JPanel bottomSection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomSection.setPreferredSize(new Dimension(1000, 35));
        bottomSection.setBackground(Color.blue);

        JButton addNewSlide = new JButton("New Slide");
        addNewSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                addNewSlide();
                //addSlideToPresentationSlider();
            }
        });
        bottomSection.add(addNewSlide);

        /*
         * Adds each section/button onto the display
         * */
        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(this.presentationSlider, BorderLayout.CENTER);
        presentationSliderPanel.add(bottomSection, BorderLayout.SOUTH);

        /*
         * Adds the presentationSlider (An instance of SlideManager class) directly onto the application frame window
         * */
        frame.add(presentationSliderPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds the first slide to the display on startup of the program
     * */
    private void addFirstSlide() {
        firstSlide = new JPanel();
        firstSlide.setBackground(Color.ORANGE);

        Integer firstPSSlideNo = presentationSliderSlides.size() + 1;
        JButton firstPSSlide = createPSSlide(firstPSSlideNo);
        presentationSliderSlides.add(firstPSSlide);
        sliderPanel.add(firstPSSlide);

        this.slides.add(firstSlide);

        JLabel slideNo = new JLabel("Slide " + (slides.size()));
        slideNo.setFont(new Font("Arial", Font.BOLD, 24));
        firstSlide.add(slideNo);

        applicationFrame.add(firstSlide, BorderLayout.CENTER);
        System.out.println("New Slide - " + (slides.size()) + "!");
    }

    /**
     * Displays the previous slide in the presentation slider given that there is at least one slide
     * */
    private void showPrevSlide() {
        /*
         * Get the previous slide if there is one
         * Remove the current slide from the display
         * Set the previous slide to the display
         * */
        if(this.currentSlide > 1) {
            //System.out.println("Was on Slide " + currentSlide);
            JPanel currentSlide = getCurrentSlide();
            this.currentSlide--;
            this.currentSlideIndex--;
            JPanel prevSlide = getPrevSlide(currentSlide);

            if(prevSlide != null) {
                applicationFrame.remove(currentSlide);
                applicationFrame.add(prevSlide, BorderLayout.CENTER);
                applicationFrame.revalidate();
                applicationFrame.repaint();
                //System.out.println("Previous Slide - On Slide " + this.currentSlide + "!");
                //System.out.println("Current slide Index: " + this.currentSlideIndex);
            } else {
                System.out.println("PROBLEM!");
            }
        } else {
            System.out.println("First slide reached!");
        }
    }

    /**
     * Displays the next slide in the presentation slider given that it has not reached the last slide
     * */
    private void showNextSlide() {
        /*
         * Get the next slide if there is one
         * Remove the current slide from the display
         * Set the next slide to the display
         * */
        if(this.currentSlide < slides.size()) {
            //System.out.println("Was on Slide " + this.currentSlide);

            JPanel currentSlide = getCurrentSlide();
            this.currentSlide++;
            this.currentSlideIndex++;
            JPanel nextSlide = getNextSlide(currentSlide);

            if(nextSlide != null) {
                applicationFrame.remove(currentSlide);
                applicationFrame.add(nextSlide, BorderLayout.CENTER);
                applicationFrame.revalidate();
                applicationFrame.repaint();
                //System.out.println("Next Slide - On Slide " + this.currentSlide + "!");
                //System.out.println("Current slide Index: " + this.currentSlideIndex);
            } else {
                System.out.println("PROBLEM!");
            }
        } else {
            System.out.println("Last slide reached!");
        }
    }

    /**
     * Displays the selected slide on the main display
     * @param slide The slide to display
     * @param display The display the slide will be added to
     * */
    private void showSlide(JPanel slide, JFrame display) {
        /*
        * Get the selected slide
        * Get the current slide displayed
        * Remove the current slide from the display
        * Set the selected slide to the display
        * */
    }

    /**
     * Creates a new slide and assigns it a slide number, then adds the slide onto the end of the existing slides
     * and finally display the new slide onto the screen.
     * */
    private void addNewSlide() {
        /*
         * Create a new slide
         * Create a new presentation slider slide (PS slide)
         * Assign each with the same slide number (label for now)
         * Add slide to main display
         * Add PS slide to slider
         * Display new slide on the display - OPTIONAL
         * */

        // Create new slide JPanel (FOR NOW)
        JPanel newSlide = new JPanel();
        newSlide.setBackground(Color.LIGHT_GRAY);

        // Create a new presentation slider slide (PS slide) and assign its number
        Integer slideNum = presentationSliderSlides.size() + 1;
        JButton newPSSlide = createPSSlide(slideNum);

        // Assign the slide its slide number
        JLabel slideNo = new JLabel("Slide " + (slides.size() + 1));
        slideNo.setFont(new Font("Arial", Font.BOLD, 24));
        newSlide.add(slideNo);

        // Add the slide onto the end of slide linked list (slider)
        slides.add(newSlide);
        //System.out.println("New Slide - " + (slides.size()) + "!");

        // Add PS slide to presentation slider linked list
        this.presentationSliderSlides.add(newPSSlide);

        // Add PS slide to slider
        this.sliderPanel.add(newPSSlide);
        this.sliderPanel.revalidate();

        // The code below changes the main display to the new slide added
        // There is only one slide currently being displayed - the first slide
        if(slides.size() == 1) {
            // Remove first slide
            applicationFrame.remove(firstSlide);
            // Add new slide
            applicationFrame.add(newSlide, BorderLayout.CENTER);
            // Repaint frame
            applicationFrame.revalidate();
            applicationFrame.repaint();

            // Increment slide index to current slide added
            this.currentSlide = slides.size();
            this.currentSlideIndex = slides.size() - 1;

            System.out.println("New Slide - " + (slides.size()) + "!");
        } else if (slides.size() > 1) { // There is more than one slide present
            // Get current slide
            JPanel currentSlide = getCurrentSlide();

            // Check if slide exists
            if(currentSlide != null) {
                // Remove current slide displayed
                applicationFrame.remove(currentSlide);
                // Add new slide
                applicationFrame.add(newSlide, BorderLayout.CENTER);
                // Repaint frame
                applicationFrame.revalidate();
                applicationFrame.repaint();

                // Increment slide index to current slide added
                this.currentSlide = slides.size();
                this.currentSlideIndex = slides.size() - 1;

                System.out.println("New Slide - " + (slides.size()) + "!");
            } else {
                System.out.println("PROBLEM IN ADD_SLIDE");
            }
        }
    }

    /**
     * Returns the slide before the selected slide if there is none it returns null.
     * @param slide The current slide
     * */
    private JPanel getPrevSlide(JPanel slide) {
        int indexOfSelectedSlide = slides.indexOf(slide);
        int indexOfPrevSlide = indexOfSelectedSlide - 1;
        if(indexOfPrevSlide >= 0) {
            return slides.get(indexOfPrevSlide);
        } else {
            return null;
        }
    }

    /**
     * Returns the slide after the selected slide if there is none it returns null.
     * @param slide The current slide
     * */
    private JPanel getNextSlide(JPanel slide) {
        int indexOfSelectedSlide = slides.indexOf(slide);
        int indexOfNextSlide = indexOfSelectedSlide + 1;

        if(indexOfNextSlide <= slides.size()) {
            return slides.get(indexOfNextSlide);
        } else {
            return null;
        }
    }

    /**
     * Returns the current slide being displayed
     * */
    private JPanel getCurrentSlide() {
        if(this.currentSlide >= 1 && this.currentSlide <= this.slides.size()) {
            return slides.get(this.currentSlideIndex);
        } else {
            return null;
        }
    }

    /**
     * Adds the new slide (represented as buttons) to the presentation slider
     * */
    /*private void addSlideToPresentationSlider() {
        /*
        * Create a slide (button)
        * Assign the button a no number
        * Add to presentation slider
        * */
        /*Integer slideNum = presentationSliderSlides.size() + 1;

        JButton newPSSlide = createPSSlide(slideNum);

        this.presentationSliderSlides.add(newPSSlide);

        this.sliderPanel.add(newPSSlide);

        this.sliderPanel.revalidate();
    }*/

    /**
     * Creates a PS slide
     * @param slideNo The slide number to assign it to
     * @return JButton (PS Slide)
     * */
    private JButton createPSSlide(Integer slideNo) {
        JButton newPSSlide = new JButton("Slide " + slideNo);

        newPSSlide.setPreferredSize(new Dimension(200, 120));
        newPSSlide.setBackground(Color.white);
        newPSSlide.setFocusable(false);

        int ps = this.currentSlide;
        newPSSlide.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displaySelectedSlide(slideNo);
            }
        });

        return newPSSlide;
    }

    /**
     * Add a slide after the selected slide
     * @param selectedSlide The selected slide
     * */
    private void addSlideAfter(JPanel selectedSlide) {}

    /**
     * Add a slide before the selected slide
     * @param selectedSlide The selected slide
     * */
    private void addSlideBefore(JPanel selectedSlide) {}

    /**
     * Gets the selected slide
     * @param slideNo The slide number
     * @return JPanel
     * */
    private JPanel getSelectedSlide(Integer slideNo) {
        JPanel selectedSlide = slides.get(slideNo - 1);
        return selectedSlide;
    }

    /**
     * Displays the selected slide to the main display
     * */
    private void displaySelectedSlide(int slideNo) {
        System.out.println(slideNo);
        System.out.println("Previous slide: " + this.currentSlide);
        JPanel currentSlide = getCurrentSlide();
        JPanel selectedSlide = getSelectedSlide(slideNo);
        this.currentSlide = slideNo;
        this.currentSlideIndex = slideNo - 1;
        System.out.println("Current slide: " + this.currentSlide);

        // Remove first slide
        applicationFrame.remove(currentSlide);
        // Add new slide
        applicationFrame.add(selectedSlide, BorderLayout.CENTER);
        // Repaint frame
        applicationFrame.revalidate();
        applicationFrame.repaint();
    }


}
