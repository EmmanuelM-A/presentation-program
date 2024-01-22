package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.scc210groupproject.structure.*;
//import com.scc210groupproject.ui.ScaledPanel;

public class MainDisplayPanel extends JPanel
{
    private SlideImage slideImage;
    private BufferedImage bufferedSlideImage;
    public static MainDisplayPanel instance;
    private Presentation currentPresentation;

    private Dimension slideDimension;

    public MainDisplayPanel(int width, int height, Color colour)
    {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        //this.setLayout(new GridBagLayout());
        //this.setLayout(new BorderLayout());
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

        //resizeBufferedSlideImage();

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
        /*panel.setPreferredSize(panel.getSize());
        panel.setMinimumSize(panel.getSize());
        panel.setMaximumSize(panel.getSize());*/

        this.add(panel);
        this.revalidate();
    }

    public Presentation getCurrentPresentation()
    {
        return this.currentPresentation;
    }

    public void resizeBufferedSlideImage() {
        int width = this.getWidth();
        int height = this.getHeight();

        if (width > 0 && height > 0) {
            double slideRatio = (double)bufferedSlideImage.getWidth() / (double)bufferedSlideImage.getHeight();
            double displayRatio = (double) width / (double) height;

            System.out.println("Slide width: " + bufferedSlideImage.getWidth() + " height: " + bufferedSlideImage.getHeight());

            this.slideDimension = slideRatio > displayRatio ?
                    new Dimension(width, (int) ((double) width / slideRatio)) :
                    new Dimension((int) ((double) height * slideRatio), height);

            Point newOffset = new Point(
                    (super.getWidth() - this.slideDimension.width) / 2,
                    (super.getHeight() - this.slideDimension.height) / 2);

            slideImage.setOffset(newOffset);

            //System.out.println("Offset x:" + slideImage.getOffset().x + " y: " + slideImage.getOffset().y);

            slideImage.setBufferedSlideImage(slideImage.getSlide().createPreview(this.slideDimension));

            double newScale = (double)bufferedSlideImage.getWidth() / (double)this.slideDimension.width;

            slideImage.setScale(newScale);

            System.out.println("New slide width: " + slideDimension.getWidth() + " height: " + slideDimension.getHeight());

            repaint();
        }
    }

    public SlideImage getSlideImage() {
        return slideImage;
    }

    public BufferedImage getBufferedSlideImage() {
        return this.bufferedSlideImage;
    }

    public void setBufferedSlideImage(BufferedImage newSlideImage) {
        this.bufferedSlideImage = newSlideImage;
        resizeBufferedSlideImage();
        repaint();
    }

    public Dimension getSlideDimension() {
        return this.slideDimension;
    }

    public void setSlideImage(SlideImage slideImage) {
        this.slideImage = slideImage;
        this.bufferedSlideImage = this.slideImage.getBufferedSlideImage();
    }
}


