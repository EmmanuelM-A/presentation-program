package com.scc210groupproject.applicationWIndow.toolBarOptions;

import javax.swing.*;

public enum ToolBarOptions {
    NEW("NEW_FILE", "new-document.png"),
    OPEN("OPEN_FILE", "folder.png"),
    SAVE("SAVE_FILE", "diskette.png");

    private final String title;
    private final ImageIcon icon;
    private ToolBarOptions(String title, String filename) {
        this.title = title;
        this.icon = getIconFromFile(filename);
    }

    public String getTitle() {
        return this.title;
    }
    public ImageIcon getIcon() {
        return this.icon;
    }

    private ImageIcon getIconFromFile(String file) {
        String filePath = "src/main/resources/images/" + file;
        try {
            return new ImageIcon(filePath);
        } catch (Exception e) {
            return null;
        }
    }
}
