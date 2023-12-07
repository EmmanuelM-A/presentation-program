package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class InsertToolBar extends JToolBar {
    private JButton addText, addImage, addVideo, newSlide, addDiagram, addShape, addAudio, help;

    public InsertToolBar() {
        this.setRollover(true);

        addText = new JButton(ToolBarOptions.ADD_TEXT.getIcon());
        addText.setText(ToolBarOptions.ADD_TEXT.getTitle());
        addText.setFocusable(false);
        addText.setHorizontalTextPosition(SwingConstants.CENTER);
        addText.setVerticalTextPosition(SwingConstants.BOTTOM);

        addImage = new JButton(ToolBarOptions.ADD_IMAGE.getIcon());
        addImage.setText(ToolBarOptions.ADD_IMAGE.getTitle());
        addImage.setFocusable(false);
        addImage.setHorizontalTextPosition(SwingConstants.CENTER);
        addImage.setVerticalTextPosition(SwingConstants.BOTTOM);

        addVideo = new JButton(ToolBarOptions.ADD_VIDEO.getIcon());
        addVideo.setText(ToolBarOptions.ADD_VIDEO.getTitle());
        addVideo.setFocusable(false);
        addVideo.setHorizontalTextPosition(SwingConstants.CENTER);
        addVideo.setVerticalTextPosition(SwingConstants.BOTTOM);

        newSlide = new JButton(ToolBarOptions.NEW_SLIDE.getIcon());
        newSlide.setText(ToolBarOptions.NEW_SLIDE.getTitle());
        newSlide.setFocusable(false);
        newSlide.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlide.setVerticalTextPosition(SwingConstants.BOTTOM);

        addDiagram = new JButton(ToolBarOptions.ADD_DIAG.getIcon());
        addDiagram.setText(ToolBarOptions.ADD_DIAG.getTitle());
        addDiagram.setFocusable(false);
        addDiagram.setHorizontalTextPosition(SwingConstants.CENTER);
        addDiagram.setVerticalTextPosition(SwingConstants.BOTTOM);

        addShape = new JButton(ToolBarOptions.SHAPES.getIcon());
        addShape.setText(ToolBarOptions.SHAPES.getTitle());
        addShape.setFocusable(false);
        addShape.setHorizontalTextPosition(SwingConstants.CENTER);
        addShape.setVerticalTextPosition(SwingConstants.BOTTOM);

        addAudio = new JButton(ToolBarOptions.ADD_AUDIO.getIcon());
        addAudio.setText(ToolBarOptions.ADD_AUDIO.getTitle());
        addAudio.setFocusable(false);
        addAudio.setHorizontalTextPosition(SwingConstants.CENTER);
        addAudio.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(ToolBarOptions.HELP.getIcon());
        help.setText(ToolBarOptions.HELP.getTitle());
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

<<<<<<< HEAD
        addVideoBtn.setText(ToolBarOptions.ADD_VIDEO.getTitle());
        addVideoBtn.setFocusable(false);
        addVideoBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        addVideoBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(addVideoBtn);

        this.setName("Insert");
        this.setFloatable(false);
=======
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
    }
}
