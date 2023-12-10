package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * FileToolBar
 *
 * @author madukaag
 * */
public class FileToolBar extends JToolBar {
    private JButton newFile, openFile, saveFile, saveAs, export, print, help;

    public FileToolBar() {
        this.setRollover(true);

        newFile = new JButton(GeneralButtons.NEW.getIcon());
        newFile.setText(GeneralButtons.NEW.getTitle());
        newFile.setFocusable(false);
        newFile.setHorizontalTextPosition(SwingConstants.CENTER);
        newFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        openFile = new JButton(GeneralButtons.OPEN.getIcon());
        openFile.setText(GeneralButtons.OPEN.getTitle());
        openFile.setFocusable(false);
        openFile.setHorizontalTextPosition(SwingConstants.CENTER);
        openFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        saveFile = new JButton(GeneralButtons.SAVE.getIcon());
        saveFile.setText(GeneralButtons.SAVE.getTitle());
        saveFile.setFocusable(false);
        saveFile.setHorizontalTextPosition(SwingConstants.CENTER);
        saveFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        saveAs = new JButton(GeneralButtons.SAVE_AS.getIcon());
        saveAs.setText(GeneralButtons.SAVE_AS.getTitle());
        saveAs.setFocusable(false);
        saveAs.setHorizontalTextPosition(SwingConstants.CENTER);
        saveAs.setVerticalTextPosition(SwingConstants.BOTTOM);

        export = new JButton(GeneralButtons.EXPORT.getIcon());
        export.setText(GeneralButtons.EXPORT.getTitle());
        export.setFocusable(false);
        export.setHorizontalTextPosition(SwingConstants.CENTER);
        export.setVerticalTextPosition(SwingConstants.BOTTOM);

        print = new JButton(GeneralButtons.PRINT.getIcon());
        print.setText(GeneralButtons.PRINT.getTitle());
        print.setFocusable(false);
        print.setHorizontalTextPosition(SwingConstants.CENTER);
        print.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(GeneralButtons.HELP.getIcon());
        help.setText(GeneralButtons.HELP.getTitle());
        help.setFocusable(false);
        help.setHorizontalTextPosition(SwingConstants.CENTER);
        help.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(newFile);
        this.add(openFile);
        this.add(saveFile);
        this.add(saveAs);
        this.addSeparator();

        this.add(print);
        this.addSeparator();

        this.add(help);


<<<<<<< HEAD
        saveBtn.setText("SAVE");
        saveBtn.setFocusable(false);
        saveBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        saveBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(saveBtn);

        this.setName("File");
        this.setFloatable(false);
=======
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
    }
}
