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

    public SlideImage getSlideImage() {
        return slideImage;
    }

    public void setSlideImage(SlideImage slideImage) {
        this.slideImage = slideImage;
        this.bufferedSlideImage = this.slideImage.getBufferedSlideImage();
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


    /*public void resizeBufferedSlideImage() {
        int width = getWidth();
        int height = getHeight();

        if (width > 0 && height > 0) {
            double aspectRatio = (double) bufferedSlideImage.getWidth() / bufferedSlideImage.getHeight();

            if (width > height) {
                width = Math.min(width, bufferedSlideImage.getWidth()); // Limit width
                height = (int) (width / aspectRatio);
            } else {
                height = Math.min(height, bufferedSlideImage.getHeight()); // Limit height
                width = (int) (height * aspectRatio);
            }

            Image scaledImage = bufferedSlideImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            bufferedSlideImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = bufferedSlideImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            repaint();
        }

    }*/

    // THE SLIDE SCALES PROPERLY BUT SORT OUT SLIDEMANAGER DISPALYING NEW SLIDE RUINS EVERYTHING AND HAVE OFFSET ADJUST AUTOMATICALLY AS THE SCREEN IS RESIZED.

    public void resizeBufferedSlideImage() {
        int width = this.getWidth();
        int height = this.getHeight();

        if (width > 0 && height > 0) {
            Dimension dimension = getDimension(width, height);

            /*Point newOffset = new Point(
                    (super.getWidth() - dimension.width) / 2,
                    (super.getHeight() - dimension.height) / 2);

            //System.out.println("Offset x:" + newOffset.x + " y: " + newOffset.y);

            slideImage.setOffset(newOffset);*/

            //System.out.println("Offset x:" + slideImage.getOffset().x + " y: " + slideImage.getOffset().y);

            slideImage.setBufferedSlideImage(slideImage.getSlide().createPreview(dimension));

            double newScale = (double)bufferedSlideImage.getWidth() / (double)dimension.width;

            slideImage.setScale(newScale);

            /*Image scaledImage = bufferedSlideImage.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH);
            bufferedSlideImage = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_ARGB);

            //System.out.println("New slide width: " + dimension.getWidth() + " height: " + dimension.getHeight());

            Graphics2D g2d = bufferedSlideImage.createGraphics();
            g2d.drawImage(scaledImage, slideImage.getOffset().x, slideImage.getOffset().y, null);
            g2d.dispose();*/
            repaint();
        }
    }

    private Dimension getDimension(int width, int height) {
        double slideRatio = (double)bufferedSlideImage.getWidth() / (double)bufferedSlideImage.getHeight();
        double displayRatio = (double) width / (double) height;

        //System.out.println("Display width: " + this.getWidth() + " height: " + this.getHeight());

        //System.out.println("Slide width: " + bufferedSlideImage.getWidth() + " height: " + bufferedSlideImage.getHeight());

        return slideRatio > displayRatio ?
                new Dimension(width, (int) ((double) width / slideRatio)) :
                new Dimension((int) ((double) height * slideRatio), height);
    }

    public void setBufferedSlideImage(BufferedImage newSlideImage) {
        this.bufferedSlideImage = newSlideImage;
        resizeBufferedSlideImage();
        repaint();
    }
}


