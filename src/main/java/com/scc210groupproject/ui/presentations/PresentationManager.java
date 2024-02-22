package com.scc210groupproject.ui.presentations;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.SlideImage;
import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class PresentationManager {
    /**
     * The list of the slide buffer images 
     */
    private LinkedList<JLabel> slidesToPresent;

    /**
     * The index of the current image being displayed
     */
    private int currentImageIndex;

    private JFrame frame;

    private JPanel presentationDisplay;

    public PresentationManager(JFrame frame, JPanel presentationDisplay, int startIndex) {
        this.presentationDisplay = presentationDisplay;

        this.slidesToPresent = populteSlidesToPresent(SlideManager.slideManager.getSlideImages());

        this.currentImageIndex = startIndex;

        this.presentationDisplay.add(this.slidesToPresent.get(startIndex), BorderLayout.CENTER);

        this.presentationDisplay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextSlide();
            }
        });
    }

    public LinkedList<JLabel> populteSlidesToPresent(LinkedList<SlideImage> list) {
        LinkedList<JLabel> images = new LinkedList<>();

        for(int i = 0; i < list.size(); i++) {
            ImageIcon image = new ImageIcon(list.get(i).getBufferedSlideImage());

            ImageIcon slideToPresent = GeneralButtons.resizeIcon(
                image, 
                presentationDisplay.getSize().width, 
                presentationDisplay.getSize().height
            );

            images.add(new JLabel(slideToPresent));
        }

        return images;
    }

    public void nextSlide() {
        if(currentImageIndex <= (slidesToPresent.size() - 1)) {
            this.presentationDisplay.remove(this.slidesToPresent.get(currentImageIndex));

            currentImageIndex++;

            this.presentationDisplay.add(this.slidesToPresent.get(currentImageIndex), BorderLayout.CENTER);

            this.presentationDisplay.revalidate();

            this.presentationDisplay.repaint();
        } else {
            this.frame.dispose();
        }
    }
}
