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
    private JButton newFile, openFile, saveFile, export, format, print, help;

    public FileToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon(), recentsPanel);

        openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon(), recentsPanel);
        openFile.addActionListener(new OpenAction());

        saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon(), recentsPanel);
        saveFile.addActionListener(new SaveAction());

        export = makeToolbarButton(GeneralButtons.EXPORT.getTitle(), GeneralButtons.EXPORT.getIcon(), recentsPanel);

        format = makeToolbarButton(GeneralButtons.FORMAT.getTitle(), GeneralButtons.FORMAT.getIcon(), recentsPanel);

        print = makeToolbarButton(GeneralButtons.PRINT.getTitle(), GeneralButtons.PRINT.getIcon(), recentsPanel);

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), recentsPanel);

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
