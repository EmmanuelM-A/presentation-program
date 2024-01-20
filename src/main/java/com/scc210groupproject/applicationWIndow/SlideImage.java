package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.structure.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SlideImage extends JPanel {
    // The image of current slide being displayed
    private BufferedImage slideImage;

    // The current slide being displayed
    private Slide slide;

    // This variable is responsible for centering the slide image on the display
    private Point offset;

    // The scale
    private double scale;

    private Graphics2D g2;

    public SlideImage(Slide slide, Dimension dimension) {
        this.slide = slide;

        this.offset = new Point(50, 50);

        this.slideImage = this.slide.createPreview(dimension);
        this.g2 = (Graphics2D) slideImage.getGraphics();
    }

    public BufferedImage getSlideImage() {
        return this.slideImage;
    }
    public Slide getSlide() {
        return slide;
    }
    public Point getOffset() {
        return offset;
    }
    public double getScale() {
        return scale;
    }

    public void setSlideImage(BufferedImage newSlideImage) {
        this.slideImage = newSlideImage;
    }
    public void setSlide(Slide slide) {
        this.slide = slide;
    }
    public void setOffset(Point offset) {
        this.offset = offset;
    }
    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (this.slideImage != null) {
            g.drawImage(this.slideImage, 0, 0, null);
        }
    }

    /*public BufferedImage updateSlideImageDimensions(MainDisplayPanel display) {
        // Calculate the aspect ratio of the slide
        double slideRatio = (double)this.slide.asComp().getWidth() / (double)slide.asComp().getHeight();

        // Calculate the aspect ratio of the display
        double displayRatio = (double) display.getWidth() / display.getHeight();

        // Calculate the new aspect ratio saved as new dimension
        Dimension dimension = slideRatio > displayRatio ?
                new Dimension(display.getWidth(), (int)((double)display.getWidth() / slideRatio)) :
                new Dimension((int)((double)display.getHeight() * slideRatio), display.getHeight());

        // Change the dimensions of the slide image
        BufferedImage newSlideImage = (BufferedImage) this.slideImage.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);

        // Center image on the display
        offset = new Point(
                (display.getWidth() - dimension.width) / 2,
                (display.getHeight() - dimension.height) / 2);

        // Update scale
        scale = (double)slide.asComp().getWidth() / (double)dimension.width;

        return newSlideImage;
    }*/
}
