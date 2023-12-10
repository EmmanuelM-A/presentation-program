package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * AboutToolBar
 *
 * @author madukaag
 * */
public class AboutToolBar extends JToolBar {
    private JButton license, shortcuts, help;
    public AboutToolBar() {
        this.setRollover(true);

        license = new JButton(GeneralButtons.LICENSE.getIcon());
        license.setText(GeneralButtons.LICENSE.getTitle());
        license.setFocusable(false);
        license.setHorizontalTextPosition(SwingConstants.CENTER);
        license.setVerticalTextPosition(SwingConstants.BOTTOM);

        shortcuts = new JButton(GeneralButtons.SHORTCUTS.getIcon());
        shortcuts.setText(GeneralButtons.SHORTCUTS.getTitle());
        shortcuts.setFocusable(false);
        shortcuts.setHorizontalTextPosition(SwingConstants.CENTER);
        shortcuts.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(GeneralButtons.HELP.getIcon());
        help.setText(GeneralButtons.HELP.getTitle());
        help.setFocusable(false);
        help.setHorizontalTextPosition(SwingConstants.CENTER);
        help.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(license);
        this.addSeparator();

        this.add(shortcuts);
        this.addSeparator();

        this.add(help);

<<<<<<< HEAD
        btn3.setText("Btn3");
        btn3.setFocusable(false);
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn3);
    
        this.setName("About");
        this.setFloatable(false);
=======
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
    }
}
