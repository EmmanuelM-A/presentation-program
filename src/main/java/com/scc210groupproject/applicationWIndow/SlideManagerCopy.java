package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.structure.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SlideManager1 {
    // Where the slides that are created are stored
    private final LinkedList<Slide> slides;

    // This list represents the slides displayed on the presentation slider (PS)
    private final LinkedList<JButton> presentationSliderSlides;

    // The current slide being displayed/selected
    private int currentSlide;

    // The index of the current slide being display/selected
    private int currentSlideIndex;

    // This slide is always present on load
    private Slide firstSlide;

    // The panel that will contain the slide scroller and slides added
    private final JPanel sliderPanel;

    // The main display panel (where slides will be viewed)
    private final MainDisplay mainDisplay;

    /**
     * Slide Manager Constructor
     * @param mainDisplay The main display of the ui
     * */
    public SlideManager1(final MainDisplay mainDisplay) {
        this.slides = new LinkedList<>();
        this.presentationSliderSlides = new LinkedList<>();
        this.currentSlide = 1; // There will be slide present on load of the program
        this.currentSlideIndex = 0;
        this.mainDisplay = mainDisplay;

        // The alternate display where all slides can be displayed, selected and scrolled through
        this.sliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane presentationSlider = new JScrollPane(sliderPanel);

        addFirstSlide();

        /*
         * The JPanel/Section that contains the components that make up the presentation slider:
         * previous slide button, next slide button, the actual presentation slider and
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
        this.sliderPanel.setBackground(Color.lightGray);
        presentationSlider.setPreferredSize(new Dimension(1000, 160));
        presentationSlider.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        /*
         * Bottom Section of the presentation slider where buttons such as newSlide, deleteSlide,
         * etc. will be placed.
         * */
        JPanel bottomSection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomSection.setPreferredSize(new Dimension(1000, 35));
        bottomSection.setBackground(Color.lightGray);

        /*
         * New slide button
         */
        JButton addNewSlide = new JButton("New Slide");
        addNewSlide.addActionListener(new ActionListener() {
            public void setDisplayNewSlides(Boolean displayNewSlides) {
                this.displayNewSlides = displayNewSlides;
            }

            private Boolean displayNewSlides = true; // Determines if a new slide added should be displayed or not

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                addNewSlide(this.displayNewSlides);
            }
        });


        JButton deleteSlide = new JButton("Delete Slide");
        JButton present = new JButton("Present");
        JButton presentAt = new JButton("Present From");
        JLabel noSlides = new JLabel();


        /*
         * Add buttons to the bottom section
         * */
        bottomSection.add(addNewSlide);
        bottomSection.add(deleteSlide);
        bottomSection.add(present);
        bottomSection.add(presentAt);
        bottomSection.add(noSlides);

        /*
         * Adds each component of the presentation slider panel onto the display
         * */
        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(presentationSlider, BorderLayout.CENTER);
        presentationSliderPanel.add(bottomSection, BorderLayout.SOUTH);

        /*
         * Adds the presentationSlider directly onto the application frame window
         * */
        mainDisplay.add(presentationSliderPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds the first slide to the display on startup of the program.
     * <p>
     * The currentSlide, currentSlideIndex, slides linkedList and PS slider linkedList
     * will all be updated accordingly.
     * */
    private void addFirstSlide() {
        // Create a new slide (the first slide)
        firstSlide = new Slide();

        // Create it's corresponding PS slide and add it the PS slide list
        int firstPSSlideNo = presentationSliderSlides.size() + 1;
        JButton firstPSSlide = createPSSlide(firstPSSlideNo);
        presentationSliderSlides.add(firstPSSlide);

        // Then add it to the presentation slider
        this.sliderPanel.add(firstPSSlide);

        // Add firstSlide to slides list
        this.slides.add(firstSlide);

        // Used to differentiate between slides
        JLabel slideNo = new JLabel("Slide " + (slides.size()));
        slideNo.setFont(new Font("Arial", Font.BOLD, 24));

        //firstSlide.add(slideNo);

        mainDisplay.add(firstSlide.asComp(), BorderLayout.CENTER);
        System.out.println("New Slide - " + (slides.size()) + "!");
    }

    /**
     * Displays the previous slide in the presentation slider given that there is at least one slide
     * */
    private void showPrevSlide()
    {
        if(this.currentSlide > 1) {
            // Get the current slide
            JPanel currentSlide = getCurrentSlide();
            this.currentSlide--;
            this.currentSlideIndex--;

            // Get the previous slide if there is one
            JPanel prevSlide = getPrevSlide(currentSlide);

            if(prevSlide != null) {
                // Display slide
                showSlide(currentSlide, prevSlide, this.mainDisplay);
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
        }
    }

    /**
     * Removes the slideToRemove from the display and adds/displays the slideToDisplay onto the display
     * @param slideToRemove The slide to remove from the display
     * @param slideToDisplay The slide to add to the display
     * @param mainDisplay The display the slide will be added and removed from
     * */
    private void showSlide(Slide slideToRemove, JPanel slideToDisplay, MainDisplay mainDisplay) {
        // Remove current slide
        mainDisplay.remove(slideToRemove);
        // Add new slide
        mainDisplay.add(slideToDisplay, BorderLayout.CENTER);
        // Repaint display
        mainDisplay.revalidate();
        mainDisplay.repaint();
    }

    /**
     * Creates a new slide and assigns it a slide number, then adds the slide onto the end of the existing slides
     * and finally display the new slide onto the screen.
     * */
    private void addNewSlide(Boolean displayNewSlides) {
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
        if(displayNewSlides) {
            // There is only one slide currently being displayed - the first slide
            if(slides.size() == 1) {
                // Display slide
                showSlide(firstSlide, newSlide, applicationFrame);
                // Increment slide index to current slide added
                this.currentSlide = slides.size();
                this.currentSlideIndex = slides.size() - 1;
                System.out.println("New Slide - " + (slides.size()) + "!");
            } else if (slides.size() > 1) { // There is more than one slide present
                // Get current slide
                JPanel currentSlide = getCurrentSlide();
                // Check if slide exists
                if(currentSlide != null) {
                    // Display
                    showSlide(currentSlide, newSlide, applicationFrame);
                    // Increment slide index to current slide added
                    this.currentSlide = slides.size();
                    this.currentSlideIndex = slides.size() - 1;
                    System.out.println("New Slide - " + (slides.size()) + "!");
                } else {
                    System.out.println("PROBLEM IN ADD_SLIDE");
                }
            }
        }
    }

    /**
     * Adds the new slide (represented as buttons) to the presentation slider
     * */
    private void addSlideToPresentationSlider() {
        Integer slideNum = presentationSliderSlides.size() + 1;

        JButton newPSSlide = createPSSlide(slideNum);

        this.presentationSliderSlides.add(newPSSlide);

        this.sliderPanel.add(newPSSlide);

        this.sliderPanel.revalidate();
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
     * Creates a PS slide
     * @param slideNo The slide number to assign it to
     * @return JButton (PS Slide)
     * */
    private JButton createPSSlide(Integer slideNo) {
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
        return slides.get(slideNo - 1);
    }

    /**
     * Highlights the slide (PS Slide)
     * @param PSSlide The PS Slide that was selected
     * */
    private void highlightSelectedSlide(JButton PSSlide) {
        /*
         * Get selected slide
         * Highlight ONLY that selected slide
         * use boolean isSelected for slides
         * onClick of slide set isSelected true and every other slide false
         * from slides array filter out clicked slide and set isSelected slide to true
         * other slides set false
         * apply action to the isSelected slide like display, highlight slide
         * */
        PSSlide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
    }

    /**
     * Displays the selected slide to the main display
     * */
    private void displaySelectedSlide(int slideNo) {
        JPanel currentSlide = getCurrentSlide();
        JPanel selectedSlide = getSelectedSlide(slideNo);
        this.currentSlide = slideNo;
        this.currentSlideIndex = slideNo - 1;
        // Display slide
        showSlide(currentSlide, selectedSlide, applicationFrame);
    }
}
