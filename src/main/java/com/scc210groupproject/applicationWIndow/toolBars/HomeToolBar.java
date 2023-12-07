package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.mainDisplay.MainDisplayPanel;
import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;
import com.scc210groupproject.structure.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeToolBar extends JToolBar implements ActionListener {
    private final JButton newSlideButton, btn2, btn3;

    public HomeToolBar() {
        newSlideButton = new JButton("NEW SLIDE");
        btn2 = new JButton("BUTTON 2");
        btn3 = new JButton("BUTTON 3");

        this.setRollover(true);

        newSlideButton.setFocusable(false);
        newSlideButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlideButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(newSlideButton);
        newSlideButton.addActionListener(this);

        btn2.setFocusable(false);
        btn2.setHorizontalTextPosition(SwingConstants.CENTER);
        btn2.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn2);

        btn3.setFocusable(false);
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn3);

        this.setName("Home");
        this.setFloatable(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == newSlideButton)
        {
            MainDisplayPanel.instance.getCurrentPresentation().newSlide();
        }
    }
}
