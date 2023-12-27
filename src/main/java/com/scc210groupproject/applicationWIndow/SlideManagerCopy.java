package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.structure.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class SlideManagerCopy implements ActionListener {
    // Where the slides that are created are stored
    private final LinkedList<Slide> slides;

    // This list represents the slides displayed on the presentation slider (PS)
    private final LinkedList<JButton> presentationSliderSlides;

    // The current slide being displayed/selected
    private int currentSlide;

    // The index of the current slide being display/selected
    private int currentSlideIndex;

    // Determines if a new slide added should be displayed or not
    private boolean displayNewSlide = true;

    // This slide is always present on load
    private Slide firstSlide;

    // The panel that will contain the slide scroller and slides added
    private final JPanel sliderPanel;

    // The main display panel (where slides will be viewed)
    private final MainDisplay mainDisplay;

    private final JButton prevSlide, nextSlide, addNewSlide, deleteSlide, present, presentAt;

    /**
     * Slide Manager Constructor
     * @param mainDisplay The main display section/panel of the ui
     * */
    public SlideManagerCopy(final MainDisplay mainDisplay) {
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
        this.prevSlide = new JButton("<");
        this.prevSlide.setPreferredSize(new Dimension(50, 160));
        this.prevSlide.addActionListener(this);

        /*
         * Next slide button
         * */
        this.nextSlide = new JButton(">");
        this.nextSlide.setPreferredSize(new Dimension(50, 160));
        this.nextSlide.addActionListener(this);

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
         * The buttons for the bottom section
         */
        this.addNewSlide = new JButton("New Slide");
        this.addNewSlide.addActionListener(this);

        this.deleteSlide = new JButton("Delete Slide");
        this.present = new JButton("Present");
        this.presentAt = new JButton("Present From");
        //this.noSlides = new JLabel();


        /*
         * Add buttons to the bottom section
         * */
        bottomSection.add(this.addNewSlide);
        bottomSection.add(this.deleteSlide);
        bottomSection.add(this.present);
        bottomSection.add(this.presentAt);
        //bottomSection.add(this.noSlides);

        /*
         * Adds each component/section/buttons onto the presentation slider panel
         * */
        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(presentationSlider, BorderLayout.CENTER);
        presentationSliderPanel.add(bottomSection, BorderLayout.SOUTH);

        /*
         * Adds the presentationSliderPanel directly onto the ui frame
         * */
        mainDisplay.add(presentationSliderPanel, BorderLayout.SOUTH);
    }

    /***
     * Returns the value of the displayNewSlide variable
     * @return Boolean
     */
    public Boolean getDisplayNewSlides() {
        return this.displayNewSlide;
    }

    /**
     * Set to true to display new slides added. Set too false to not display new
     * slides added.
     * @param yesOrNo Either true or false
     * */
    public void setDisplayNewSlides(Boolean yesOrNo) {
        this.displayNewSlide = yesOrNo;
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

        // Add first slide to the main display
        this.mainDisplay.add(firstSlide.asComp(), BorderLayout.CENTER);
        System.out.println("New Slide - " + (slides.size()) + "!");
    }

    /**
     * Displays the previous slide in the presentation slider given that there is at least one slide
     * present.
     * */
    private void showPrevSlide()
    {
        if(this.currentSlide > 1) {
            // Get the current slide
            Slide currentSlide = getCurrentSlide();

            // Decrement values
            this.currentSlide--;
            this.currentSlideIndex--;

            // Get the previous slide if there is one
            Slide prevSlide = getPrevSlide(currentSlide);

            if(prevSlide != null) {
                // Display the previous slide
                showSlide(currentSlide, prevSlide, this.mainDisplay);
            } else {
                System.out.println("PROBLEM - PREV SLIDE IS NULL!");
            }
        } else {
            System.out.println("First slide reached!");
        }
    }

    /**
     * Displays the next slide in the presentation slider given that the last slide has not been
     * reached yet.
     * */
    private void showNextSlide()
    {
        if(this.currentSlide < slides.size()) {
            // Get the current slide
            Slide currentSlide = getCurrentSlide();

            // Increment values
            this.currentSlide++;
            this.currentSlideIndex++;

            // Get the next slide
            Slide nextSlide = getNextSlide(currentSlide);

            if(nextSlide != null) {
                // Display the next slide
                showSlide(currentSlide, nextSlide, this.mainDisplay);
            } else {
                System.out.println("PROBLEM - NEXT SLIDE IS NULL!");
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
    private void showSlide(Slide slideToRemove, Slide slideToDisplay, MainDisplay mainDisplay) {
        // Remove current slide
        mainDisplay.remove(slideToRemove.asComp());
        // Add new slide
        mainDisplay.add(slideToDisplay.asComp(), BorderLayout.CENTER);
        // Repaint display
        mainDisplay.revalidate();
        mainDisplay.repaint();
    }

    /**
     * Creates a new slide (and assigns it a slide number), then adds the slide onto the end of the existing slides
     * and finally can display the new slide onto the screen .
     * */
    private void addNewSlide(Boolean displayNewSlides)
    {
        // Create new slide
        Slide newSlide = new Slide();
        newSlide.asComp().setBackground(Color.LIGHT_GRAY);

        // Assign the slide its slide number
        JLabel slideNo = new JLabel("Slide " + (slides.size() + 1));
        slideNo.setFont(new Font("Arial", Font.BOLD, 24));
        //newSlide.add(slideNo);

        // Add the slide to slides linked list
        slides.add(newSlide);
        //System.out.println("New Slide - " + (slides.size()) + "!");

        // Create the corresponding PS slide and add it to presentation slider
        addSlideToPresentationSlider();

        // The code below changes the main display to the new slide added
        if(displayNewSlides) {
            if(this.slides.size() == 1) { // Only the first slide is present
                // Display new slide
                showSlide(this.firstSlide, newSlide, this.mainDisplay);

                // Set the currentSlide and currentSlideIndex to the slide just added
                this.currentSlide = slides.size();
                this.currentSlideIndex = slides.size() - 1;
                System.out.println("New Slide - " + (slides.size()) + "!");

            } else if (slides.size() > 1) { // There is more than one slide present
                // Get current slide being displayed
                Slide currentSlide = getCurrentSlide();

                // Check if slide exists
                if(currentSlide != null) {
                    // Display the new slide
                    showSlide(currentSlide, newSlide, this.mainDisplay);

                    // Set the currentSlide and currentSlideIndex to the slide just added
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
    private Slide getPrevSlide(Slide slide) {
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
    private Slide getNextSlide(Slide slide) {
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
     */
    private Slide getCurrentSlide() {
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
    private Slide getSelectedSlide(Integer slideNo) {
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
        //PSSlide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
    }

    /**
     * Displays the selected slide to the main display
     * @param slideNo The slide number/position of the slide selected
     * */
    private void displaySelectedSlide(int slideNo) {
        // Get the current slide being displayed
        Slide currentSlide = getCurrentSlide();

        // Get the slide that was selected
        Slide selectedSlide = getSelectedSlide(slideNo);

        // Set the values to match the slide selected
        this.currentSlide = slideNo;
        this.currentSlideIndex = slideNo - 1;

        // Display the selected slide
        if(currentSlide != null) {
            showSlide(currentSlide, selectedSlide, this.mainDisplay);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.prevSlide) {
            showPrevSlide();
        } else if (e.getSource() == this.nextSlide) {
            showNextSlide();
        } else if (e.getSource() == this.addNewSlide) {
            addNewSlide(this.displayNewSlide);
        } else if (e.getSource() == this.deleteSlide) {
            System.out.println("Do Something!");
        } else if (e.getSource() == this.present) {
            System.out.println("Do Something Else!");
        } else if (e.getSource() == this.presentAt) {
            System.out.println("Do Something Better!");
        }
    }
}
