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
    private int currentSlideIndex; // The current slide on
    private JPanel firstSlide; // This slide is always present on load
    private JFrame applicationFrame; // The application window
    public SlideManager(final JFrame frame) {
        this.slides = new LinkedList<>();
        this.currentSlideIndex = 1; // There is always one slide present on load
        this.applicationFrame = frame;

        addFirstSlide();

        /**
         * The JPanel that contains the components that make up the presentation slider:
         * previous slide button, next slide button, main view (the actual presentation slider) and
         * the bottom section which will hold a bunch of buttons.
         * */
        JPanel presentationSliderPanel = new JPanel(new BorderLayout());
        presentationSliderPanel.setPreferredSize(new Dimension(1000, 180));
        presentationSliderPanel.setBackground(Color.YELLOW);

        /**
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

        /**
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

        /**
         * This is where the actual presentation slider will go
         * */
        JPanel mainView = new JPanel();
        mainView.setPreferredSize(new Dimension(1000, 160));
        mainView.setBackground(Color.green);

        /**
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
                addSlide();
            }
        });
        bottomSection.add(addNewSlide);

        /**
         * Adds each section/button onto the display
         * */
        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(mainView, BorderLayout.CENTER);
        presentationSliderPanel.add(bottomSection, BorderLayout.SOUTH);

        /**
         * Adds the presentationSlider (SlideManager directly onto the application frame window)
         * */
        frame.add(presentationSliderPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds the first slide to the display on startup of the program
     * */
    private void addFirstSlide() {
        firstSlide = new JPanel();
        firstSlide.setBackground(Color.ORANGE);
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
        /**
         * Get the previous slide if there is one
         * Remove the current slide from the display
         * Set the previous slide to the display
         * */
        if(currentSlideIndex > 1) {
            JPanel currentSlide = getCurrentSlide();
            currentSlideIndex--;
            JPanel prevSlide = getPrevSlide(currentSlide);

            if(prevSlide != null) {
                applicationFrame.remove(currentSlide);
                applicationFrame.add(prevSlide, BorderLayout.CENTER);
                applicationFrame.revalidate();
                applicationFrame.repaint();
                System.out.println("Previous Slide!");
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
        /**
        * Get the next slide if there is one
        * Remove the current slide from the display
        * Set the next slide to the display
        * */
        if(currentSlideIndex < slides.size()) {
            JPanel currentSlide = getCurrentSlide();
            currentSlideIndex++;
            JPanel nextSlide = getNextSlide(currentSlide);

            if(nextSlide != null) {
                applicationFrame.remove(currentSlide);
                applicationFrame.add(nextSlide, BorderLayout.CENTER);
                applicationFrame.revalidate();
                applicationFrame.repaint();
                System.out.println("Next Slide!");
            } else {
                System.out.println("PROBLEM!");
            }
        } else {
            System.out.println("Last slide reached!");
        }
    }

    /**
     * Displays the selected slide on the main display
     * */
    private void showSlide(JPanel slide) {
        /*if(slides.size() == 1) { // Means the only slide displayed is the default/first slide
            applicationFrame.remove(mainDisplay);
            applicationFrame.add(slide, BorderLayout.CENTER);
        } else if(slides.size() > 1) { // Means there is more than one slide present
            JPanel currentSlideDisplayed = slides.get(slides.size() - 1);
            applicationFrame.remove(currentSlideDisplayed);
            applicationFrame.add(slide, BorderLayout.CENTER);
            System.out.println("Point 2!");
        } else {
            // There is only
            applicationFrame.add(slide, BorderLayout.CENTER);
            System.out.println("Point 1!");
        }*/
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
    private void addSlide() {
        // Create new slide JPanel (FOR NOW)
        JPanel newSlide = new JPanel();
        newSlide.setBackground(Color.LIGHT_GRAY);

        // Assign the slide its slide number
        JLabel slideNo = new JLabel("Slide " + (slides.size() + 1));
        slideNo.setFont(new Font("Arial", Font.BOLD, 24));
        newSlide.add(slideNo);

        // Add the slide onto the end of slide linked list (slider)
        slides.add(newSlide);

        // Change the main display to the new slide added
        if(currentSlideIndex == 1) { // There is only one slide currently being displayed - the first slide
            applicationFrame.remove(firstSlide);
            applicationFrame.add(newSlide, BorderLayout.CENTER);
            applicationFrame.revalidate();
            applicationFrame.repaint();

            System.out.println("New Slide - " + (slides.size()) + "!");
        } else if (currentSlideIndex > 1) {
            //JPanel currentSlide = getCurrentSlide();
            JPanel currentSlide = slides.get(currentSlideIndex); // PROBLEM SOMEWHERE HERE

            if(currentSlide != null) {
                applicationFrame.remove(currentSlide);
                applicationFrame.add(newSlide, BorderLayout.CENTER);
                applicationFrame.revalidate();
                applicationFrame.repaint();

                System.out.println("New Slide - " + (slides.size()) + "!");
            } else {
                System.out.println("PROBLEM IN ADD_SLIDE");
            }

        }
        currentSlideIndex++;
        /*
         * Create a new slide
         * Assign the slide with a slide number (label)
         * Add slide to slides linked list
         * Display slide on the display - OPTIONAL
         * */
    }

    /**
     * Returns the slide before the selected slide if there is none it returns null.
     * @param slide The current slide
     * */
    private JPanel getPrevSlide(JPanel slide) {
        int indexOfSelectedSlide = slides.indexOf(slide);
        int indexOfPrevSlide = indexOfSelectedSlide - 1;

        if(indexOfPrevSlide > 0) {
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
            return slides.get(indexOfNextSlide - 1); // I'M NOT SURE WHY THIS WORKS
        } else {
            return null;
        }
    }

    /**
     * Returns the current slide being displayed
     * */
    private JPanel getCurrentSlide() {
        if(currentSlideIndex > 0 && currentSlideIndex < slides.size()) {
            return slides.get(currentSlideIndex);
        } else {
            return null;
        }
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
     * Returns the selected slide
     * */
    private JPanel getSelectedSlide() {
        return null;
    }


}
