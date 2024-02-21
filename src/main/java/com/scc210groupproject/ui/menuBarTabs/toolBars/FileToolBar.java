package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * FileToolBar
 *
 * @author madukaag
 * */
public class FileToolBar extends ToolBar {
    private JButton newFile, openFile, saveFile, saveAsFile, export, format, print, help;

    public FileToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        newFile = makeToolbarButton(GeneralButtons.NEW, recentsPanel);

        openFile = makeToolbarButton(GeneralButtons.OPEN, recentsPanel);

        saveFile = makeToolbarButton(GeneralButtons.SAVE, recentsPanel);

        saveAsFile = makeToolbarButton(GeneralButtons.SAVE_AS, recentsPanel);

        export = makeToolbarButton(GeneralButtons.EXPORT, recentsPanel);

        format = makeToolbarButton(GeneralButtons.FORMAT, recentsPanel);

        print = makeToolbarButton(GeneralButtons.PRINT, recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP, recentsPanel);

        this.add(newFile);
        this.add(openFile);
        this.add(saveFile);
        this.add(saveAsFile);

        separator(this);

        this.add(export);
        this.add(format);
        this.add(print);

        separator(this);

        this.add(help);

        this.setName("File");
        this.setFloatable(false);
    }
}
