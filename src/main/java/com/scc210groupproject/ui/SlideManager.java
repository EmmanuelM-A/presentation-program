package com.scc210groupproject.ui;

import com.scc210groupproject.action.NewSlideAction;
import com.scc210groupproject.structure.*;

import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;
import com.scc210groupproject.structure.eventListeners.IUpdateSlideListener;
import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * This class handles the slides currently being displayed on the screen.
 *
 * @author madukaag
 */
public class SlideManager implements ActionListener, IChangePresentationListener, ICreateSlideListener, IDiscardSlideListener, IUpdateSlideListener
{
    public static SlideManager slideManager;

    // The current presentation being viewed by the program
    private final Presentation presentation;

    // This list represents the slides on the slide view slider
    private final LinkedList<JButton> slidesViewer = new LinkedList<>();

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

    //private JLabel noSlides;

    // The main display panel
    private final MainDisplayPanel mainDisplay;

    public static SlideManager instance;

    // This list represents the slideImages that are painted onto the display
    private final LinkedList<SlideImage> slideImages = new LinkedList<>();

    /**
     * Constructor for the SlideManager
     *
     * @param mainDisplay The main display panel of the ui frame
     */
    public SlideManager(MainDisplayPanel mainDisplay) {
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

        Presentation.addCreateSlideListener(this);
        Presentation.addUpdateSlideListener(this);
        Presentation.addDiscardSlideListener(this);
        Presentation.addChangePresentationListener(this);

        displayFirstSlide();
        instance = this;
    }

    /**
     * Returns the value of the displayNewSlide variable
     * @return Boolean
     */
    public Boolean getDisplayNewSlides() {
        return this.displayNewSlide;
    }

    /**
     * Set to true to display new slides added or false to not display new
     * slides added.
     * @param yesOrNo Either true or false
     * */
    public void setDisplayNewSlides(Boolean yesOrNo) {
        this.displayNewSlide = yesOrNo;
    }

    /**
     * The JPanel that contains the components that make up the presentation slider:
     * previous slide button, next slide button, main view (the actual presentation slider) and
     * the bottom section which will hold the buttons.
     * @return The presentation slider panel
     */
    public JPanel createPresentationSlider() {
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
        //this.viewSliderPanel.setPreferredSize(new Dimension(1000, 160));
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
        this.addNewSlide.addActionListener(new NewSlideAction());

        this.deleteSlide = new JButton("Delete Slide");
        this.present = new JButton("Present");
        this.presentAt = new JButton("Present From");
        //this.noSlides = new JLabel("Slides: ");

        /*
         * Add buttons to bottom sections
         * */
        bottomSection.add(this.addNewSlide);
        bottomSection.add(this.deleteSlide);
        bottomSection.add(this.present);
        bottomSection.add(this.presentAt);
        //bottomSection.add(this.noSlides);

        /*
         * Adds each section/button onto the display
         * */
        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(presentationSlider, BorderLayout.CENTER);
        presentationSliderPanel.add(bottomSection, BorderLayout.SOUTH);

        return presentationSliderPanel;
    }

    /** POSSIBLE CHANGES WILL BE MADE HERE
     * Displays the first slide in the presentation
     */
    private void displayFirstSlide() {
        Slide firstSlide = presentation.getSlideAtIndex(0);

        SlideImage firstSlideImage = new SlideImage(firstSlide, this.mainDisplay);
        this.slideImages.add(firstSlideImage);

        int firstSlideInViewerNo = this.slidesViewer.size() + 1;
        JButton firstSlideInViewer = createSlideForViewer(firstSlideInViewerNo);

        this.slidesViewer.add(firstSlideInViewer);
        this.viewSliderPanel.add(firstSlideInViewer);

        displaySlide(firstSlideImage, this.mainDisplay);

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
            SlideImage prevSlide = getPrevSlide(currentSlide);

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
            SlideImage nextSlide = getNextSlide(currentSlide);

            if(nextSlide != null) {
                // Display the next slide
                displaySlide(nextSlide, this.mainDisplay);
                //this.mainDisplay.repaint();
            } else {
                System.out.println("PROBLEM - NEXT SLIDE IS NULL!");
            }
        } else {
            System.out.println("Last slide reached!");
        }
    }

    /**
     * Displays/Paints the slideToDisplay onto the main display (via the main display panel)
     * @param slideToDisplay The slideImage to add to the display
     * @param display The display the slide will be added and removed from
     */
    private void displaySlide(SlideImage slideToDisplay, MainDisplayPanel display) {
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

        SlideImage newSlideImage = new SlideImage(newSlide, this.mainDisplay);

        this.slideImages.add(newSlideImage);

        // The code below will display the new slide added to the main display panel if displayNewSlide equals true
        if(this.displayNewSlide) {
            // Display new slide
            displaySlide(newSlideImage, this.mainDisplay);
        }

        // Add slide to presentation slider
        addSlideToViewer();

        System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");
    }

    private void deleteSlide() {
        /*
        Get the selected slide (slide clicked)
        Delete selected slide from slides list
        then remove selected slide from display
        Check if there is slides before and after it.
        if before display slide before else display slide after
        if there are none at all display nothing
         */
        Slide currentSlide = getCurrentSlide();
        presentation.removeSlide(currentSlide);

        if(getPrevSlide(currentSlide) != null) {
            showPrevSlide();
            System.out.println("Waluigi!");
        } else if(getNextSlide(currentSlide) != null) {
            showNextSlide();
            System.out.println("Waluigi!");
        } else {
            // Show nothing an empty presentation
            System.out.println("Show nothing, an empty presentation!");
        }
    }

    /** POSSIBLE CHANGES WILL BE MADE HERE
     * Creates a slide for the slides viewer
     * @param slideNo The slide number to assign it to
     * @return JButton
     * */
    private JButton createSlideForViewer(int slideNo) {
        JButton slide = new JButton();

        slide.setPreferredSize(new Dimension(200, 115));

        slide.setFocusable(false);

        ImageIcon previewSlideImage = new ImageIcon(slideImages.get(slideNo - 1).getBufferedSlideImage());
        slide.setIcon(GeneralButtons.resizeIcon(previewSlideImage, 200, 115));

        //slide.repaint();

        slide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //highlightSelectedSlide(slide);
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

        JButton slide = createSlideForViewer(slideNum);

        this.slidesViewer.add(slide);

        this.viewSliderPanel.add(slide);

        this.viewSliderPanel.revalidate();
    }
    /**
     * Gets the slideImage before the selected slide. If there is none it returns null.
     * @param slide The current/selected slide
     * @return SlideImage
     */
    private SlideImage getPrevSlide(Slide slide) {
        int indexOfSelectedSlide = this.presentation.getSlides().indexOf(slide);
        int indexOfPrevSlide = indexOfSelectedSlide - 1;
        //System.out.println("Selected slide: " + indexOfSelectedSlide + " Previous slide before selected slide: " + indexOfPrevSlide);

        if(indexOfPrevSlide >= 0) {
            return this.slideImages.get(indexOfPrevSlide);
        } else {
            return null;
        }
    }

    /**
     * Gets the slideImage after the selected slide. If there is none it will return null.
     * @param slide The current/selected slide
     * @return SlideImage
     */
    private SlideImage getNextSlide(Slide slide) {
        int indexOfSelectedSlide = this.presentation.getSlides().indexOf(slide);
        int indexOfNextSlide = indexOfSelectedSlide + 1;
        System.out.println("Selected slide: " + indexOfSelectedSlide + " Next slide after selected slide: " + indexOfNextSlide);

        if(indexOfNextSlide <= this.presentation.getSlideCount()) {
            return this.slideImages.get(indexOfNextSlide -1);
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
     * @return SlideImage
     * */
    private SlideImage getSelectedSlide(int slideIndex) {
        return this.slideImages.get(slideIndex);
    }

    /*
     * Highlights a selected slide in the slide viewer
     * @param JButton Slide
     * */
    private void highlightSelectedSlide(JButton slide, int slideNo) {
        /*
         * Get selected slide
         * Highlight ONLY that selected slide
         * use boolean isSelected for slides
         * onClick of slide set isSelected true and every other slide false
         * from slides array filter out clicked slide and set isSelected slide to true
         * other slides set false
         * apply action to the isSelected slide like display, highlight slide
         */
        /*SlideImage selectedSlide = getSelectedSlide(slideNo);

        selectedSlide.setIsSelected(true);*/





        //slide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

    }

    /**
     * Displays the selected slideImage to the main display
     * @param slideNo The slide number (position) of the selected slide
     * */
    private void displaySelectedSlide(int slideNo) {
        SlideImage selectedSlide = getSelectedSlide(slideNo - 1);

        currentSlide = slideNo;
        currentSlideIndex = slideNo - 1;

        // Display selected slide
        displaySlide(selectedSlide, this.mainDisplay);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.prevSlide) { // Previous Slide Button
            showPrevSlide();
        } else if (e.getSource() == this.nextSlide) { // Next slide Button
            showNextSlide();
        } else if (e.getSource() == this.addNewSlide) { // Add New Slide
            //addNewSlide();
        } else if (e.getSource() == this.deleteSlide) { // Delete (a selected) Slide
            // System.out.println("Do Something!");
            deleteSlide();
        } else if (e.getSource() == this.present) { // Start Presentation mode at the beginning
            System.out.println("Do Something Else!");
        } else if (e.getSource() == this.presentAt) { // Start Presentation mode at the selected slide
            System.out.println("Do Something Better!");
        }
    }

    public void showSlideAtIndex(int index) {
        Slide currentSlide = this.presentation.getSlideAtIndex(index);
        SlideImage currentSlideImage = new SlideImage(currentSlide, this.mainDisplay);

        displaySlide(currentSlideImage, this.mainDisplay);
    }

    @Override
    public void onChangePresentation(Presentation current, Presentation discarded) {
        if (current != null && current.getSlideCount() > 0) {
            showSlideAtIndex(0);
            return;
        }

        this.mainDisplay.setCurrentSlideImage(null);
        this.mainDisplay.clearPaintedSlide();
    }

    @Override
    public void onCreateSlide(int index, Slide slide) {
        // -------- This code is only used to differentiate slides added
        slide.asComp().setBackground(new Color((float)this.presentation.getSlideCount() / 10 % 1, (float)this.presentation.getSlideCount() / 10 % 1, (float)this.presentation.getSlideCount() / 10 % 1));
        // --------

        SlideImage newSlideImage = new SlideImage(slide, this.mainDisplay);

        this.slideImages.add(newSlideImage);

        // The code below will display the new slide added to the main display panel if displayNewSlide equals true
        if(this.displayNewSlide) {
            // Display new slide
            displaySlide(newSlideImage, this.mainDisplay);
        }

        // Add slide to presentation slider
        addSlideToViewer();

        System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");
    }

    @Override
    public void onDiscardSlide(int index, Slide slide) {
        /*if(slide == getCurrentSlide()) {
            showSlideAtIndex(index > presentation.getSlideCount() ? index - 1 : index);
        }*/
        Slide currentSlide = getCurrentSlide();
        presentation.removeSlide(currentSlide);

        if(getPrevSlide(currentSlide) != null) {
            showPrevSlide();
            System.out.println("Waluigi!");
        } else if(getNextSlide(currentSlide) != null) {
            showNextSlide();
            System.out.println("Waluigi!");
        } else {
            // Show nothing an empty presentation
            System.out.println("Show nothing, an empty presentation!");
        }
    }

    @Override
    public void onUpdateSlide(int index, Slide slide) {
        // Get the slide in the slideViewer at the index
        JButton previewSlide = this.slidesViewer.get(index);

        // Recreate the slide image (icon) for that slide in the slideViewer
        ImageIcon previewSlideImage = new ImageIcon(slideImages.get(index).getBufferedSlideImage());

        // Reset the image icon of that slide to the new slide image
        previewSlide.setIcon(GeneralButtons.resizeIcon(previewSlideImage, 200, 115));
    }
}
