package com.scc210groupproject.ui.presentations;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.scc210groupproject.ui.SlideImage;
import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class pm {
    /**
     * The list of the slide buffer images 
     */
    private LinkedList<SlideImage> slidesToPresent;

    /**
     * The index of the current image being displayed
     */
    private int currentImageIndex;

    private JFrame frame;

    private JPanel presentationDisplay;

    public pm(JFrame frame, JPanel presentationDisplay, int startIndex) {
        this.presentationDisplay = presentationDisplay;

        this.slidesToPresent = populteSlidesToPresent(SlideManager.slideManager.getSlideImages());

        this.currentImageIndex = startIndex;
    }

    public LinkedList<SlideImage> populteSlidesToPresent(LinkedList<SlideImage> list) {
        LinkedList<SlideImage> slideImages = new LinkedList<>();

        for(int i = 0; i < list.size(); i++) {
            SlideImage slideImage = new SlideImage(list.get(i).getSlide(), presentationDisplay);

            slideImages.add(slideImage);
        }

        return slideImages;
    }
}
