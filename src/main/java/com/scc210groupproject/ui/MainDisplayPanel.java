package com.scc210groupproject.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import com.scc210groupproject.structure.*;


/**
 * This class handles the displaying of the slides as well as the automatic resizing of slides displayed when
 * the frame is resized.
 */
public class MainDisplayPanel extends JPanel
{
    // The slideImage to be displayed
    private SlideImage slideImage;

    // The corresponding bufferedSlideImage for the slideImage
    private BufferedImage bufferedSlideImage;

    // The main display panel
    public static MainDisplayPanel instance;

    // The current presentation being viewed/used in the program
    private Presentation currentPresentation;

    public MainDisplayPanel(int width, int height, Color colour)
    {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        super.setLayout(new GridLayout(0, 1));

        this.currentPresentation = null;
        createNewPresentation();

        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (slideImage.getSlide() != null) {
                    System.out.println(slideImage.getSlide().findElmentAt(new Point(
                            (int)((double)(e.getX() - slideImage.getOffset().x) * slideImage.getScale()),
                            (int)((double)(e.getY() - slideImage.getOffset().y) * slideImage.getScale()))));
                }
            }
        });

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                resizeBufferedSlideImage();
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
    public SlideImage getSlideImage() {
        return slideImage;
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
    public void setSlideImage(SlideImage newslideImage) {
        this.slideImage = newslideImage;
        setBufferedSlideImage(this.slideImage.getBufferedSlideImage());
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
    public void resizeBufferedSlideImage() {
        // Get the dimensions of the display
        int width = this.getWidth();
        int height = this.getHeight();

        if (width > 0 && height > 0) {
            // Calculate slide aspect ratio and display aspect ratio
            double slideRatio = (double)bufferedSlideImage.getWidth() / (double)bufferedSlideImage.getHeight();
            double displayRatio = (double) width / (double) height;

            // Determine the slide dimension to be used
            Dimension slideDimension = slideRatio > displayRatio ?
                    new Dimension(width, (int) ((double) width / slideRatio)) :
                    new Dimension((int) ((double) height * slideRatio), height);

            // Calculate and set the new offset
            Point newOffset = new Point(
                    (super.getWidth() - slideDimension.width) / 2,
                    (super.getHeight() - slideDimension.height) / 2);
            slideImage.setOffset(newOffset);

            // Set the new bufferedSlideImage to the newly created buffer image with the new slide dimensions
            slideImage.setBufferedSlideImage(slideImage.getSlide().createPreview(slideDimension));

            // Calculate and set the new scale
            double newScale = (double)bufferedSlideImage.getWidth() / (double) slideDimension.width;
            slideImage.setScale(newScale);

            repaint();
        }
    }
}


