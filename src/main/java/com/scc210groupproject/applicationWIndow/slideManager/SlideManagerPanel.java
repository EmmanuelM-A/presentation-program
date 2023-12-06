package com.scc210groupproject.applicationWIndow.slideManager;

import javax.swing.*;
import java.awt.*;
import com.scc210groupproject.structure.*;

public class SlideManagerPanel extends JScrollPane {

    private Presentation currentPresentation;

    public Presentation getCurrentPresentation()
    {
        return this.currentPresentation;
    }

    public void setCurrentPresentation(Presentation currentPresentation)
    {
        this.currentPresentation = currentPresentation;
        insertIntoDisplay();
    }

    public SlideManagerPanel(int width, int height, Color colour, Presentation presentation) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.currentPresentation = presentation;
    }

    private void insertIntoDisplay()
    {
        for (Slide slide : currentPresentation.getSlides())
        {
            this.paint(slide.createPreview());
        }
    }  
}
