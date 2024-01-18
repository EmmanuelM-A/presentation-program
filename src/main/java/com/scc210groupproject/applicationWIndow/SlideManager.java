package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.structure.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * This class handles the slides currently being displayed on the screen.
 *
 * @author madukaag
 */
public class SlideManager implements ActionListener {

    public static SlideManager slideManager;

    // The current presentation being viewed by the program
    private final Presentation presentation;

    // This list represents the slides on the slide view slider
    private final LinkedList<JButton> slidesViewer = new LinkedList<>();;

    // Determines if a new slide added should be displayed or not
    private boolean displayNewSlide;

    // The current slide being displayed (starts from one)
    private int currentSlide;

    // The current index of the slide being displayed (starts from zero)
    private int currentSlideIndex;

    // This JPanel will be slide viewer for all the slides in the presentation
    private final JPanel viewSliderPanel;

    // The actual presentation slider
    private final JScrollPane presentationSlider;

    private JButton prevSlide, nextSlide, addNewSlide, deleteSlide, present, presentAt;

    private JLabel noSlides;

    // The main display panel
    private final MainDisplayPanel mainDisplay;

    /**
     * Constructor for the SlideManager
     * @param frame The ui frame
     * @param mainDisplay The main display panel of the ui frame
     */
    public SlideManager(final JFrame frame, MainDisplayPanel mainDisplay) {
        // Get the current presentation
        this.presentation = mainDisplay.getCurrentPresentation();

        // Default Setting - new slides added should be displayed on the main display panel
        this.displayNewSlide = true;

        // The current slide being displayed
        this.currentSlide = 1;

        // The index of the current slide being displayed
        this.currentSlideIndex = 0;

        // This contains the alternate display/viewer where all slides will be displayed and can be selected or scrolled through
        this.viewSliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.presentationSlider = new JScrollPane(this.viewSliderPanel);

        // The main display panel where slides will be added viewed and edited
        this.mainDisplay = mainDisplay;

        displayFirstSlide();
    }

    /**
     * Returns the value of the displayNewSlide variable
     * @return Boolean
     */
    public Boolean getDisplayNewSlides() {
        return this.displayNewSlide;
    }

    /**
     * Set to true to display new slides added. Set the variable to false to not display new
     * slides added.
     * @param yesOrNo Either true or false
     * */
    public void setDisplayNewSlides(Boolean yesOrNo) {
        this.displayNewSlide = yesOrNo;
    }

    /**
     * The JPanel that contains the components that make up the presentation slider:
     * previous slide button, next slide button, main view (the actual presentation slider) and
     * the bottom section which will hold a bunch of buttons.
     * @param uiFrame The frame that the slide manager will be added to
     */
    public void createPresentationSlider(JFrame uiFrame) {
        JPanel presentationSliderPanel = new JPanel(new BorderLayout());
        presentationSliderPanel.setPreferredSize(new Dimension(1000, 180));

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
        this.addNewSlide.addActionListener(this);

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
         * Adds the presentationSlider directly onto the frame window
         * */
        uiFrame.add(presentationSliderPanel, BorderLayout.SOUTH);
    }

    /** POSSIBLE CHANGES WILL BE MADE HERE
     * Displays the first slide in the presentation
     */
    private void displayFirstSlide() {
        Slide firstSlide = presentation.getSlideAtIndex(0);

        int firstSlideInViewerNo = this.slidesViewer.size() + 1;
        JButton firstSlideInViewer = createSlideForViewer(firstSlideInViewerNo);

        this.slidesViewer.add(firstSlideInViewer);
        this.viewSliderPanel.add(firstSlideInViewer);

        this.mainDisplay.add(firstSlide.asComp(), BorderLayout.CENTER);

        System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");
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
                displaySlide(prevSlide, this.mainDisplay);
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
        if(this.currentSlide < presentation.getSlideCount()) {
            // Get the current slide
            Slide currentSlide = getCurrentSlide();

            // Increment values
            this.currentSlide++;
            this.currentSlideIndex++;

            // Get the next slide
            Slide nextSlide = getNextSlide(currentSlide);

            if(nextSlide != null) {
                // Display the next slide
                displaySlide(nextSlide, this.mainDisplay);
                this.mainDisplay.repaint();
            } else {
                System.out.println("PROBLEM - NEXT SLIDE IS NULL!");
            }
        } else {
            System.out.println("Last slide reached!");
        }
    }

    /**
     * Displays the slideToDisplay onto the display
     * @param slideToDisplay The slide to add to the display
     * @param display The display the slide will be added and removed from
     * */
    private void displaySlide(Slide slideToDisplay, MainDisplayPanel display) {
        // Remove currently displayed slide
        display.removeAll();
        // Display the new slide
        display.add(slideToDisplay.asComp(), BorderLayout.CENTER);
        // Update frame
        display.revalidate();
        display.repaint();
        //display.pack();
    }

    /**
     * Creates a new slide and assigns it a slide number, then adds the slide onto the end of the existing slides
     * and finally display the new slide onto the screen.
     * */
    private void addNewSlide() {
        // Create new slide
        Slide newSlide = this.presentation.newSlide();

        // Keeps track of the slide currently being displayed
        this.currentSlide = this.presentation.getSlideCount();
        this.currentSlideIndex = this.currentSlide - 1;

        // -------- This code is only used to differentiate slides added
        newSlide.asComp().setBackground(new Color((float)this.presentation.getSlideCount() / 10 % 1, (float)this.presentation.getSlideCount() / 10 % 1, (float)this.presentation.getSlideCount() / 10 % 1));
        // --------

        // The code below will display the new slide added to the main display panel if displayNewSlide equals true
        if(this.displayNewSlide) {
            // Display new slide
            displaySlide(newSlide, this.mainDisplay);
        }

        // Add slide to presentation slider
        addSlideToViewer();

        System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");
    }

    /** POSSIBLE CHANGES WILL BE MADE HERE
     * Creates a slide for the slides viewer
     * @param slideNo The slide number to assign it to
     * @return JButton
     * */
    private JButton createSlideForViewer(int slideNo) {
        JButton slide = new JButton("Slide " + slideNo);

        slide.setPreferredSize(new Dimension(200, 115));
        slide.setBackground(Color.white);
        slide.setFocusable(false);
        slide.setIcon(new ImageIcon(presentation.getSlideAtIndex(slideNo - 1).createPreview(new Dimension(200, 115))));

        slide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displaySelectedSlide(slideNo);
                //System.out.println("Slide " + slideNo + " Clicked!");
            }
        });

        return slide;

    }

    /**
     * Adds new slides ot the slide viewer
     */
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
     * @return The previous slide
     * */
    private Slide getPrevSlide(Slide slide) {
        int indexOfSelectedSlide = this.presentation.getSlides().indexOf(slide);
        int indexOfPrevSlide = indexOfSelectedSlide - 1;
        //System.out.println("Selected slide: " + indexOfSelectedSlide + " Previous slide before selected slide: " + indexOfPrevSlide);

        if(indexOfPrevSlide >= 0) {
            return this.presentation.getSlideAtIndex(indexOfPrevSlide);
        } else {
            return null;
        }
    }

    /**
     * Returns the slide after the selected slide if there is none it returns null.
     * @param slide The current slide
     * */
    private Slide getNextSlide(Slide slide) {
        int indexOfSelectedSlide = this.presentation.getSlides().indexOf(slide);
        int indexOfNextSlide = indexOfSelectedSlide + 1;
        //System.out.println("Selected slide: " + indexOfSelectedSlide + " Next slide after selected slide: " + indexOfNextSlide);

        if(indexOfNextSlide <= this.presentation.getSlideCount()) {
            return this.presentation.getSlideAtIndex(indexOfNextSlide);
        } else {
            return null;
        }
    }

    /**
     * Returns the current slide being displayed
     * @return Slide
     * */
    public Slide getCurrentSlide() {
        if(this.currentSlide >= 1 && this.currentSlide <= this.presentation.getSlideCount()) {
            return this.presentation.getSlideAtIndex(this.currentSlideIndex);
        } else {
            return null;
        }
    }

    /**
     * Add a slide after the selected slide
     * @param selectedSlide The selected slide
     * */
    private void addSlideAfter(Slide selectedSlide) {}

    /**
     * Add a slide before the selected slide
     * @param selectedSlide The selected slide
     * */
    private void addSlideBefore(Slide selectedSlide) {}

    /**
     * Gets the selected slide
     * @param slideIndex The index of the selected slide
     * @return JPanel
     * */
    private Slide getSelectedSlide(int slideIndex) {
        return this.presentation.getSlideAtIndex(slideIndex);
    }

    /**
     * Highlights a selected slide in the slide viewer
     * @param JButton Slide
     * */
    /*private void highlightSelectedSlide(JButton Slide) {
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
     * @param slideNo The slide number (position) of the selected slide
     * */
    private void displaySelectedSlide(int slideNo) {
        Slide currentSlide = getCurrentSlide();
        Slide selectedSlide = getSelectedSlide(slideNo - 1);

        this.currentSlide = slideNo;
        this.currentSlideIndex = slideNo - 1;

        // Display selected slide
        displaySlide(selectedSlide, this.mainDisplay);
    }

    /*private ImageIcon bufferImageToImageIcon(BufferedImage imageToConvert) {
        return new ImageIcon(imageToConvert);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.prevSlide) { // Previous Slide Button
            showPrevSlide();
        } else if (e.getSource() == this.nextSlide) { // Next slide Button
            showNextSlide();
        } else if (e.getSource() == this.addNewSlide) { // Add New Slide
            addNewSlide();
        } else if (e.getSource() == this.deleteSlide) { // Delete (a selected) Slide
            System.out.println("Do Something!");
        } else if (e.getSource() == this.present) { // Start Presentation mode at the beginning
            System.out.println("Do Something Else!");
        } else if (e.getSource() == this.presentAt) { // Start Presentation mode at the selected slide
            System.out.println("Do Something Better!");
        }
    }
}
