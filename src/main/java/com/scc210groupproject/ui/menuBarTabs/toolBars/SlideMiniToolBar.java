package com.scc210groupproject.ui.menuBarTabs.toolBars;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;

import com.scc210groupproject.ui.helper.GeneralButtons;

public class SlideMiniToolBar extends ToolBar {

    private JButton newSlide, insertSlideBefore, insertSlideAfter, deleteSlide, presentSlide;

    public SlideMiniToolBar() {
        this.setRollover(true);
        this.setName("Slide Mini Toolbar");
        this.setFloatable(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(400, 80));

        newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE, null);

        insertSlideBefore = makeToolbarButton(null, null);

        insertSlideAfter = makeToolbarButton(null, null);

        deleteSlide = makeToolbarButton(null, null);

        presentSlide = makeToolbarButton(null, null);

        this.add(newSlide);
        this.add(insertSlideBefore);
        this.add(insertSlideAfter);
        this.add(deleteSlide);
        this.add(presentSlide);

    }

    /*private JToolBar miniTextToolbar() {
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();

        JToolBar bar = new JToolBar();
        bar.setLayout(layout);
        bar.setPreferredSize(new Dimension(300, 60));

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // First Row
        gbc.gridwidth = 3;
        makeButton("Fonts", bar, layout, gbc);
        gbc.gridwidth = 1;
        makeButton("Sizes", bar, layout, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("Move", bar, layout, gbc);

        // Second Row
        gbc.gridwidth = 1;
        makeButton("Bold", bar, layout, gbc);
        makeButton("Italic", bar, layout, gbc);
        makeButton("U...", bar, layout, gbc);
        makeButton("Colour", bar, layout, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        makeButton("Bucket", bar, layout, gbc);

        return bar;
    }*/
}
