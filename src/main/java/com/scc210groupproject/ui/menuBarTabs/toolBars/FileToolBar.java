package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.action.OpenAction;
import com.scc210groupproject.action.SaveAction;
import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * FileToolBar
 *
 * @author madukaag
 * */
public class FileToolBar extends ToolBar {
    private JButton newFile, openFile, saveFile, saveFileAs, export, format, print, help;

    public FileToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon(), GeneralButtons.NEW.getEvent(), recentsPanel);

        openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon(), GeneralButtons.OPEN.getEvent(), recentsPanel);

        saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon(), GeneralButtons.SAVE.getEvent(), recentsPanel);

        saveFileAs = makeToolbarButton(GeneralButtons.SAVE_AS.getTitle(), GeneralButtons.SAVE_AS.getIcon(), GeneralButtons.SAVE_AS.getEvent(), recentsPanel);

        export = makeToolbarButton(GeneralButtons.EXPORT.getTitle(), GeneralButtons.EXPORT.getIcon(), GeneralButtons.EXPORT.getEvent(), recentsPanel);

        format = makeToolbarButton(GeneralButtons.FORMAT.getTitle(), GeneralButtons.FORMAT.getIcon(), GeneralButtons.FORMAT.getEvent(), recentsPanel);

        print = makeToolbarButton(GeneralButtons.PRINT.getTitle(), GeneralButtons.PRINT.getIcon(), GeneralButtons.PRINT.getEvent(), recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), GeneralButtons.HELP.getEvent(), recentsPanel);

        this.add(newFile);
        this.add(openFile);
        this.add(saveFile);
        this.add(saveFileAs);

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
