package com.scc210groupproject.applicationWIndow.helper;

import javax.swing.*;

/**
 * This enum consists of buttons (just its information like title, icons and actions) where its functionality is the same where ever its used.
 * The primary aim of this enum is to store buttons that will be use in more than one place and where is function is not changed
 * (but a new function can always be assigned).
 *
 * At some point the actions performed onClicks, drags etc. will be added in, or you can add them yourself.
 *
 * @author madukaag
 * */
public enum GeneralButtons {
    NEW("NEW FILE", "new-document.png"),
    OPEN("OPEN FILE", "folder.png"),
    SAVE("SAVE FILE", "diskette.png"),
    SAVE_AS("SAVE AS", ""),
    PRINT("PRINT", ""),
    RECENTS("RECENT", ""),
    CLIPBOARD("CLIPBOARD", ""),
    SELECT("SELECT", ""),
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

    private final String title; // Button title - will be used for hover text
    private final ImageIcon icon; // Button icon - will be used to set the icon of a button (32x32px)
    private GeneralButtons(String title, String filename) {
        this.title = title;
        this.icon = getIconFromFile(filename);
    }

    /**
     * Gets the title of the button
     * @return String
     * */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the icon of the button
     * @return ImageIcon
     * */
    public ImageIcon getIcon() {
        return this.icon;
    }

    /**
     * Gets the icon of a button from the file
     * @param file Only the name of the icon file
     * @return ImageIcon
     * */
    private ImageIcon getIconFromFile(String file) {
        String filePath = "src/main/resources/images/" + file;
        try {
            return new ImageIcon(filePath);
        } catch (Exception e) {
            return null;
        }
    }
}
