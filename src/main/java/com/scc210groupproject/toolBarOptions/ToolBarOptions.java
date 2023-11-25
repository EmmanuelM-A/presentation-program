package com.scc210groupproject.toolBarOptions;

import javax.swing.*;

public enum ToolBarOptions {
    NEW("NEW_FILE", "new-document.png"),
    OPEN("OPEN_FILE", "folder.png"),
    SAVE("SAVE_FILE", "diskette.png");

    private final String title;
    private final ImageIcon icon;
    private ToolBarOptions(String title, String filename) {
        this.title = title;
        this.icon = CreateToolBarOption.getIcon(filename);
    }

    public String getTitle() {
        return this.title;
    }
    public ImageIcon getIcon() {
        return this.icon;
    }
}
