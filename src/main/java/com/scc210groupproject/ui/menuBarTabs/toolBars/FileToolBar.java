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
    /**
     * The buttons for this toolbar
     */
    private final JButton newFile, openFile, saveFile, saveAsFile, export, format, print, help;

    /**
     * The constructor for the FileToolBar - Creates the file toolbar and adds all of its buttons to its toolbar
     * @param recentsPanel The recents panel on the home tab
     */
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

    /**
     * Gets the new file button from the file toolbar
     * @return JButton
     */
    public JButton getNewFile() {
        return newFile;
    }

    /**
     * Gets the open file button from the file toolbar
     * @return JButton
     */
    public JButton getOpenFile() {
        return openFile;
    }

    /**
     * Gets the save button from the file toolbar
     * @return JButton
     */
    public JButton getSaveFile() {
        return saveFile;
    }

    /**
     * Gets the save as button from the file toolbar
     * @return JButton
     */
    public JButton getSaveAsFile() {
        return saveAsFile;
    }

    /**
     * Gets the export button from the file toolbar
     * @return JButton
     */
    public JButton getExport() {
        return export;
    }

    /**
     * Gets the format button from the file toolbar
     * @return JButton
     */
    public JButton getFormat() {
        return format;
    }

    /**
     * Gets the print button from the file toolbar
     * @return JButton
     */
    public JButton getPrint() {
        return print;
    }

    /**
     * Gets the help button from the file toolbar
     * @return JButton
     */
    public JButton getHelp() {
        return help;
    }
}
