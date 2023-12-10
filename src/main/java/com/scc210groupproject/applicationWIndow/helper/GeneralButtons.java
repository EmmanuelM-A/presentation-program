package com.scc210groupproject.applicationWIndow.helper;

import javax.swing.*;

public enum GeneralButtons {
    NEW("NEW FILE", "new-document.png"),
    OPEN("OPEN FILE", "folder.png"),
    SAVE("SAVE FILE", "diskette.png"),
    SAVE_AS("SAVE AS", ""),
    PRINT("PRINT", ""),
    RECENTS("RECENTS", ""),
    CLIPBOARD("CLIPBOARD", ""),
    SELECT("SELCET", ""),
    PASTE("PASTE", ""),
    SETTINGS("SETTINGS", ""),
    SPELL_CHECKER("SPELL CHECKER", ""),
    HELP("HELP", ""),
    NEW_SLIDE("NEW SLIDE", ""),
    ADD_TEXT("TEXT", "add-text.png"),
    ADD_IMAGE("IMAGE", "add-image.png"),
    ADD_VIDEO("VIDEO", "add-video.png"),
    ADD_AUDIO("AUDIO", ""),
    ADD_DIAG("DIAGRAMS", ""),
    SHAPES("SHAPES", ""),
    EXPORT("EXPORT", "export.png"),
    IMPORT("IMPORT", "import.png"),
    SHARE("SHARE", "share.png"),
    FORMAT("FORMAT", ""),
    PRESENT("PRESENT", "projector-screen.png"),
    PRESENT_AT("PRESENT AT", ""),
    AUTOMATE("AUTOMATE", ""),
    HIDE_SLIDE("HIDE SLIDE", ""),
    ANIMATIONS("ANIMATIONS", ""),
    TRANSITIONS("TRANSITIONS", ""),
    LICENSE("LICENSE", ""),
    SHORTCUTS("SHORTCUTS", "");

    private final String title;
    private final ImageIcon icon;
    private GeneralButtons(String title, String filename) {
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
