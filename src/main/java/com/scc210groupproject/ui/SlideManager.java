package com.scc210groupproject.ui;

import com.scc210groupproject.action.DeleteSlideAction;
import com.scc210groupproject.action.NewSlideAction;
import com.scc210groupproject.action.NextSlideAction;
import com.scc210groupproject.action.PresentAction;
import com.scc210groupproject.action.PresentAtAction;
import com.scc210groupproject.action.PrevSlideAction;
import com.scc210groupproject.structure.*;

import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;
import com.scc210groupproject.structure.eventListeners.IUpdateSlideListener;
import com.scc210groupproject.structure.liveness.IUpdateListener;
import com.scc210groupproject.ui.helper.GeneralButtons;
import com.scc210groupproject.ui.menuBarTabs.toolBars.SlideMiniToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * This class handles the slides currently being displayed on the screen as well as in the slide viwer.
 */
public class SlideManager implements IChangePresentationListener, ICreateSlideListener, IDiscardSlideListener, IUpdateSlideListener
{
    /**
     * The instance of the slideMnaager class
     */
    public static SlideManager slideManager;
    /**
     * This list represents the slides in the slide view slider
     */
    private final LinkedList<JButton> slidesViewer = new LinkedList<>();
    /**
     * Determines if a new slide added should be displayed or not
     */
    private boolean displayNewSlide = true;
    /**
     * The current index of the slide being displayed (starts from zero)
     */
    private int currentSlideIndex;
    /**
     * This JPanel will be slide viewer for all the slides in the presentation
     */
    private final JPanel viewSliderPanel;
    /**
     * The actual presentation slider
     */
    private final JScrollPane presentationSlider;
    /**
     * The buttons that will be used manipulate and traverse slides 
     */
    private JButton prevSlide, nextSlide, addNewSlide, deleteSlide, present, presentAt;
    /**
     * This reperesents the number of slides added as well as the current slide being displayed
     */
    //private JLabel noSlides;
    /**
     * Used as a temp variable to store previously highlighted slides
     */
    private JButton prevHighlightedSlide = null;
    /**
     * This list represents the slideImages that are painted onto the display
     */
    private LinkedList<SlideImage> slideImages = new LinkedList<>();

    /**
     * Constructor for the SlideManager
     *
     * @param mainDisplay The instance of the main display panel that is being used in the ui frame
     */
    public SlideManager(MainDisplayPanel mainDisplay) {
        // The index of the current slide being displayed
        this.currentSlideIndex = 0;

        // This contains the alternate display/viewer where all slides will be displayed and can be selected or scrolled through
        this.viewSliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.presentationSlider = new JScrollPane(this.viewSliderPanel);

        Presentation.addCreateSlideListener(this);
        Presentation.addUpdateSlideListener(this);
        Presentation.addDiscardSlideListener(this);
        Presentation.addChangePresentationListener(this);

    }

    //////////// GETTERS ////////////

    /**
     * Returns the value of the displayNewSlide variable
     * @return Boolean
     */
    public Boolean getDisplayNewSlides() {
        return this.displayNewSlide;
    }
    

    public int getCurrentSlideIndex() {
        return this.currentSlideIndex;
    }

    /**
     * Returns the number of slideImages
     * @return int
     */
    public int getSlideCount() {
        return this.slideImages.size();
    }

    /**
     * Gets the current slideImage's list
     * @return LinkedList<SlideImage>
     */
    public LinkedList<SlideImage> getSlideImages() {
        return this.slideImages;
    }

    /**
     * Returns the slideImage at a given index
     * @param index The index of the slideImage
     * @return SlideImage
     */
    public SlideImage getSlideImageAt(int index) {
        return this.slideImages.get(index);
    }

    /**
     * Gets the slide's slideImage before the selected slide. If there is none it returns null.
     * @param slide The current/selected slide
     * @return SlideImage
     */
    private SlideImage getPrevSlide(Slide slide) {
        int indexOfSelectedSlide = Presentation.get().getSlides().indexOf(slide);
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
        int indexOfSelectedSlide = Presentation.get().getSlides().indexOf(slide);
        int indexOfNextSlide = indexOfSelectedSlide + 1;
        //System.out.println("On slide " + this.currentSlide + " - Was on slide " + (this.currentSlide - 1));

        if(indexOfNextSlide <= Presentation.get().getSlideCount()) {
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
        if(this.currentSlideIndex >= 0 && this.currentSlideIndex < Presentation.get().getSlideCount()) {
            return Presentation.get().getSlideAtIndex(this.currentSlideIndex);
        } else {
            return null;
        }
    }

    /**
     * Gets the selected slide's slideImage.
     * @param slide The slide in the slide viewer
     * @return The slide's slideImage at the same index
     */
    private SlideImage getSelectedSlide(JButton slide) {
        int position = getSlidePosition(slide);
        return this.slideImages.get(position);
    }

    /**
     * Gets the index of the slide in its slide list.
     * @param slide The slide (JButton) you wish to get its index
     * @return int
     */
    private int getSlidePosition(JButton slide) {
        return this.slidesViewer.indexOf(slide);
    }

    /**
     * Gets the index of the slide in its slide list.
     * @param slide The slideImage you wish to get its index
     * @return int
     */
    @SuppressWarnings("unused")
    private int getSlidePosition(SlideImage slide) {
        return this.slideImages.indexOf(slide);
    }

    /**
     * Gets the index of the slide in its slide list.
     * @param slide The slide instance you wish to get its index
     * @return int
     */
    private int getSlidePosition(Slide slide) {
        return Presentation.get().getSlides().indexOf(slide);
    }

    //////////// SETTERS ////////////

    /**
     * Set to true to display new slides added or false to not display new
     * slides added.
     * @param yesOrNo Either true or false
     * */
    public void setDisplayNewSlides(Boolean yesOrNo) {
        this.displayNewSlide = yesOrNo;
    }

    public void setSlideImages(LinkedList<SlideImage> slides) {
        this.slideImages = slides;
    }

    //////////// METHODS ////////////

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
        this.prevSlide = new JButton(GeneralButtons.getIconFromFile("angle-left.png"));
        this.prevSlide.setPreferredSize(new Dimension(50, 160));
        PrevSlideAction prevSlideAction = new PrevSlideAction();
        this.prevSlide.addActionListener(prevSlideAction);
        this.prevSlide.setFocusable(false);
        this.prevSlide.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "previous");
        this.prevSlide.getActionMap().put("previous", prevSlideAction);

        /*
         * Next slide button
         * */
        this.nextSlide = new JButton(GeneralButtons.getIconFromFile("angle-right.png"));
        this.nextSlide.setPreferredSize(new Dimension(50, 160));
        NextSlideAction nextSlideAction = new NextSlideAction();
        this.nextSlide.addActionListener(nextSlideAction);
        this.nextSlide.setFocusable(false);
        this.nextSlide.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "next");
        this.nextSlide.getActionMap().put("next", nextSlideAction);

        /*
         * This is where the actual presentation slider will go
         * */
        this.viewSliderPanel.setBackground(UIManager.getColor("Main.Dim"));
        presentationSlider.setPreferredSize(new Dimension(1000, 160));
        presentationSlider.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        UIFrame.instance.addUpdateListener(new IUpdateListener() {
            @Override
            public void onUpdate(Object object) {
                viewSliderPanel.setBackground(UIManager.getColor("Main.Dim"));
            }
        });

        /*
         * Bottom Section of the presentation slider where buttons such as newSlide, deleteSlide,
         * etc. will be placed. As well as a number of slides display.
         * */
        JPanel bottomSection = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomSection.setPreferredSize(new Dimension(1000, 35));
        bottomSection.setBackground(UIManager.getColor("Main.Dim"));
        UIFrame.instance.addUpdateListener(new IUpdateListener() {
            @Override
            public void onUpdate(Object object) {
                bottomSection.setBackground(UIManager.getColor("Main.Dim"));
            }
        });

        this.addNewSlide = new JButton(GeneralButtons.resizeIcon(
            GeneralButtons.NEW_SLIDE.getIcon(), 20, 20));
        this.addNewSlide.addActionListener(new NewSlideAction());
        this.addNewSlide.setToolTipText("New Slide");

        this.deleteSlide = new JButton(GeneralButtons.resizeIcon(
            GeneralButtons.DELETE_SLIDE.getIcon(), 20, 20));
        DeleteSlideAction deleteAction = new DeleteSlideAction();
        deleteAction.setButton(this.deleteSlide);
        this.deleteSlide.addActionListener(deleteAction);
        this.deleteSlide.setToolTipText("Delete Slide");


        this.present = new JButton(GeneralButtons.resizeIcon(
            GeneralButtons.PRESENT.getIcon(), 20, 20));
        this.present.setToolTipText("Present");
        this.present.addActionListener(new PresentAction());
        this.present.setMnemonic(KeyEvent.VK_P);

        this.presentAt = new JButton(GeneralButtons.resizeIcon(
            GeneralButtons.PRESENT_AT.getIcon(), 20, 20));
        this.presentAt.setToolTipText("Present From");
        this.presentAt.addActionListener(new PresentAtAction());
        this.presentAt.setMnemonic(KeyEvent.VK_C);

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
     * Displays the previous slide in the presentation slider given that there is at least one slide
     * present.
     * */
    public boolean showPrevSlide()
    {
        if(this.currentSlideIndex > 0) {
            // Get the current slide
            Slide currentSlide = getCurrentSlide();

            if(currentSlide == null) System.out.println("PROBLEM WITH CURRENT SLIDE");

            // Decrement values
            this.currentSlideIndex--;

            // Get the previous slide if there is one
            SlideImage prevSlide = getPrevSlide(currentSlide);

            if(prevSlide != null) {
                // Display the previous slide
                displaySlide(prevSlide, MainDisplayPanel.instance);
                highlightSlide(prevSlide);
                return true;
            } else {
                //System.out.println("PROBLEM - PREV SLIDE IS NULL!");
            }
        } else {
            //System.out.println("First slide reached!");
        }
        return false;
    }

    /**
     * Displays the next slide in the presentation slider given that the last slide has not been
     * reached yet.
     * */
    public boolean showNextSlide()
    {
        if(this.currentSlideIndex < Presentation.get().getSlideCount() - 1) {
            // Get the current slide
            Slide currentSlide = getCurrentSlide();

            // Increment values
            this.currentSlideIndex++;

            // Get the next slide
            SlideImage nextSlide = getNextSlide(currentSlide);

            if(nextSlide != null) {
                // Display the next slide
                displaySlide(nextSlide, MainDisplayPanel.instance);
                highlightSlide(nextSlide);
                return true;
            } else {
                //System.out.println("PROBLEM - NEXT SLIDE IS NULL!");
            }
        } else {
            //System.out.println("Last slide reached!");
        }
        return false;
    }

    /**
     * Displays/Paints the slideToDisplay onto the main display (via the main display panel)
     * @param slideToDisplay The slideImage to add to the display
     * @param display The display the slide will be added and removed from
     */
    public void displaySlide(SlideImage slideToDisplay, MainDisplayPanel display) {
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
     * Creates a slide for the slides viewer
     * @param slideNo The slide number to assign it to
     * @param setPreview Determines weither a preview should set or just be white
     * @return JButton
     * */
    private JButton createSlideForViewer(int slideNo, boolean setPreview) {
        JButton slide = new JButton();

        slide.setPreferredSize(new Dimension(200, 118));

        slide.setFocusable(false);

        ImageIcon previewSlideImage;

        if(setPreview == true) {
            previewSlideImage = new ImageIcon(slideImages.get(slideNo - 1).getBufferedSlideImage());
        } else {
            previewSlideImage = new ImageIcon(MainDisplayPanel.instance.getBufferedSlideImage());
        }

        slide.setIcon(GeneralButtons.resizeIcon(previewSlideImage, 200, 118));

        slide.setToolTipText("Slide " + slideNo);

        slide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                highlightSlideOnClick(slide);
                displaySelectedSlide(slide);
                displaySlideToolbar(slide);
                //System.out.println("Slide " + (getSlidePosition(slide) + 1) + " Clicked!");
            }
        });

        return slide;
    }

    /**
     * Adds a new slide to the slide viewer at given index. If the index is -1 it will add it onto the
     * end of the slide viewer instead.
     * @param index The index to add the new slide in the slide viewer
     */
    private void addSlideToViewer(int index) {
        if(index == -1) {
            int slideNum = this.slidesViewer.size() + 1;

            JButton slide = createSlideForViewer(slideNum, true);

            this.slidesViewer.add(slide);
            this.viewSliderPanel.add(slide);

            this.viewSliderPanel.revalidate();

            highlightSlide(slide);
        } else if(index >= 0 && index <= this.slidesViewer.size()){
            JButton slide = createSlideForViewer(index, false);

            this.slidesViewer.add(index, slide);
            this.viewSliderPanel.add(slide, index);

            this.viewSliderPanel.revalidate();

            highlightSlide(slide);
        }
    }

    public void addNewSlideAt(int index) {
        // Create a slide
        Slide slide = new Slide(new Dimension(1920, 1080));

        // Set the slide colour
        slide.setBackground(Color.WHITE);

        // Add slide to slides at index
        Presentation.get().getSlides().add(index, slide);

        // Add update listener
        slide.addUpdateListener(Presentation.get());

        // Keeps track of the slide currently being displayed
        this.currentSlideIndex = index;

        // Create a new slide image of the slide
        SlideImage newSlideImage = new SlideImage(slide, MainDisplayPanel.instance);

        // Add to slide images list
        this.slideImages.add(index, newSlideImage);

        // The code below will display the new slide added to the main display panel if displayNewSlide equals true
        if(this.displayNewSlide) {
            // Display new slide
            displaySlide(newSlideImage, MainDisplayPanel.instance);
        }

        // Add slide to presentation slider
        addSlideToViewer(index);

        // Update slide numbers
        for(int i = 0; i < this.slidesViewer.size(); i++) {
            this.slidesViewer.get(i).setToolTipText("Slide " + (i + 1));
        }
    }

    /**
     * Add a slide after the selected slide
     * @param selectedSlide The selected slide
     * */
    public void addSlideAfter() {
        int index = getSlidePosition(getCurrentSlide());
        //System.out.println("Slide " + index + " Clicked!");

        addNewSlideAt(index + 1);
    }

    /**
     * Add a slide before the selected/current slide
     * @param selectedSlide The selected slide
     * */
    public void addSlideBefore() {
        int index = getSlidePosition(getCurrentSlide());
        //System.out.println("Slide " + index + " Clicked!");

        addNewSlideAt(index);
    }

    

    private void setSelectedSlide(JButton slide) {
        this.currentSlideIndex = getSlidePosition(slide);
    }

    /**
     * Highlights the slide in the slides viewer if the slide is clicked
     * @param slide The slide in the slide viewer
     */
    private void highlightSlideOnClick(JButton slide) {
        // Set the highlighting of the previous slide to null
        if(this.prevHighlightedSlide != null) this.prevHighlightedSlide.setBorder(null);

        // Highlight new slide
        slide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        // Set the highlighted slide as the selected slide
        setSelectedSlide(slide);

        // Set the previous highlighted slide to the new slide
        this.prevHighlightedSlide = slide;
    }

    /**
     * Highlights the slide in the slides viewer if the slide is selected or is currently being displayed
     * @param slide The slide in the slide viewer
     */
    private void highlightSlide(JButton slide) {
        if(getDisplayNewSlides()) {
            // Set the highlighting of the previous slide to null
            if(this.prevHighlightedSlide != null) this.prevHighlightedSlide.setBorder(null);

            // Highlight new slide
            slide.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

            // Set the highlighted slide as the selected slide
            setSelectedSlide(slide);

            // Set the previous highlighted slide to the new slide
            this.prevHighlightedSlide = slide;
        }
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
        this.currentSlideIndex = getSlidePosition(slide);

        //System.out.println("Slide " + this.currentSlide + " has been selected! " + " Slide index is " + this.currentSlideIndex);

        // Display selected slide
        displaySlide(selectedSlide, MainDisplayPanel.instance);
    }

    public void displaySlideToolbar(JButton slide) {
        slide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    popupMenu.add(new SlideMiniToolBar());

                    popupMenu.show(slide, e.getX(), e.getY());
                }
            }
        });
    }

    /**
     * Displays the slide at a given index
     * @param index The index of the slide to display
     */
    public void showSlideAtIndex(int index) {
        displaySlide(this.slideImages.get(index), MainDisplayPanel.instance);
        highlightSlide(this.slidesViewer.get(index));
    }

    //////////// INTERFACE METHODS ////////////

    @Override
    public void onChangePresentation(Presentation current, Presentation discarded) {

        this.slideImages.clear();
        this.slidesViewer.clear();
        this.viewSliderPanel.removeAll();
        this.viewSliderPanel.revalidate();
        this.viewSliderPanel.repaint();

        MainDisplayPanel.instance.setCurrentSlideImage(null);
        MainDisplayPanel.instance.clearPaintedSlide();
    }

    @Override
    public void onCreateSlide(int index, Slide slide) {
        // Keeps track of the slide currently being displayed
        this.currentSlideIndex = Presentation.get().getSlideCount() - 1;

        // Create a new slide image of the slide
        SlideImage newSlideImage = new SlideImage(slide, MainDisplayPanel.instance);

        // Add to slide images list
        this.slideImages.add(newSlideImage);

        // The code below will display the new slide added to the main display panel if displayNewSlide equals true
        if(this.displayNewSlide) {
            // Display new slide
            displaySlide(newSlideImage, MainDisplayPanel.instance);
        }

        // Add slide to presentation slider
        addSlideToViewer(-1);

        //System.out.println("New Slide - " + (Presentation.get().getSlideCount()) + "!");
    }

    @Override
    public void onDiscardSlide(int index, Slide slide) {
        // Delete from slide images
        this.slideImages.remove(index);

        // Delete from slidesViewer
        JButton slideToDelete = this.slidesViewer.get(index);
        this.slidesViewer.remove(slideToDelete);

        // Delete from slide viewer panel
        this.viewSliderPanel.remove(slideToDelete);

        // Update slides Viewer
        this.viewSliderPanel.revalidate();
        this.viewSliderPanel.repaint();

        if(index == 0) {
            displaySlide(this.slideImages.get(0), MainDisplayPanel.instance);
            highlightSlide(this.slidesViewer.get(0));
        } else if (index == this.slidesViewer.size()){
            displaySlide(this.slideImages.get(this.slidesViewer.size() - 1), MainDisplayPanel.instance);
            highlightSlide(this.slidesViewer.get(this.slidesViewer.size() - 1));
        } else {
            if (showNextSlide()) {
                //System.out.println("Slide " + (this.currentSlideIndex) + " deleted - Now displaying next slide!");
            } else if(showPrevSlide()) {
                //System.out.println("Slide " + (this.currentSlideIndex + 2) + " deleted - Now displaying previous slide!");
            }
        }

        for(int i = 0; i < this.slidesViewer.size(); i++) {
            this.slidesViewer.get(i).setToolTipText("Slide " + (i + 1));
        }
    }

    @Override
    public void onUpdateSlide(int index, Slide slide) {
        
        if (index != currentSlideIndex)
            MainDisplayPanel.instance.updateBufferedSlideImage(slideImages.get(index), 200, 118);
        
        // Get the slide in the slideViewer at the index
        JButton previewSlide = this.slidesViewer.get(index);

        // Recreate the slide image (icon) for that slide in the slideViewer
        ImageIcon previewSlideImage = new ImageIcon(slideImages.get(index).getBufferedSlideImage());

        // Reset the image icon of that slide to the new slide image
        previewSlide.setIcon(GeneralButtons.resizeIcon(previewSlideImage, 200, 118));

    }
}
