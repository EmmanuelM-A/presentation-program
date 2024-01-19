package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import com.scc210groupproject.structure.*;
import com.scc210groupproject.ui.ScaledPanel;

public class MainDisplayPanel extends JPanel
{
    public static MainDisplayPanel instance;
    private Presentation currentPresentation;
    private ScaledPanel scaledSlide;

    /*private BufferedImage slideImage;
    private Point offset;
    private Slide slide;
    private double slideScale;*/

    public MainDisplayPanel(int width, int height, Color colour)
    {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        //this.setLayout(new GridBagLayout());
        //this.setLayout(new BorderLayout());

        scaledSlide = new ScaledPanel();
        super.add(scaledSlide);

        super.addComponentListener(new ComponentAdapter() {

            private SlideManager slideManager;
            @Override
            public void componentResized(ComponentEvent e) {
                //scaledSlide.renderSlide();
            }
        });

        this.currentPresentation = null;
        createNewPresentation();

        instance = this;
    }

    /*public void setSlideManager(SlideManager slideManager) {
        this.slideManager = slideManager;
    }

    public SlideManager getSlideManager() {
        return this.slideManager;
    }*/

    public ScaledPanel getScaledSlide() {
        return this.scaledSlide;
    }

    /*public void onPanelResize(SlideManager slideManager) {
        scaledSlide = new ScaledPanel();
        super.add(scaledSlide);

        Slide currentSlide = S;
        currentSlide.setBackground(Color.BLUE);
        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scaledSlide.renderSlide(currentSlide);
            }
        });

        /*Presentation.addChangePresentationListener(this);
        Presentation.addUpdateSlideListener(this);
        Presentation.addDiscardSlideListener(this);
    }*/

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

    /*public void setCurrentPresentation(Presentation currentPresentation)
    {
        this.currentPresentation = currentPresentation;
        insertIntoDisplay();
    }*/

    /*@Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (slideImage == null)
            return;

        ((Graphics2D)g).drawImage(slideImage, offset.x, offset.y, null);
    }

    private void updateSlideDimensions(Slide slide) {
        this.slide = slide;

        if (slide == null)
            return;

        double slideRatio = (double)slide.asComp().getWidth() / (double)slide.asComp().getHeight();
        double diplayRatio = (double) super.getWidth() / super.getHeight();

        Dimension dimension = slideRatio > diplayRatio ?
                new Dimension(super.getWidth(), (int)((double)super.getWidth() / slideRatio)) :
                new Dimension((int)((double)super.getHeight() * slideRatio), super.getHeight());

        slideImage = slide.createPreview(dimension);
        offset = new Point(
                (super.getWidth() - dimension.width) / 2,
                (super.getHeight() - dimension.height) / 2);
        slideScale = (double)slide.asComp().getWidth() / (double)dimension.width;

        super.repaint();
    }

    public void clearRender() {
        slideImage = null;
        slide = null;

        super.repaint();
    }*/
}


