package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.contextMenu.*;
import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;
import com.scc210groupproject.structure.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * InsertToolBar
 *
 * @author madukaag
 * */
public class InsertToolBar extends ToolBar implements ActionListener {
    private JButton addText, addImage, addVideo, newSlide, addDiagram, addShape, addAudio, help;

    private JFrame uiFrame;
    private ContextMenuPanel contextMenuPanel;

    public InsertToolBar(JFrame frame, ContextMenuPanel c) {
        this.contextMenuPanel = c;
        this.uiFrame = frame;
        this.setRollover(true);

        addText = makeToolbarButton(GeneralButtons.ADD_TEXT.getTitle(), GeneralButtons.ADD_TEXT.getIcon());

        addImage = makeToolbarButton(GeneralButtons.ADD_IMAGE.getTitle(), GeneralButtons.ADD_IMAGE.getIcon());

        addVideo = makeToolbarButton(GeneralButtons.ADD_VIDEO.getTitle(), GeneralButtons.ADD_VIDEO.getIcon());

        newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE.getTitle(), GeneralButtons.NEW_SLIDE.getIcon());

        addDiagram = makeToolbarButton(GeneralButtons.ADD_DIAG.getTitle(), GeneralButtons.ADD_DIAG.getIcon());

        addShape = makeToolbarButton(GeneralButtons.SHAPES.getTitle(), GeneralButtons.SHAPES.getIcon());

        addAudio = makeToolbarButton(GeneralButtons.ADD_AUDIO.getTitle(), GeneralButtons.ADD_AUDIO.getIcon());

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon());

        this.add(newSlide);

        separator(this);

        this.add(addText);
        this.add(addImage);
        this.add(addVideo);
        this.add(addDiagram);
        this.add(addAudio);
        this.add(addShape);

        separator(this);

        this.add(help);

        this.setName("Insert");
        this.setFloatable(false);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.addText) {
            //System.out.println("SOMETHING WAS CLICKED!");
            //new TextContextMenu(this.uiFrame, 0, 157);
            //new DataStructureContextMenu(this.uiFrame, 0, 157);
            new ChartContextMenu(this.uiFrame, 0, 157);
        } else if (e.getSource() == this.addImage) {
            //new ImageContextMenu(this.uiFrame, 0, 157);
            new CodeContextMenu(this.uiFrame, 0, 157);
            //new TableContextMenu(this.uiFrame, 0, 157);
            //System.out.println("SOMETHING ELSE WAS CLICKED!");
        }
    }
}
