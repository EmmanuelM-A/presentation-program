package com.scc210groupproject.applicationWIndow.slideManager;

import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
import com.scc210groupproject.structure.*;

public class SlideManagerPanel extends JPanel {

    private Presentation currentPresentation;
    private JScrollPane slideScroll;
    private JLabel slideView;

    public SlideManagerPanel(int width, int height, Color colour, Presentation presentation) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(0, 1));

        this.currentPresentation = presentation;

        slideView = new JLabel();
        slideScroll = new JScrollPane(slideView);
        slideScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(slideScroll);
        insertIntoDisplay();
    }

    private void insertIntoDisplay()
    {
        for (Slide slide : currentPresentation.getSlides())
        {
            slideView.setIcon(new ImageIcon(slide.createPreview(slide.asComp().getSize())));
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
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class SlideManagerPanel {
    private JPanel slide = new JPanel();
    private LinkedList<JPanel> slides;
    private int currentSlideIndex;
    private JPanel mainDisply;
    public SlideManagerPanel(final JFrame frame, JPanel mainDsiplay) {
        slides = new LinkedList<>();
        currentSlideIndex = 0;
        this.mainDisply = mainDsiplay;

        JPanel presentationSliderPanel = new JPanel(new BorderLayout());
        presentationSliderPanel.setPreferredSize(new Dimension(1000, 180));
        presentationSliderPanel.setBackground(Color.YELLOW);

        JButton prevSlide = new JButton("<");
        prevSlide.setPreferredSize(new Dimension(50, 160));
        prevSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Previous Slide!");
            }
        });

        JButton nextSlide = new JButton(">");
        nextSlide.setPreferredSize(new Dimension(50, 160));
        nextSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Next Slide!");
            }
        });

        JPanel mainView = new JPanel();
        mainView.setPreferredSize(new Dimension(1000, 160));
        mainView.setBackground(Color.green);

        JPanel bottom = getBottom();

        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(mainView, BorderLayout.CENTER);
        presentationSliderPanel.add(bottom, BorderLayout.SOUTH);

        frame.add(presentationSliderPanel, BorderLayout.SOUTH);
>>>>>>> 04267b6 (Changed the layout of the files inside application window. Also added a slide manager (NOT COMPLETE).)
    }

    private JPanel getBottom() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setPreferredSize(new Dimension(1000, 35));
        bottom.setBackground(Color.blue);

        JButton addNewSlide = new JButton("New Slide");
        addNewSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("New Slide!");
                addSlide();
            }
        });
        bottom.add(addNewSlide);
        return bottom;
    }

    /**
     * Displays the previous slide in the presentation slider given that there is at least one slide
     * */
    private void showPrevSlide() {
        if(currentSlideIndex > 0) {
            currentSlideIndex--;
            showSlide(slides.get(currentSlideIndex));
        }
    }

    /**
     * Displays the next slide in the presentation slider given that it has not reached the last slide
     * */
    private void showNextSlide() {
        if(currentSlideIndex < (slides.size() - 1)) {
            currentSlideIndex++;
            showSlide(slides.get(currentSlideIndex));
        }
    }

    /**
     * Displays the selected slide on the main display
     * */
    private void showSlide(JPanel slide) {
        mainDisply.add(slide);
    }

    /**
     * Creates a new slide and assigns it a slide number. Then adds the slide onto the end of the existing slides.
     * */
    private void addSlide() {
        JPanel newSlide = new JPanel(); // Can be switched with actual slide later
        newSlide.setBackground(Color.LIGHT_GRAY);
        JLabel slideNo = new JLabel("Slide " + (slides.size() + 1));
        slideNo.setFont(new Font("Arial", Font.BOLD, 24));
        newSlide.add(slideNo);

        slides.add(newSlide);
        showSlide(newSlide);
    }

}
