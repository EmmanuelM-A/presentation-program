package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.structure.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SlideImage extends JPanel {
    // The image of current slide being displayed
    private BufferedImage bufferedSlideImage;

    // The current slide being displayed
    private Slide slide;

    // This variable is responsible for centering the slide image on the display
    private Point offset;

    // The scale
    private double scale;

    private Dimension slideDimension = new Dimension(1000, 600);

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

    public BufferedImage getBufferedSlideImage() {
        return this.bufferedSlideImage;
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
    public Dimension getSlideDimension() {
        return slideDimension;
    }

    public void setBufferedSlideImage(BufferedImage newBufferedSlideImage) {
        this.bufferedSlideImage = newBufferedSlideImage;
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
