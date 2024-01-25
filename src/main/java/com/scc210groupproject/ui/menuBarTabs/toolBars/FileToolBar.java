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
    private JButton newFile, openFile, saveFile, export, format, print, help;

    public FileToolBar() {
        this.setRollover(true);

        newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon(), HomeToolBar.getRecentPanel());

        openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon(), HomeToolBar.getRecentPanel());

        saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon(), HomeToolBar.getRecentPanel());

        export = makeToolbarButton(GeneralButtons.EXPORT.getTitle(), GeneralButtons.EXPORT.getIcon(), HomeToolBar.getRecentPanel());

        format = makeToolbarButton(GeneralButtons.FORMAT.getTitle(), GeneralButtons.FORMAT.getIcon(), HomeToolBar.getRecentPanel());

        print = makeToolbarButton(GeneralButtons.PRINT.getTitle(), GeneralButtons.PRINT.getIcon(), HomeToolBar.getRecentPanel());

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), HomeToolBar.getRecentPanel());

        this.add(newFile);
        this.add(openFile);
        this.add(saveFile);

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
