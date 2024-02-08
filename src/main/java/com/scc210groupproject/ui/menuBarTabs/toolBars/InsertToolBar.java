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

        addText = makeToolbarButton(GeneralButtons.ADD_TEXT, recentsPanel);

        addImage = makeToolbarButton(GeneralButtons.ADD_IMAGE, recentsPanel);

        addVideo = makeToolbarButton(GeneralButtons.ADD_VIDEO, recentsPanel);

        newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE, recentsPanel);

        addDiagram = makeToolbarButton(GeneralButtons.ADD_DIAG, recentsPanel);

        addShape = makeToolbarButton(GeneralButtons.SHAPES, recentsPanel);

        addLine = makeToolbarButton(GeneralButtons.ADD_LINE, recentsPanel);

        delete = makeToolbarButton(GeneralButtons.DELETE_ELEMENT, recentsPanel);

        addAudio = makeToolbarButton(GeneralButtons.ADD_AUDIO, recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP, recentsPanel);

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
        // if(e.getSource() == this.addText) {
            
        //     //System.out.println("SOMETHING WAS CLICKED!");
        //     //new TextContextMenu(this.uiFrame, 0, 157);
        //     //new DataStructureContextMenu(this.uiFrame, 0, 157);
        //     new ChartContextMenu(this.uiFrame, 0, 157);
        // } else if (e.getSource() == this.addImage) {
        //     //new ImageContextMenu(this.uiFrame, 0, 157);
        //     new CodeContextMenu(this.uiFrame, 0, 157);
        //     //new TableContextMenu(this.uiFrame, 0, 157);
        //     //System.out.println("SOMETHING ELSE WAS CLICKED!");
        // }
    }
}
