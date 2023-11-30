package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class HomeToolBar extends JToolBar {
    private final JButton btn1, btn2, btn3;

    public HomeToolBar() {
        btn1 = new JButton("BUTTON 1");
        btn2 = new JButton("BUTTON 2");
        btn3 = new JButton("BUTTON 3");

        this.setRollover(true);

        btn1.setText("Btn1");
        btn1.setFocusable(false);
        btn1.setHorizontalTextPosition(SwingConstants.CENTER);
        btn1.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn1);

        btn2.setText("Btn2");
        btn2.setFocusable(false);
        btn2.setHorizontalTextPosition(SwingConstants.CENTER);
        btn2.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn2);

        btn3.setText("Btn3");
        btn3.setFocusable(false);
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn3);
    }
}
