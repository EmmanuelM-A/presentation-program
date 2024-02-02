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

    private JPanel animationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel transistionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public ViewToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        present = makeToolbarButton(GeneralButtons.PRESENT.getTitle(), GeneralButtons.PRESENT.getIcon(), GeneralButtons.PRESENT.getEvent(), recentsPanel);

        presentAt = makeToolbarButton(GeneralButtons.PRESENT_AT.getTitle(), GeneralButtons.PRESENT_AT.getIcon(), GeneralButtons.PRESENT_AT.getEvent(), recentsPanel);

        animationsPanel.setBackground(Color.WHITE);
        animations = new JScrollPane(animationsPanel);
        animations.setMaximumSize(new Dimension(500, 90));
        animations.setToolTipText("Animations");
        animations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        transistionsPanel.setBackground(Color.WHITE);
        transistions = new JScrollPane(transistionsPanel);
        transistions.setMaximumSize(new Dimension(500, 90));
        transistions.setToolTipText("Transitions");
        transistions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), GeneralButtons.HELP.getEvent(), recentsPanel);

        addButtonsToAnimations("Animation 1");
        addButtonsToAnimations("Animation 2");
        addButtonsToAnimations("Animation 3");
        addButtonsToAnimations("Animation 4");
        addButtonsToAnimations("Animation 5");
        addButtonsToAnimations("Animation 6");
        addButtonsToAnimations("Animation 7");

        addButtonsToTransition("Transition 1");
        addButtonsToTransition("Transition 2");
        addButtonsToTransition("Transition 3");
        addButtonsToTransition("Transition 4");
        addButtonsToTransition("Transition 5");
        addButtonsToTransition("Transition 6");
        addButtonsToTransition("Transition 7");


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

    private void addButtonsToTransition(String title) {
        JButton button = new JButton("<html> " + title + "</html>");
        button.setPreferredSize(new Dimension(76, 76));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        transistionsPanel.add(button);
    }

    private void addButtonsToAnimations(String title) {
        JButton button = new JButton("<html> " + title + "</html>");
        button.setPreferredSize(new Dimension(76, 76));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        animationsPanel.add(button);
    }
}
