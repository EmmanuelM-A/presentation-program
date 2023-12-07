package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class AboutToolBar extends JToolBar {
    private JButton license, shortcuts, help;
    public AboutToolBar() {
        this.setRollover(true);

        license = new JButton(ToolBarOptions.LICENSE.getIcon());
        license.setText(ToolBarOptions.LICENSE.getTitle());
        license.setFocusable(false);
        license.setHorizontalTextPosition(SwingConstants.CENTER);
        license.setVerticalTextPosition(SwingConstants.BOTTOM);

        shortcuts = new JButton(ToolBarOptions.SHORTCUTS.getIcon());
        shortcuts.setText(ToolBarOptions.SHORTCUTS.getTitle());
        shortcuts.setFocusable(false);
        shortcuts.setHorizontalTextPosition(SwingConstants.CENTER);
        shortcuts.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(ToolBarOptions.HELP.getIcon());
        help.setText(ToolBarOptions.HELP.getTitle());
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
