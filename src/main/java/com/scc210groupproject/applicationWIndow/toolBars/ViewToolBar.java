package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class ViewToolBar extends JToolBar {
    private JButton present, presentAt, help;
    //private JTextField presentAt;
    private JScrollPane animations, transistions;

    public ViewToolBar() {
        this.setRollover(true);

        present = new JButton(ToolBarOptions.PRESENT.getIcon());
        present.setText(ToolBarOptions.PRESENT.getTitle());
        present.setFocusable(false);
        present.setHorizontalTextPosition(SwingConstants.CENTER);
        present.setVerticalTextPosition(SwingConstants.BOTTOM);

        presentAt = new JButton(ToolBarOptions.PRESENT_AT.getIcon());
        presentAt.setText(ToolBarOptions.PRESENT_AT.getTitle());
        presentAt.setFocusable(false);
        presentAt.setHorizontalTextPosition(SwingConstants.CENTER);
        presentAt.setVerticalTextPosition(SwingConstants.BOTTOM);

        animations = new JScrollPane();
        animations.setSize(50, 100);
        animations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        transistions = new JScrollPane();
        transistions.setSize(50, 100);
        transistions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        help = new JButton(ToolBarOptions.HELP.getIcon());
        help.setText(ToolBarOptions.HELP.getTitle());
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

<<<<<<< HEAD
        btn3.setText("Btn3");
        btn3.setFocusable(false);
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn3);

        this.setName("View");
        this.setFloatable(false);
=======
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
    }
}
