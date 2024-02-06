package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.action.NewImageElementAction;
import com.scc210groupproject.ui.contextMenu.*;
import com.scc210groupproject.ui.helper.GeneralButtons;

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
    private JButton addText, addImage, addVideo, newSlide, addDiagram, addShape, addLine, delete, addAudio, help;

    private JFrame uiFrame;
    private ContextMenuPanel contextMenuPanel;

    public InsertToolBar(JFrame frame, ContextMenuPanel c, JPanel recentsPanel) {
        this.contextMenuPanel = c;
        this.uiFrame = frame;
        this.setRollover(true);

        addText = makeToolbarButton(GeneralButtons.ADD_TEXT.getTitle(), GeneralButtons.ADD_TEXT.getIcon(), GeneralButtons.ADD_TEXT.getEvent(), recentsPanel);

        addImage = makeToolbarButton(GeneralButtons.ADD_IMAGE.getTitle(), GeneralButtons.ADD_IMAGE.getIcon(), GeneralButtons.ADD_IMAGE.getEvent(), recentsPanel);

        addVideo = makeToolbarButton(GeneralButtons.ADD_VIDEO.getTitle(), GeneralButtons.ADD_VIDEO.getIcon(), GeneralButtons.ADD_VIDEO.getEvent(), recentsPanel);

        newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE.getTitle(), GeneralButtons.NEW_SLIDE.getIcon(), GeneralButtons.NEW_SLIDE.getEvent(), recentsPanel);

        addDiagram = makeToolbarButton(GeneralButtons.ADD_DIAG.getTitle(), GeneralButtons.ADD_DIAG.getIcon(), GeneralButtons.ADD_DIAG.getEvent(), recentsPanel);

        addShape = makeToolbarButton(GeneralButtons.SHAPES.getTitle(), GeneralButtons.SHAPES.getIcon(), GeneralButtons.SHAPES.getEvent(), recentsPanel);

        addLine = makeToolbarButton(GeneralButtons.ADD_LINE.getTitle(), GeneralButtons.ADD_LINE.getIcon(), GeneralButtons.ADD_LINE.getEvent(), recentsPanel);

        delete = makeToolbarButton(GeneralButtons.DELETE_ELEMENT.getTitle(), GeneralButtons.DELETE_ELEMENT.getIcon(), GeneralButtons.DELETE_ELEMENT.getEvent(), recentsPanel);

        addAudio = makeToolbarButton(GeneralButtons.ADD_AUDIO.getTitle(), GeneralButtons.ADD_AUDIO.getIcon(), GeneralButtons.ADD_AUDIO.getEvent(), recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), GeneralButtons.HELP.getEvent(), recentsPanel);

        this.add(newSlide);

        separator(this);

        addImage.addActionListener(new NewImageElementAction());

        this.add(addText);
        this.add(addImage);
        this.add(addVideo);
        this.add(addDiagram);
        this.add(addAudio);
        this.add(addShape);
        this.add(addLine);

        separator(this);

        this.add(delete);
        
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
            // new ChartContextMenu(this.uiFrame, 0, 157);
        } else if (e.getSource() == this.addImage) {
            //new ImageContextMenu(this.uiFrame, 0, 157);
            new CodeContextMenu(this.uiFrame, 0, 157);
            //new TableContextMenu(this.uiFrame, 0, 157);
            //System.out.println("SOMETHING ELSE WAS CLICKED!");
        }
    }
}
