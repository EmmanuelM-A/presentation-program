package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * ViewToolBar
 *
 * @author madukaag
 * */
public class ViewToolBar extends ToolBar {
    private JButton present, presentAt, help;
    //private JTextField presentAt;
    private JScrollPane animations, transistions;

    private JPanel animationsPanel = new JPanel();
    private JPanel transistionsPanel = new JPanel();

    public ViewToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        present = makeToolbarButton(GeneralButtons.PRESENT.getTitle(), GeneralButtons.PRESENT.getIcon(), GeneralButtons.PRESENT.getEvent(), recentsPanel);

        presentAt = makeToolbarButton(GeneralButtons.PRESENT_AT.getTitle(), GeneralButtons.PRESENT_AT.getIcon(), GeneralButtons.PRESENT_AT.getEvent(), recentsPanel);

        animationsPanel.setBackground(Color.BLUE);
        animations = new JScrollPane(animationsPanel);
        animations.setSize(50, 100);
        animations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        transistionsPanel.setBackground(Color.ORANGE);
        transistions = new JScrollPane(transistionsPanel);
        transistions.setSize(50, 100);
        transistions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), null, recentsPanel);

        this.add(present);
        this.add(presentAt);

        separator(this);

        this.add(animations);

        separator(this);

        this.add(transistions);

        separator(this);

        this.add(help);

        this.setName("View");
        this.setFloatable(false);
    }
}
