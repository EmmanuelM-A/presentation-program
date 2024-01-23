package com.scc210groupproject.ui;

import com.scc210groupproject.structure.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class converts slides into images and handles the painting of the slides.
 */
public class SlideImage extends JPanel {
    // The image of current slide being displayed
    private BufferedImage bufferedSlideImage;

    // The current slide being displayed
    private Slide slide;

    // This variable is responsible for centering the slide image on the display
    private Point offset;

    // The scale
    private double scale;

    // Default slide dimensions (changed later once displayed)
    private Dimension slideDimension = new Dimension(1000, 600);

    /**
     * Allows you to create an instance of the SlideImage class
     * @param slide A slide
     * @param display An instance of the main display panel
     */
    public SlideImage(Slide slide, MainDisplayPanel display) {
        this.slide = slide;

        this.setLayout(null);

        int width = display.getWidth();
        int height = display.getHeight();

        this.bufferedSlideImage = this.slide.createPreview(slideDimension);

        double slideRatio = (double)bufferedSlideImage.getWidth() / (double)bufferedSlideImage.getHeight();
        double displayRatio = (double) width / (double) height;

        this.slideDimension = slideRatio > displayRatio ?
                new Dimension(width, (int) ((double) width / slideRatio)) :
                new Dimension((int) ((double) height * slideRatio), height);

        this.offset = new Point(
                (super.getWidth() - this.slideDimension.width) / 2,
                (super.getHeight() - this.slideDimension.height) / 2);

    }

    /**
     * Gets the buffer image of the slide that has been made.
     * @return BufferImage
     */
    public BufferedImage getBufferedSlideImage() {
        return this.bufferedSlideImage;
    }
    /**
     * Gets the slide that was used to create the slideImage.
     * @return Slide
     */
    public Slide getSlide() {
        return slide;
    }
    /**
     * Gets the offset of the image painted to the display.
     * @return Point
     */
    public Point getOffset() {
        return offset;
    }
    /**
     * Gets the scale.
     * @return double
     */
    public double getScale() {
        return scale;
    }
    /**
     * Gets the (default) slide dimension.
     * @return Dimension
     */
    public Dimension getSlideDimension() {
        return slideDimension;
    }

    /**
     * Allows you to change the buffer image of the slide image.
     * @param newBufferedSlideImage The bufferImage you wish to change to
     */
    public void setBufferedSlideImage(BufferedImage newBufferedSlideImage) {
        this.bufferedSlideImage = newBufferedSlideImage;
    }
    /**
     * Allows you to change the slide being used to create the slideImage
     * @param slide A slide instance
     */
    public void setSlide(Slide slide) {
        this.slide = slide;
    }
    /**
     * Allows you to change the offset of the image being painted
     * @param offset The new offset
     */
    public void setOffset(Point offset) {
        this.offset = offset;
    }

    /**
     * Allows you to change the scale
     * @param scale The new scale
     */
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Allows you to change the slide dimension being used
     * @param slideDimension The new slide
     */
    public void setSlideDimension(Dimension slideDimension) {
        this.slideDimension = slideDimension;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (this.bufferedSlideImage != null) {
            g.drawImage(this.bufferedSlideImage, offset.x, offset.y, null);
        }
    }
}
