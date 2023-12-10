package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * This class handles the displaying and transversing of slides on the main display and on the presentation slider.
 * FOR NOW THE SLIDES ARE REPRESENTED AS JPANELS RATHER THAN INSTANCES OF THE SLIDE CLASS
 * */
public class SlideManager {
    private JPanel slide = new JPanel();
    private LinkedList<JPanel> slides;
    private int currentSlideIndex;
    private JPanel firstSlide; // This slide is always present on load
    private JFrame applicationFrame;
    public SlideManager(final JFrame frame) {
        slides = new LinkedList<>();

        // There is always one slide present on load
        firstSlide = new JPanel();
        firstSlide.setBackground(Color.ORANGE);
        currentSlideIndex = 1;
        this.slides.add(firstSlide);

        this.applicationFrame = frame;

        applicationFrame.add(firstSlide, BorderLayout.CENTER);
        System.out.println("New Slide - " + (slides.size()) + "!");

        JPanel presentationSliderPanel = new JPanel(new BorderLayout());
        presentationSliderPanel.setPreferredSize(new Dimension(1000, 180));
        presentationSliderPanel.setBackground(Color.YELLOW);

        JButton prevSlide = new JButton("<");
        prevSlide.setPreferredSize(new Dimension(50, 160));
        prevSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showPrevSlide();
            }
        });

        JButton nextSlide = new JButton(">");
        nextSlide.setPreferredSize(new Dimension(50, 160));
        nextSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showNextSlide();
            }
        });

        JPanel mainView = new JPanel();
        mainView.setPreferredSize(new Dimension(1000, 160));
        mainView.setBackground(Color.green);

        JPanel bottom = getBottom();

        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(mainView, BorderLayout.CENTER);
        presentationSliderPanel.add(bottom, BorderLayout.SOUTH);

        frame.add(presentationSliderPanel, BorderLayout.SOUTH);
    }

    private JPanel getBottom() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setPreferredSize(new Dimension(1000, 35));
        bottom.setBackground(Color.blue);

        JButton addNewSlide = new JButton("New Slide");
        addNewSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addSlide();
            }
        });
        bottom.add(addNewSlide);
        return bottom;
    }

    /**
     * Displays the previous slide in the presentation slider given that there is at least one slide
     * */
    private void showPrevSlide() {
        /**
         *   - Get the previous slide if there is one
         *   - Remove the current slide from the display
         *   - Set the previous slide to the display
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
        *   - Get the next slide if there is one
        *   - Remove the current slide from the display
        *   - Set the next slide to the display
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
            JPanel currentSlide = getCurrentSlide();

            applicationFrame.remove(currentSlide);
            applicationFrame.add(newSlide, BorderLayout.CENTER);
            applicationFrame.revalidate();
            applicationFrame.repaint();

            System.out.println("New Slide - " + (slides.size()) + "!");
        }

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


}
