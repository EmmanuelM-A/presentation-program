package com.scc210groupproject.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import com.scc210groupproject.structure.*;
import com.scc210groupproject.structure.eventListeners.IUpdateSlideListener;
import com.scc210groupproject.structure.input.InputEmulator;


/**
 * This class handles the displaying of the slides as well as the automatic resizing of slides displayed when
 * the frame is resized.
 */
public class MainDisplayPanel extends JPanel implements IUpdateSlideListener
{
    // The slideImage to be displayed
    private SlideImage currentSlideImage;

    // The corresponding bufferedSlideImage for the slideImage
    private BufferedImage bufferedSlideImage;

    // The main display panel
    public static MainDisplayPanel instance;

    // The current presentation being viewed/used in the program
    private Presentation currentPresentation;
    
    private InputEmulator emulator;

    public InputEmulator getInputEmulator()
    {
        return emulator;
    }

    public MainDisplayPanel(int width, int height, Color colour)
    {
        emulator = new InputEmulator();

        super.addMouseListener(emulator);
        super.addMouseMotionListener(emulator);
        super.addMouseWheelListener(emulator);
        super.addKeyListener(emulator);
        super.setFocusable(true);

        Presentation.addUpdateSlideListener(this);

        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        super.setLayout(new GridLayout(0, 1));

        this.currentPresentation = null;
        createNewPresentation();


        
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                updateBufferedSlideImage();
            }

            @Override
            public void componentMoved(ComponentEvent componentEvent) {

            }

            @Override
            public void componentShown(ComponentEvent componentEvent) {

            }

            @Override
            public void componentHidden(ComponentEvent componentEvent) {

            }
        });

        instance = this;
    }

    /**
     * Gets the slideImage currently being displayed
     * @return SlideImage
     */
    public SlideImage getCurrentSlideImage() {
        return currentSlideImage;
    }

    /**
     * Gets the bufferedSlideImage that is painted
     * @return BufferImage
     */
    public BufferedImage getBufferedSlideImage() {
        return bufferedSlideImage;
    }

    /**
     * Gets the current presentation
     * @return Presentation
     */
    public Presentation getCurrentPresentation()
    {
        return this.currentPresentation;
    }

    /**
     * Allows you to change the slideImage as well as set the bufferedSlideImage.
     * @param newslideImage The newSlideImage you wish to change to
     */
    public void setCurrentSlideImage(SlideImage newslideImage) {
            
        Slide newSlide = newslideImage != null ? newslideImage.getSlide() : null;
        Slide currentSlide = currentSlideImage != null ? currentSlideImage.getSlide() : null;
        if (newSlide != currentSlide)
            emulator.setTargetSlide(newSlide);

        this.currentSlideImage = newslideImage;
        setBufferedSlideImage(this.currentSlideImage.getBufferedSlideImage());
    }

    /**
     * Allows you to change the bufferedSlideImage being painted to the display.
     * @param newBufferedSlideImage The bufferImage you wish to paint
     */
    private void setBufferedSlideImage(BufferedImage newBufferedSlideImage) {
        this.bufferedSlideImage = newBufferedSlideImage;
    }

    public void createNewPresentation()
    {
        currentPresentation = Presentation.getOrCreate();
        insertIntoDisplay();
    }

    private void insertIntoDisplay()
    {
        this.removeAll();
        JPanel panel = (JPanel)currentPresentation.getSlideAtIndex(0).asComp();
        panel.setBackground(Color.ORANGE);
        this.add(panel);
        this.revalidate();
    }

    /**
     * Resizes the current slide image being displayed so that it grows and shrinks proportionally to the size of the frame.
     * The offset of the image is also maintained as frame changes size.
     */
    public void updateBufferedSlideImage() {

        Component slideComp = currentSlideImage.getSlide().asComp();

        // Get the dimensions of the display
        int width = this.getWidth();
        int height = this.getHeight();

        if (width > 0 && height > 0) {
            // Calculate slide aspect ratio and display aspect ratio
            double slideRatio = (double)slideComp.getWidth() / (double)slideComp.getHeight();
            double displayRatio = (double) width / (double) height;

            // Determine the slide dimension to be used
            Dimension slideDimension = slideRatio > displayRatio ?
                    new Dimension(width, (int) ((double) width / slideRatio)) :
                    new Dimension((int) ((double) height * slideRatio), height);

            // Calculate and set the new offset
            Point newOffset = new Point(
                    (super.getWidth() - slideDimension.width) / 2,
                    (super.getHeight() - slideDimension.height) / 2);
            currentSlideImage.setOffset(newOffset);

            // Set the new bufferedSlideImage to the newly created buffer image with the new slide dimensions
            currentSlideImage.setBufferedSlideImage(currentSlideImage.getSlide().createPreview(slideDimension));

            // Calculate and set the new scale
            double newScale = (double)bufferedSlideImage.getWidth() / (double) slideDimension.width;
            currentSlideImage.setScale(newScale);

            
            emulator.setPositioning(newOffset, (double)slideComp.getWidth() / (double)slideDimension.width);

            repaint();
        }
    }

    /**
     * Removes the painted image from display
     */
    public void clearPaintedSlide() {
        this.bufferedSlideImage = null;
        this.currentSlideImage.setSlide(null);

        super.repaint();
    }

    @Override
    public void onUpdateSlide(int index, Slide slide) {
        updateBufferedSlideImage();
    }
}


