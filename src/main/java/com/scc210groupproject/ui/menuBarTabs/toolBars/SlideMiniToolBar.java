package com.scc210groupproject.ui.menuBarTabs.toolBars;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;

import com.scc210groupproject.ui.helper.GeneralButtons;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the mini toolbar on right click
 * of the slides in the slide viewer 
 *
 * @author madukaag
 */
public class SlideMiniToolBar extends ToolBar {
    /**
     * The buttons for this toolbar
     */
    private final JButton newSlide, insertSlideBefore, insertSlideAfter, deleteSlide, presentSlide;

    /**
     * The constructor for the SlideMiniToolBar - Creates the slides mini toolbar and adds all of its buttons to its toolbar
     */
    public SlideMiniToolBar() {
        this.setRollover(true);
        this.setName("Slide Mini Toolbar");
        this.setFloatable(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setPreferredSize(new Dimension(390, 74));

        newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE, null);

        insertSlideBefore = makeToolbarButton(GeneralButtons.INSERT_SLIDE_BEFORE, null);

        insertSlideAfter = makeToolbarButton(GeneralButtons.INSERT_SLIDE_AFTER, null);
        insertSlideAfter.setPreferredSize(new Dimension(82, 74));

        deleteSlide = makeToolbarButton(GeneralButtons.DELETE_SLIDE, null);

        presentSlide = makeToolbarButton(GeneralButtons.PRESENT_AT, null);

        this.add(newSlide);
        this.add(insertSlideBefore);
        this.add(insertSlideAfter);
        this.add(deleteSlide);
        this.add(presentSlide);
    }

    public JButton getNewSlide() {
        return this.newSlide;
    }

}
