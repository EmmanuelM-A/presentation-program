package com.scc210groupproject.applicationWIndow.helper;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Objects;

/**
 * This enum consists of buttons (just its information like title, icons and actions) where its functionality is the same where ever its used.
 * The primary aim of this enum is to store buttons that will be use in more than one place and where is function is not changed
 * (but a new function can always be assigned).
 * <p>
 * At some point the actions performed onClicks, drags etc. will be added in, or you can add them yourself.
 *
 * @author madukaag
 * */
public enum GeneralButtons {
    NEW("New File", "new-document.png", 32, 32, null),
    OPEN("Open File", "folder.png", 32, 32, null),
    SAVE("Save File", "diskette.png", 32, 32, null),
    SAVE_AS("Save As", "", 32, 32, null),
    PRINT("Print", "", 32, 32, null),
    RECENTS("RECENT", "", 32, 32, null),
    CLIPBOARD("CLIPBOARD", "", 32, 32, null),
    SELECT("SELECT", "", 32, 32, null),
    PASTE("PASTE", "", 32, 32, null),
    SETTINGS("SETTINGS", "", 32, 32, null),
    SPELL_CHECKER("SPELL CHECKER", "", 32, 32, null),
    HELP("Help", "", 32, 32, null),
    NEW_SLIDE("NEW SLIDE", "", 32, 32, null),
    ADD_TEXT("TEXT", "add-text.png", 32, 32, null),
    ADD_IMAGE("IMAGE", "add-image.png", 32, 32, null),
    ADD_VIDEO("VIDEO", "add-video.png", 32, 32, null),
    ADD_AUDIO("AUDIO", "", 32, 32, null),
    ADD_DIAG("DIAGRAMS", "", 32, 32, null),
    SHAPES("SHAPES", "", 32, 32, null),
    EXPORT("Export", "export.png", 32, 32, null),
    IMPORT("IMPORT", "import.png", 32, 32, null),
    SHARE("Share", "share.png", 32, 32, null),
    FORMAT("Format", "", 32, 32, null),
    PRESENT("PRESENT", "projector-screen.png", 32, 32, null),
    PRESENT_AT("PRESENT AT", "", 32, 32, null),
    AUTOMATE("AUTOMATE", "", 32, 32, null),
    HIDE_SLIDE("HIDE SLIDE", "", 32, 32, null),
    ANIMATIONS("ANIMATIONS", "", 32, 32, null),
    TRANSITIONS("TRANSITIONS", "", 32, 32, null),
    LICENSE("LICENSE", "", 32, 32, null),
    SHORTCUTS("SHORTCUTS", "", 32, 32, null);

    private final String title; // Button title - will be used for hover text
    private final ImageIcon icon; // Button icon - will be used to set the icon of a button
    private final ActionListener event; //
    private GeneralButtons(String title, String filename, int width, int height, ActionListener event) {
        this.title = title;
        this.icon = resizeIcon( // Default dimensions are 32x32px
                Objects.requireNonNull(getIconFromFile(filename)),
                width <= 0 ? 32 : width,
                height <= 0 ? 32 : height
        );
        this.event = event;
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
     * Gets the action listener of the button
     * @return ActionListener
     * */
    public ActionListener getEvent() {
        return this.event;
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

    /**
     * Allows ImageIcons to be resized to a certain width and height
     * @param icon - ImageIcon
     * @param width The new width of the ImageIcon
     * @param height The height of the ImageIcon
     * @return ImageIcon
     */
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        // Get the Image from the ImageIcon
        Image image = icon.getImage();

        // Resize the Image
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Return the resized ImageIcon
        return new ImageIcon(resizedImage);
    }
}
