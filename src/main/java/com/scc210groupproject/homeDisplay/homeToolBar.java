package com.scc210groupproject.homeDisplay;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class homeToolBar extends JTabbedPane {

    private JButton blankPage;
    public homeToolBar(){

        blankPage = new JButton("New Presentation");
        this.add(blankPage);
    }

}
