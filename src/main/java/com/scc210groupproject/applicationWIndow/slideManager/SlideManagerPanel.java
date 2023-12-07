package com.scc210groupproject.applicationWIndow.slideManager;

import javax.swing.*;
import java.awt.*;
import com.scc210groupproject.structure.*;

public class SlideManagerPanel extends JPanel {

    private Presentation currentPresentation;
    private JScrollPane slideScroll;
    private JPanel slideView;

    public SlideManagerPanel(int width, int height, Color colour, Presentation presentation) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(0, 1));

        this.currentPresentation = presentation;

        slideView = new JPanel();
        slideScroll = new JScrollPane(slideView);
        slideScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(slideScroll);
        insertIntoDisplay();
    }

    private void insertIntoDisplay()
    {
        for (Slide slide : currentPresentation.getSlides())
        {
            slideView.paintComponents(slide.createPreview());
            slideView.revalidate();
        }
    } 

    public Presentation getCurrentPresentation()
    {
        return this.currentPresentation;
    }

    public void setCurrentPresentation(Presentation currentPresentation)
    {
        this.currentPresentation = currentPresentation;
        insertIntoDisplay();
    }
}
