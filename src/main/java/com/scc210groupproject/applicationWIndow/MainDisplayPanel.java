package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
        this.setLayout(new BorderLayout());

        //this.bufferedSlideImage = this.slideImage.getSlideImage();

        this.currentPresentation = null;
        createNewPresentation();

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                //resizeSlideImage();
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

    public void setSlideImage(SlideImage slideImage) {
        this.slideImage = slideImage;
        this.bufferedSlideImage = this.slideImage.getSlideImage();
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


    public void resizeSlideImage() {
        int width = this.getWidth();
        int height = this.getHeight();

        System.out.println("Display width: " + width + " height: " + height);

        System.out.println("Slide width: " + bufferedSlideImage.getWidth() + " height: " + bufferedSlideImage.getHeight());

        if (width > 0 && height > 0) {
            Image scaledImage = bufferedSlideImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            bufferedSlideImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            System.out.println("New slide width: " + bufferedSlideImage.getWidth() + " height: " + bufferedSlideImage.getHeight());

            Graphics2D g2d = bufferedSlideImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            repaint();
        }

    }

    public void resizeBufferedSlideImage() {
        int width = this.getWidth();
        int height = this.getHeight();

        if (width > 0 && height > 0) {
            double slideRatio = (double) bufferedSlideImage.getWidth() / (double) bufferedSlideImage.getHeight();
            double displayRatio = (double)width / (double)height;

            System.out.println("Display width: " + this.getWidth() + " height: " + this.getHeight());

            System.out.println("Slide width: " + bufferedSlideImage.getWidth() + " height: " + bufferedSlideImage.getHeight());

            Dimension dimension = slideRatio > displayRatio ?
                    new Dimension(width, (int) ((double)width / slideRatio)) :
                    new Dimension((int) ((double) height * slideRatio), height);

            Image scaledImage = bufferedSlideImage.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH);
            bufferedSlideImage = new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_ARGB);

            System.out.println("New slide width: " + dimension.getWidth() + " height: " + dimension.getHeight());

            Graphics2D g2d = bufferedSlideImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            repaint();
        }
    }

    public void setBufferedSlideImage(BufferedImage newSlideImage) {
        this.bufferedSlideImage = newSlideImage;
        //resizeSlideImage();
        resizeBufferedSlideImage();
        repaint();
    }
}


