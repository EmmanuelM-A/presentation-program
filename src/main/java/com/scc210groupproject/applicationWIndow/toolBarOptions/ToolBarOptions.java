package com.scc210groupproject.applicationWIndow.toolBarOptions;

import javax.swing.*;

public enum ToolBarOptions {
    NEW("NEW FILE", "new-document.png"),
    OPEN("OPEN FILE", "folder.png"),
    SAVE("SAVE FILE", "diskette.png"),
    ADD_TEXT("TEXT", "add-text.png"),
    ADD_IMAGE("IMAGE", "add-image.png"),
    ADD_VIDEO("VIDEO", "add-video.png"),
    EXPORT("EXPORT", "export.png"),
    IMPORT("IMPORT", "import.png"),
    SHARE("SHARE", "share.png"),
    PRESENT("PRESENT", "projector-screen.png");

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
