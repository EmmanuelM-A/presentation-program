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

    public SlideImage(Slide slide, Dimension dimension) {
        this.slide = slide;

        this.setLayout(null);

        this.bufferedSlideImage = this.slide.createPreview(dimension);

        /*this.offset = new Point(
                (super.getWidth() - this.bufferedSlideImage.getWidth()) / 2,
                (super.getHeight() - this.bufferedSlideImage.getHeight()) / 2);*/

        this.offset = new Point(0, 0);
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

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (this.bufferedSlideImage != null) {
            g.drawImage(this.bufferedSlideImage, offset.x, offset.y, null);
        }
    }
}
