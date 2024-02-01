package com.scc210groupproject.ui;

import com.scc210groupproject.action.DeleteSlideAction;
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
import java.util.ArrayList;
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
    private Presentation presentation = null;

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

    // Used as a temp variable to store previously highlighted slides
    private JButton prevHighlightedSlide = null;

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
        this.deleteSlide.addActionListener(new DeleteSlideAction());
        
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

    /**
     * Displays the first slide in the presentation
     */
    private void displayFirstSlide() {
        Slide firstSlide = presentation.getSlideAtIndex(0);

        SlideImage firstSlideImage = new SlideImage(firstSlide, this.mainDisplay);
        this.slideImages.add(firstSlideImage);

        addSlideToViewer();

        displaySlide(firstSlideImage, this.mainDisplay);

        System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");
    }

    /**
     * Displays the previous slide in the presentation slider given that there is at least one slide
     * present.
     * */
    private boolean showPrevSlide()
    {
        if(this.currentSlide > 1) {
            // Get the current slide
            Slide currentSlide = getCurrentSlide();

            // Decrement values
            this.currentSlide--;
            this.currentSlideIndex--;

            //System.out.println("Previous Slide: " + this.currentSlide);

            // Get the previous slide if there is one
            SlideImage prevSlide = getPrevSlide(currentSlide);

            if(prevSlide != null) {
                // Display the previous slide
                displaySlide(prevSlide, this.mainDisplay);
                highlightSlide(prevSlide);
                return true;
            } else {
                System.out.println("PROBLEM - PREV SLIDE IS NULL!");
            }
        } else {
            System.out.println("First slide reached!");
        }
        return false;
    }

    /**
     * Displays the next slide in the presentation slider given that the last slide has not been
     * reached yet.
     * */
    private boolean showNextSlide()
    {
        if(this.currentSlide < presentation.getSlideCount()) {
            // Get the current slide
            Slide currentSlide = getCurrentSlide();

            // Increment values
            this.currentSlide++;
            this.currentSlideIndex++;

            //System.out.println("Next Slide: " + this.currentSlide);

            // Get the next slide
            SlideImage nextSlide = getNextSlide(currentSlide);

            if(nextSlide != null) {
                // Display the next slide
                displaySlide(nextSlide, this.mainDisplay);
                highlightSlide(nextSlide);
                return true;
            } else {
                System.out.println("PROBLEM - NEXT SLIDE IS NULL!");
            }
        } else {
            System.out.println("Last slide reached!");
        }
        return false;
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
    /*private void addNewSlide() {
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
    }*/

    /*private void deleteSlide() {
        Slide currentSlide = getCurrentSlide();
        // Delete from presentation slides
        //this.presentation.removeSlide(currentSlide);
        this.presentation.getSlides().remove(currentSlide);

        // Delete from slide images
        this.slideImages.remove(getSelectedSlide(currentSlideIndex));

        // Delete from slidesViewer
        JButton slideToDelete = this.slidesViewer.get(currentSlideIndex);
        this.slidesViewer.remove(slideToDelete);

        // Delete from slide viewer panel
        this.viewSliderPanel.remove(slideToDelete);

        // Update slides Viewer
        this.viewSliderPanel.revalidate();
        this.viewSliderPanel.repaint();

        System.out.println("Number of slides: " + this.presentation.getSlideCount() + " - SlideImages list size: " + this.slideImages.size() + " - SlidesInViewer is: " + this.slidesViewer.size() + " - No. of components in the slidesViewerPanel: " + this.viewSliderPanel.getComponentCount());

        if(showPrevSlide()) {
            System.out.println("Slide " + + (this.currentSlide + 1) + " deleted - Now displaying previous slide!");
        } else if(showNextSlide()) {
            System.out.println("Slide " + (this.currentSlide + 1) + " deleted - Now displaying next slide!");
        } else {
            // Show nothing an empty presentation
            System.out.println("Slide " + + (this.currentSlide + 1) + " deleted - Presentation empty!");
        }

        System.out.println("Current Slide On: " + this.currentSlide + " and current slide index is: " + this.currentSlideIndex);
    }*/

    /**
     * Creates a slide for the slides viewer
     * @param slideNo The slide number to assign it to
     * @return JButton
     * */
    private JButton createSlideForViewer(int slideNo) {
        JButton slide = new JButton();

        slide.setPreferredSize(new Dimension(200, 118));

        slide.setFocusable(false);

        ImageIcon previewSlideImage = new ImageIcon(slideImages.get(slideNo - 1).getBufferedSlideImage());
        slide.setIcon(GeneralButtons.resizeIcon(previewSlideImage, 200, 118));

        slide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                highlightSlide(slide);
                displaySelectedSlide(slide);
                //System.out.println("Slide " + (getSlidePosition(slide) + 1) + " Clicked!");
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
        //System.out.println("Slide " + slideNum + " has been added!");

        this.slidesViewer.add(slide);

        this.viewSliderPanel.add(slide);

        this.viewSliderPanel.revalidate();

        highlightSlide(slide);
    }
    /**
     * Gets the slide's slideImage before the selected slide. If there is none it returns null.
     * @param slide The current/selected slide
     * @return SlideImage
     */
    private SlideImage getPrevSlide(Slide slide) {
        int indexOfSelectedSlide = this.presentation.getSlides().indexOf(slide);
        int indexOfPrevSlide = indexOfSelectedSlide - 1;
        //System.out.println("On slide " + this.currentSlide + " - Was on slide " + (this.currentSlide + 1));

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
        //System.out.println("On slide " + this.currentSlide + " - Was on slide " + (this.currentSlide - 1));

        if(indexOfNextSlide <= this.presentation.getSlideCount()) {
            return this.slideImages.get(indexOfNextSlide);
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
     * Gets the selected slide's slideImage
     * @param slideIndex The index of the selected slide
     * @return SlideImage
     * */
    private SlideImage getSelectedSlide(int slideIndex) {
        return this.slideImages.get(slideIndex);
    }

    /**
     * Gets the selected slide's slideImage
     * @param slide The slide in the slide viewer
     * @return The slide's slideImage at the same index
     */
    private SlideImage getSelectedSlide(JButton slide) {
        int position = getSlidePosition(slide);
        return this.slideImages.get(position);
    }

    /**
     * Gets the index position of a slide
     * @param slide A slide
     * @return The index of the slide
     * @param <T> The type of slide
     */
    private <T> int getSlidePosition(T slide) {
        int position = -1;
        if(slide instanceof JButton) {
            position = this.slidesViewer.indexOf(slide);
        } else if (slide instanceof SlideImage) {
            position = this.slideImages.indexOf(slide);
        } else if (slide instanceof Slide) {
            position = this.presentation.getSlides().indexOf(slide);
        }
        return position;
    }

    /**
     * Highlights the slide in the slides viewer if the slide is selected or is currently being displayed
     * @param slide The slide in the slide viewer
     */
    private void highlightSlide(JButton slide) {
        // Set the highlighting of the previous slide to null
        if(this.prevHighlightedSlide != null) this.prevHighlightedSlide.setBorder(null);

        // Highlight new slide
        slide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        // Set the previous highlighted slide to the new slide
        this.prevHighlightedSlide = slide;
    }

    /**
     * Highlights the slide in the slides viewer if the slide is selected or is currently being displayed
     * @param slideImage The slide's slideImage that is going to/is being displayed
     */
    private void highlightSlide(SlideImage slideImage) {
        int position = this.slideImages.indexOf(slideImage);

        JButton slide = this.slidesViewer.get(position);
        // Set the highlighting of the previous slide to null
        this.prevHighlightedSlide.setBorder(null);
        // Highlight new slide
        slide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        // Set the previous highlighted slide to the new slide
        this.prevHighlightedSlide = slide;
    }

    /**
     * Displays the selected slideImage to the main display
     * @param slide The slide that was clicked/selected
     * */
    private void displaySelectedSlide(JButton slide) {
        SlideImage selectedSlide = getSelectedSlide(slide);
        int position = getSlidePosition(slide) + 1;

        this.currentSlide = position;
        this.currentSlideIndex = position - 1;

        //System.out.println("Slide " + this.currentSlide + " has been selected! " + " Slide index is " + this.currentSlideIndex);

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
            //deleteSlide();
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

        this.slideImages.clear();
        this.slidesViewer.clear();
        this.viewSliderPanel.removeAll();
        this.viewSliderPanel.revalidate();
        this.viewSliderPanel.repaint();

        if (current != null && current.getSlideCount() > 0) {
            showSlideAtIndex(0);
            return;
        }

        this.mainDisplay.setCurrentSlideImage(null);
        this.mainDisplay.clearPaintedSlide();
    }

    @Override
    public void onCreateSlide(int index, Slide slide) {
        // Keeps track of the slide currently being displayed
        this.currentSlide = this.presentation.getSlideCount();
        this.currentSlideIndex = this.currentSlide - 1;

        // -------- This code is only used to differentiate slides added
        slide.asComp().setBackground(new Color((float)this.presentation.getSlideCount() / 10 % 1, (float)this.presentation.getSlideCount() / 10 % 1, (float)this.presentation.getSlideCount() / 10 % 1));
        // --------

        // Create a new slide image of the slide
        SlideImage newSlideImage = new SlideImage(slide, this.mainDisplay);

        // Add to slide images list
        this.slideImages.add(newSlideImage);

        // The code below will display the new slide added to the main display panel if displayNewSlide equals true
        if(this.displayNewSlide) {
            // Display new slide
            displaySlide(newSlideImage, this.mainDisplay);
        }

        // Add slide to presentation slider
        addSlideToViewer();

        // Changes highlighting to slides added
        JButton slideInViewer = this.slidesViewer.get(index);
        highlightSlide(slideInViewer);

        System.out.println("New Slide - " + (this.presentation.getSlideCount()) + "!");
    }

    @Override
    public void onDiscardSlide(int index, Slide slide) {
        // Delete from slide images
        this.slideImages.remove(getSelectedSlide(index));

        // Delete from slidesViewer
        JButton slideToDelete = this.slidesViewer.get(index);
        this.slidesViewer.remove(slideToDelete);

        // Delete from slide viewer panel
        this.viewSliderPanel.remove(slideToDelete);

        // Update slides Viewer
        this.viewSliderPanel.revalidate();
        this.viewSliderPanel.repaint();

        //System.out.println("Number of slides: " + this.presentation.getSlideCount() + " - SlideImages list size: " + this.slideImages.size() + " - SlidesInViewer is: " + this.slidesViewer.size() + " - No. of components in the slidesViewerPanel: " + this.viewSliderPanel.getComponentCount());
        /*if(this.presentation.getSlideCount() > 1) {
            if(showPrevSlide()) {
                System.out.println("Slide " + + (this.currentSlide + 1) + " deleted - Now displaying previous slide!");
            } else if(showNextSlide()) {
                System.out.println("Slide " + (this.currentSlide - 1) + " deleted - Now displaying next slide!");
            } else {
                // Show nothing an empty presentation
                System.out.println("Slide 1 was deleted - Presentation empty!");
            }
        } else {
            displaySlide(this.slideImages.get(0), this.mainDisplay);
            highlightSlide(this.slidesViewer.get(0));
        }*/

        if(showPrevSlide()) {
            System.out.println("Slide " + + (this.currentSlide + 1) + " deleted - Now displaying previous slide!");
        } else if(showNextSlide()) {
            System.out.println("Slide " + (this.currentSlide - 1) + " deleted - Now displaying next slide!");
        } else {
            // Show nothing an empty presentation
            System.out.println("Slide 1 was deleted - Presentation empty!");
        }

        //System.out.println("Current Slide On: " + this.currentSlide + " and current slide index is: " + this.currentSlideIndex);
    }

    @Override
    public void onUpdateSlide(int index, Slide slide) {
        // Get the slide in the slideViewer at the index
        JButton previewSlide = this.slidesViewer.get(index);

        // Recreate the slide image (icon) for that slide in the slideViewer
        ImageIcon previewSlideImage = new ImageIcon(slideImages.get(index).getBufferedSlideImage());

        // Reset the image icon of that slide to the new slide image
        previewSlide.setIcon(GeneralButtons.resizeIcon(previewSlideImage, 200, 118));
    }
}
