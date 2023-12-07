package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;
import javax.tools.Tool;

public class FileToolBar extends JToolBar {
    private JButton newFile, openFile, saveFile, saveAs, export, print, help;

    public FileToolBar() {
        this.setRollover(true);

        newFile = new JButton(ToolBarOptions.NEW.getIcon());
        newFile.setText(ToolBarOptions.NEW.getTitle());
        newFile.setFocusable(false);
        newFile.setHorizontalTextPosition(SwingConstants.CENTER);
        newFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        openFile = new JButton(ToolBarOptions.OPEN.getIcon());
        openFile.setText(ToolBarOptions.OPEN.getTitle());
        openFile.setFocusable(false);
        openFile.setHorizontalTextPosition(SwingConstants.CENTER);
        openFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        saveFile = new JButton(ToolBarOptions.SAVE.getIcon());
        saveFile.setText(ToolBarOptions.SAVE.getTitle());
        saveFile.setFocusable(false);
        saveFile.setHorizontalTextPosition(SwingConstants.CENTER);
        saveFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        saveAs = new JButton(ToolBarOptions.SAVE_AS.getIcon());
        saveAs.setText(ToolBarOptions.SAVE_AS.getTitle());
        saveAs.setFocusable(false);
        saveAs.setHorizontalTextPosition(SwingConstants.CENTER);
        saveAs.setVerticalTextPosition(SwingConstants.BOTTOM);

        export = new JButton(ToolBarOptions.EXPORT.getIcon());
        export.setText(ToolBarOptions.EXPORT.getTitle());
        export.setFocusable(false);
        export.setHorizontalTextPosition(SwingConstants.CENTER);
        export.setVerticalTextPosition(SwingConstants.BOTTOM);

        print = new JButton(ToolBarOptions.PRINT.getIcon());
        print.setText(ToolBarOptions.PRINT.getTitle());
        print.setFocusable(false);
        print.setHorizontalTextPosition(SwingConstants.CENTER);
        print.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(ToolBarOptions.HELP.getIcon());
        help.setText(ToolBarOptions.HELP.getTitle());
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
