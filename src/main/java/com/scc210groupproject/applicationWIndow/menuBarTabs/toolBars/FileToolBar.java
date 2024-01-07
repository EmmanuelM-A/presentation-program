package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends ToolBar and contains all the buttons that will be displayed on the
 * FileToolBar
 *
 * @author madukaag
 * */
public class FileToolBar extends ToolBar {
    private JButton newFile, openFile, saveFile, export, format, print, help;

    public FileToolBar() {
        this.setRollover(true);

        newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon());

        openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon());

        saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon());

        export = makeToolbarButton(GeneralButtons.EXPORT.getTitle(), GeneralButtons.EXPORT.getIcon());

        format = makeToolbarButton(GeneralButtons.FORMAT.getTitle(), GeneralButtons.FORMAT.getIcon());

        print = makeToolbarButton(GeneralButtons.PRINT.getTitle(), GeneralButtons.PRINT.getIcon());

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon());

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
