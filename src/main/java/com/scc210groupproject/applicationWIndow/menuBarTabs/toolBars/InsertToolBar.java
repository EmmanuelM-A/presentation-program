package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.contextMenu.TextContextMenu;
import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * InsertToolBar
 *
 * @author madukaag
 * */
public class InsertToolBar extends JToolBar implements ActionListener {
    private JButton addText, addImage, addVideo, newSlide, addDiagram, addShape, addAudio, help;

    private JFrame uiFrame;

    public InsertToolBar(JFrame frame) {
        this.uiFrame = frame;
        this.setRollover(true);

        addText = new JButton(GeneralButtons.ADD_TEXT.getIcon());
        addText.setText(GeneralButtons.ADD_TEXT.getTitle());
        addText.setFocusable(false);
        addText.setHorizontalTextPosition(SwingConstants.CENTER);
        addText.setVerticalTextPosition(SwingConstants.BOTTOM);
        addText.addActionListener(this);

        addImage = new JButton(GeneralButtons.ADD_IMAGE.getIcon());
        addImage.setText(GeneralButtons.ADD_IMAGE.getTitle());
        addImage.setFocusable(false);
        addImage.setHorizontalTextPosition(SwingConstants.CENTER);
        addImage.setVerticalTextPosition(SwingConstants.BOTTOM);

        addVideo = new JButton(GeneralButtons.ADD_VIDEO.getIcon());
        addVideo.setText(GeneralButtons.ADD_VIDEO.getTitle());
        addVideo.setFocusable(false);
        addVideo.setHorizontalTextPosition(SwingConstants.CENTER);
        addVideo.setVerticalTextPosition(SwingConstants.BOTTOM);

        newSlide = new JButton(GeneralButtons.NEW_SLIDE.getIcon());
        newSlide.setText(GeneralButtons.NEW_SLIDE.getTitle());
        newSlide.setFocusable(false);
        newSlide.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlide.setVerticalTextPosition(SwingConstants.BOTTOM);

        addDiagram = new JButton(GeneralButtons.ADD_DIAG.getIcon());
        addDiagram.setText(GeneralButtons.ADD_DIAG.getTitle());
        addDiagram.setFocusable(false);
        addDiagram.setHorizontalTextPosition(SwingConstants.CENTER);
        addDiagram.setVerticalTextPosition(SwingConstants.BOTTOM);

        addShape = new JButton(GeneralButtons.SHAPES.getIcon());
        addShape.setText(GeneralButtons.SHAPES.getTitle());
        addShape.setFocusable(false);
        addShape.setHorizontalTextPosition(SwingConstants.CENTER);
        addShape.setVerticalTextPosition(SwingConstants.BOTTOM);

        addAudio = new JButton(GeneralButtons.ADD_AUDIO.getIcon());
        addAudio.setText(GeneralButtons.ADD_AUDIO.getTitle());
        addAudio.setFocusable(false);
        addAudio.setHorizontalTextPosition(SwingConstants.CENTER);
        addAudio.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(GeneralButtons.HELP.getIcon());
        help.setText(GeneralButtons.HELP.getTitle());
        help.setFocusable(false);
        help.setHorizontalTextPosition(SwingConstants.CENTER);
        help.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(newSlide);
        this.addSeparator();

        this.add(addText);
        this.add(addImage);
        this.add(addVideo);
        this.add(addDiagram);
        this.add(addAudio);
        this.add(addShape);
        this.addSeparator();

        this.add(help);

        this.setName("Insert");
        this.setFloatable(false);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addText) {
            System.out.println("ADD_TEXT CLICKED!");
            new TextContextMenu(this.uiFrame, 100, 100);
        }
    }
}
