package com.scc210groupproject.ui.menuBarTabs.toolBars;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;

import com.scc210groupproject.ui.helper.GeneralButtons;

public class ElementMiniToolBar extends ToolBar {
    private JButton addAnimation, deleteAnimation, viewAnimation;

    public ElementMiniToolBar() {
        this.setRollover(true);
        this.setName("Element Mini Toolbar");
        this.setFloatable(false);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setPreferredSize(new Dimension(226, 74));

        // Ignore icons for now
        addAnimation = makeToolbarButton(GeneralButtons.ADD_DIAG, null);

        deleteAnimation = makeToolbarButton(GeneralButtons.ADD_IMAGE, null);

        viewAnimation = makeToolbarButton(GeneralButtons.ADD_LINE, null);

        this.add(addAnimation);
        this.add(deleteAnimation);
        this.add(viewAnimation);
    }
    
}
