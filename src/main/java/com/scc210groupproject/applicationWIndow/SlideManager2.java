package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SlideManager2 implements ActionListener {
    // The current presentation being viewed by the program
    private Presentation presentation;

    // This list represents the slides on the slide view slider
    private final LinkedList<JButton> slidesViewer = new LinkedList<>();;

    // Determines if a new slide added should be displayed or not
    private boolean displayNewSlide = true;

    // The current slide on (starts from one)
    private int currentSlide;

    // The current index of the slide (starts from zero)
    private int currentSlideIndex;

    // This is panel will be viewer for all the slides in the presentation
    private final JPanel viewSliderPanel;

    private final JButton prevSlide, nextSlide, addNewSlide, deleteSlide, present, presentAt;

    private final JLabel noSlides;

    // The main display panel
    private MainDisplayPanel mainDisplay;

    /**
     * Constructor for the SlideManager2
     * @param frame The ui frame
     */
    public SlideManager2(final JFrame frame, MainDisplayPanel mainDisplay) {
        // Create a new presentation if current presentation is not available
        this.presentation = Presentation.getOrCreate();

        // The current slide being displayed
        this.currentSlide = 1;

        // The index of the current slide being displayed
        this.currentSlideIndex = 0;

        // This contains the alternate display/viewer where all slides will be displayed and can be selected or scrolled through
        this.viewSliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane presentationSlider = new JScrollPane(this.viewSliderPanel);

        // The main display panel where slides will be added viewed and edited
        this.mainDisplay = mainDisplay;

        /*
         * The JPanel that contains the components that make up the presentation slider:
         * previous slide button, next slide button, main view (the actual presentation slider) and
         * the bottom section which will hold a bunch of buttons.
         * */
        JPanel presentationSliderPanel = new JPanel(new BorderLayout());
        presentationSliderPanel.setPreferredSize(new Dimension(1000, 180));

        /*
         * Previous slide button
         * */
        this.prevSlide = new JButton("<");
        this.prevSlide.setPreferredSize(new Dimension(50, 160));
        this.prevSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showPrevSlide();
            }
        });

        /*
         * Next slide button
         * */
        this.nextSlide = new JButton(">");
        this.nextSlide.setPreferredSize(new Dimension(50, 160));
        this.nextSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showNextSlide();
            }
        });

        /*
         * This is where the actual presentation slider will go
         * */
        this.viewSliderPanel.setBackground(Color.lightGray);
        presentationSlider.setPreferredSize(new Dimension(1000, 160));
        presentationSlider.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        /*
         * Bottom Section of the presentation slider where buttons such as newSlide, deleteSlide,
         * etc. will be placed. As well as a number of slides display.
         * */
        JPanel bottomSection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomSection.setPreferredSize(new Dimension(1000, 35));
        bottomSection.setBackground(Color.lightGray);

        this.addNewSlide = new JButton("New Slide");
        this.addNewSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //addNewSlide(this.displayNewSlides);
            }
        });


        this.deleteSlide = new JButton("Delete Slide");
        this.present = new JButton("Present");
        this.presentAt = new JButton("Present From");
        this.noSlides = new JLabel("Slides: ");


        /*
         * Add buttons to bottom sections
         * */
        bottomSection.add(this.addNewSlide);
        bottomSection.add(this.deleteSlide);
        bottomSection.add(this.present);
        bottomSection.add(this.presentAt);
        bottomSection.add(this.noSlides);

        /*
         * Adds each section/button onto the display
         * */
        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(presentationSlider, BorderLayout.CENTER);
        presentationSliderPanel.add(bottomSection, BorderLayout.SOUTH);

        /*
         * Adds the presentationSlider directly onto the application frame window
         * */
        frame.add(presentationSliderPanel, BorderLayout.SOUTH);
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
        /*if(this.currentSlide > 1) {
            JPanel currentSlide = getCurrentSlide();
            this.currentSlide--;
            this.currentSlideIndex--;
            JPanel prevSlide = getPrevSlide(currentSlide);

            if(prevSlide != null) {
                // Display slide
                showSlide(currentSlide, prevSlide, applicationFrame);
            } else {
                System.out.println("PROBLEM!");
            }
        } else {
            System.out.println("First slide reached!");
        }*/
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
        /*if(this.currentSlide < slides.size()) {
            JPanel currentSlide = getCurrentSlide();
            this.currentSlide++;
            this.currentSlideIndex++;
            JPanel nextSlide = getNextSlide(currentSlide);
            if(nextSlide != null) {
                // Display Slide
                showSlide(currentSlide, nextSlide, applicationFrame);
            } else {
                System.out.println("PROBLEM!");
            }
        } else {
            System.out.println("Last slide reached!");
        }*/
    }

    /** - /// SORT OUT HERE
     * Displays the slideToDisplay onto the display
     * @param slideToRemove The slide to remove from the display
     * @param slideToDisplay The slide to add to the display
     * @param display The display the slide will be added and removed from
     * */
    private void showSlide(Slide slideToRemove, Slide slideToDisplay, JPanel display) {
        // Remove current slide
        display.remove(slideToRemove.asComp());
        // Add new slide
        display.add(slideToDisplay.asComp(), BorderLayout.CENTER);
        // Repaint frame
        display.revalidate();
        display.repaint();
    }

    /**
     * Creates a new slide and assigns it a slide number, then adds the slide onto the end of the existing slides
     * and finally display the new slide onto the screen.
     * */
    private void addNewSlide(Boolean displayNewSlides) {
        // Create new slide
        Slide newSlide = this.presentation.newSlide();

        // Keeps track of the slide currently being displayed
        this.currentSlide = this.presentation.getSlideCount();
        this.currentSlideIndex = this.currentSlide - 1;

        System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");

        // Add slide to presentation slider
        addSlideToViewer();

        // The code below changes the main display to the new slide added
        if(displayNewSlides) {
            if (this.presentation.getSlideCount() >= 1) { // There is more than one slide present
                // Get current slide
                Slide currentSlide = getCurrentSlide();
                // Check if slide exists
                if(currentSlide != null) {
                    // Display
                    showSlide(currentSlide, newSlide, this.mainDisplay);
                    // Increment slide index to current slide added
                    this.currentSlide = presentation.getSlideCount();
                    this.currentSlideIndex = this.currentSlide - 1;
                    System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");
                } else {
                    System.out.println("PROBLEM IN ADD_SLIDE");
                }
            }
        }
    }

    /**
     * Adds a slide to the slides viewer
     * @param slideNo The slide number to assign it to
     * @return JButton
     * */
    private JButton createSlideForViewer(int slideNo) {
        JButton slide = new JButton("Slide " + slideNo);

        slide.setPreferredSize(new Dimension(200, 120));
        slide.setBackground(Color.white);
        slide.setFocusable(false);

        slide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //displaySelectedSlide(slideNo);
                System.out.println("Slide " + slideNo + " Clicked!");
            }
        });

        return slide;

    }

    private void addSlideToViewer() {
        int slideNum = this.slidesViewer.size() + 1;

        JButton newPSSlide = createSlideForViewer(slideNum);

        this.slidesViewer.add(newPSSlide);

        this.viewSliderPanel.add(newPSSlide);

        this.viewSliderPanel.revalidate();
    }

    /**
     * Returns the slide before the selected slide if there is none it returns null.
     * @param slide The current slide
     * */
    /*private JPanel getPrevSlide(JPanel slide) {
        int indexOfSelectedSlide = slides.indexOf(slide);
        int indexOfPrevSlide = indexOfSelectedSlide - 1;
        if(indexOfPrevSlide >= 0) {
            return slides.get(indexOfPrevSlide);
        } else {
            return null;
        }
    }*/

    /**
     * Returns the slide after the selected slide if there is none it returns null.
     * @param slide The current slide
     * */
    /*private JPanel getNextSlide(JPanel slide) {
        int indexOfSelectedSlide = slides.indexOf(slide);
        int indexOfNextSlide = indexOfSelectedSlide + 1;

        if(indexOfNextSlide <= slides.size()) {
            return slides.get(indexOfNextSlide);
        } else {
            return null;
        }
    }*/

    /**
     * Returns the current slide being displayed
     * */
    private Slide getCurrentSlide() {
        if(this.currentSlide >= 1 && this.currentSlide <= this.presentation.getSlideCount()) {
            return this.presentation.getSlideAtIndex(this.currentSlideIndex);
        } else {
            return null;
        }
    }

    /**
     * Creates a PS slide
     * @param slideNo The slide number to assign it to
     * @return JButton (PS Slide)
     * */
    /*private JButton createPSSlide(Integer slideNo) {
        JButton newPSSlide = new JButton("Slide " + slideNo);

        newPSSlide.setPreferredSize(new Dimension(200, 120));
        newPSSlide.setBackground(Color.white);
        newPSSlide.setFocusable(false);

        newPSSlide.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                highlightSelectedSlide(newPSSlide);
                displaySelectedSlide(slideNo);
            }
        });

        return newPSSlide;
    }*/

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
    /*private JPanel getSelectedSlide(Integer slideNo) {
        return slides.get(slideNo - 1);
    }*/

    /**
     * Highlights the slide (PS Slide)
     * @param PSSlide The PS Slide that was selected
     * */
    /*private void highlightSelectedSlide(JButton PSSlide) {
        /*
         * Get selected slide
         * Highlight ONLY that selected slide
         * use boolean isSelected for slides
         * onClick of slide set isSelected true and every other slide false
         * from slides array filter out clicked slide and set isSelected slide to true
         * other slides set false
         * apply action to the isSelected slide like display, highlight slide
         *
        PSSlide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
    }*/

    /**
     * Displays the selected slide to the main display
     * */
    /*private void displaySelectedSlide(int slideNo) {
        JPanel currentSlide = getCurrentSlide();
        JPanel selectedSlide = getSelectedSlide(slideNo);
        this.currentSlide = slideNo;
        this.currentSlideIndex = slideNo - 1;
        // Display slide
        showSlide(currentSlide, selectedSlide, applicationFrame);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.prevSlide) { // Previous Slide Button
            showPrevSlide();
        } else if (e.getSource() == this.nextSlide) { // Next slide Button
            showNextSlide();
        } else if (e.getSource() == this.addNewSlide) { // Add New Slide
            addNewSlide(this.displayNewSlide);
        } else if (e.getSource() == this.deleteSlide) { // Delete (a selected) Slide
            System.out.println("Do Something!");
        } else if (e.getSource() == this.present) { // Start Presentation mode at the beginning
            System.out.println("Do Something Else!");
        } else if (e.getSource() == this.presentAt) { // Start Presentation mode at the selected slide
            System.out.println("Do Something Better!");
        }
    }
}
