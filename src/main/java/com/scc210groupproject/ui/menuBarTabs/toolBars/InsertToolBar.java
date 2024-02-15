package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.ui.contextMenu.*;
import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * InsertToolBar
 *
 * @author madukaag
 * */
public class InsertToolBar extends ToolBar {
    private JButton addText, addImage, addVideo, newSlide, addDiagram, addChart, addLine, delete, addAudio, help;
    public JButton addShape;

    public InsertToolBar(JFrame frame, ContextMenuPanel c, JPanel recentsPanel) {
        this.setRollover(true);

        addText = makeToolbarButton(GeneralButtons.ADD_TEXT, recentsPanel);

        addImage = makeToolbarButton(GeneralButtons.ADD_IMAGE, recentsPanel);

        addVideo = makeToolbarButton(GeneralButtons.ADD_VIDEO, recentsPanel);

        newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE, recentsPanel);

        addDiagram = makeToolbarButton(GeneralButtons.ADD_DIAG, recentsPanel);

        addChart = makeToolbarButton(GeneralButtons.ADD_CHART, recentsPanel);

        addShape = makeToolbarButton(GeneralButtons.SHAPES, recentsPanel);

        addLine = makeToolbarButton(GeneralButtons.ADD_LINE, recentsPanel);

        addAudio = makeToolbarButton(GeneralButtons.ADD_AUDIO, recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP, recentsPanel);

        this.add(newSlide);

        separator(this);

        this.add(addText);
        this.add(addImage);
        this.add(addVideo);
        this.add(addDiagram);
        this.add(addChart);
        this.add(addAudio);
        this.add(addShape);
        this.add(addLine);

        separator(this);

        this.add(help);

        this.setName("Insert");
        this.setFloatable(false);

    }
}
