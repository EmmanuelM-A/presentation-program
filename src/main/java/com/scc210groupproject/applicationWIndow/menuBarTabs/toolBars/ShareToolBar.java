package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * ShareToolBar
 *
 * @author madukaag
 * */
public class ShareToolBar extends JToolBar {
    private JButton export, importFile, share, format, help;

    public ShareToolBar() {
        this.setRollover(true);

        export = new JButton(GeneralButtons.EXPORT.getIcon());
        export.setText(GeneralButtons.EXPORT.getTitle());
        export.setFocusable(false);
        export.setHorizontalTextPosition(SwingConstants.CENTER);
        export.setVerticalTextPosition(SwingConstants.BOTTOM);

        importFile = new JButton(GeneralButtons.IMPORT.getIcon());
        importFile.setText(GeneralButtons.IMPORT.getTitle());
        importFile.setFocusable(false);
        importFile.setHorizontalTextPosition(SwingConstants.CENTER);
        importFile.setVerticalTextPosition(SwingConstants.BOTTOM);

<<<<<<< HEAD:src/main/java/com/scc210groupproject/applicationWIndow/toolBars/ShareToolBar.java
<<<<<<< HEAD
        shareBtn.setText(ToolBarOptions.SHARE.getTitle());
        shareBtn.setFocusable(false);
        shareBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        shareBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(shareBtn);

        this.setName("Share");
        this.setFloatable(false);
=======
        share = new JButton(ToolBarOptions.SHARE.getIcon());
        share.setText(ToolBarOptions.SHARE.getTitle());
=======
        share = new JButton(GeneralButtons.SHARE.getIcon());
        share.setText(GeneralButtons.SHARE.getTitle());
>>>>>>> 04267b6 (Changed the layout of the files inside application window. Also added a slide manager (NOT COMPLETE).):src/main/java/com/scc210groupproject/applicationWIndow/menuBar/toolBars/ShareToolBar.java
        share.setFocusable(false);
        share.setHorizontalTextPosition(SwingConstants.CENTER);
        share.setVerticalTextPosition(SwingConstants.BOTTOM);

        format = new JButton(GeneralButtons.FORMAT.getIcon());
        format.setText(GeneralButtons.FORMAT.getTitle());
        format.setFocusable(false);
        format.setHorizontalTextPosition(SwingConstants.CENTER);
        format.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(GeneralButtons.HELP.getIcon());
        help.setText(GeneralButtons.HELP.getTitle());
        help.setFocusable(false);
        help.setHorizontalTextPosition(SwingConstants.CENTER);
        help.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(export);
        this.add(importFile);
        this.add(share);
        this.addSeparator();

        this.add(format);
        this.addSeparator();

        this.add(help);
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
    }
}
