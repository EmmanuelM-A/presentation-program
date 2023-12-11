package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * ViewToolBar
 *
 * @author madukaag
 * */
public class ViewToolBar extends JToolBar {
    private JButton present, presentAt, help;
    //private JTextField presentAt;
    private JScrollPane animations, transistions;

    public ViewToolBar() {
        this.setRollover(true);

        present = new JButton(GeneralButtons.PRESENT.getIcon());
        present.setText(GeneralButtons.PRESENT.getTitle());
        present.setFocusable(false);
        present.setHorizontalTextPosition(SwingConstants.CENTER);
        present.setVerticalTextPosition(SwingConstants.BOTTOM);

        presentAt = new JButton(GeneralButtons.PRESENT_AT.getIcon());
        presentAt.setText(GeneralButtons.PRESENT_AT.getTitle());
        presentAt.setFocusable(false);
        presentAt.setHorizontalTextPosition(SwingConstants.CENTER);
        presentAt.setVerticalTextPosition(SwingConstants.BOTTOM);

        animations = new JScrollPane();
        animations.setSize(50, 100);
        animations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        transistions = new JScrollPane();
        transistions.setSize(50, 100);
        transistions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        help = new JButton(GeneralButtons.HELP.getIcon());
        help.setText(GeneralButtons.HELP.getTitle());
        help.setFocusable(false);
        help.setHorizontalTextPosition(SwingConstants.CENTER);
        help.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(present);
        this.add(presentAt);
        this.addSeparator();

        this.add(animations);
        this.addSeparator();

        this.add(transistions);
        this.addSeparator();

        this.add(help);

        this.setName("View");
        this.setFloatable(false);
    }
}
