package com.scc210groupproject.ui.helper;

import com.scc210groupproject.action.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Objects;

/**
 * This enum consists of buttons (just its information like title, icons and actions) where its functionality is the same where ever its used.
 * The primary aim of this enum is to store buttons that will be use in more than one place and where is function is not changed
 * (but a new function can always be assigned).
 * <p>
 *
 * @author madukaag
 * */
public enum GeneralButtons {
    NEW("New File", "new-document.png", 32, 32, new NewFileAction()),
    OPEN("Open File", "folder.png", 32, 32, new OpenAction()),
    SAVE("Save File", "diskette.png", 32, 32, new SaveAction()),
    SAVE_AS("Save As", "save-as.png", 32, 32, new SaveAsAction()),
    PRINT("Print", "printer.png", 32, 32, new PrintAction()),
    RECENTS("Recents", "", 32, 32, new RecentsAction()),
    CLIPBOARD("Clipboard", "clipboard.png", 32, 32, new ClipboardAction()),
    SELECT("Select", "selection.png", 32, 32, new SelectAction()),
    PASTE("Paste", "paste.png", 32, 32, new PasteAction()),
    SETTINGS("Settings", "gear.png", 32, 32, new SettingsAction()),
    SPELL_CHECKER("Spell Checker", "search.png", 32, 32, new SpellCheckerAction()),
    HELP("Help", "question.png", 32, 32, new HelpAction()),
    NEW_SLIDE("New Slide", "new-slide.png", 32, 32, new NewSlideAction()),
    ADD_TEXT("Text", "add-text.png", 32, 32, new NewTextElementAction()),
    ADD_IMAGE("Image", "add-image.png", 32, 32, new NewImageElementAction()),
    ADD_VIDEO("Video", "add-video.png", 32, 32, new NewVideoElementAction()),
    ADD_AUDIO("Audio", "add-audio.png", 32, 32, new NewAudioElementAction()),
    ADD_DIAG("Diagrams", "", 32, 32, new NewDiagramElementAction()),
    SHAPES("Shapes", "", 32, 32, new ShapesAction()),
    EXPORT("Export", "export.png", 32, 32, new ExportAction()),
    IMPORT("Import", "import.png", 32, 32, new ImportAction()),
    SHARE("Share", "share.png", 32, 32, new ShareAction()),
    FORMAT("Format", "format.png", 32, 32, new FormatAction()),
    PRESENT("Present", "projector-screen.png", 32, 32, new PresentAction()),
    PRESENT_AT("Present From", "", 32, 32, new PresentAtAction()),
    AUTOMATE("Automate Slides", "", 32, 32, null),
    HIDE_SLIDE("Hide Slides", "", 32, 32, null),
    ANIMATIONS("Animations", "", 32, 32, null),
    TRANSITIONS("Transitions", "", 32, 32, null),
    LICENSE("License", "", 32, 32, new LicenseAction()),
    SHORTCUTS("Shortcuts", "", 32, 32, new ShortcutsAction());

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
        String filePath = "/home/tloxley/Year2/SCC210/scc210-2324-grp-62/src/main/resources/images/" + file;
        try {
            return new ImageIcon(filePath);
        } catch (Exception e) {
            System.out.println("Could not load icons");
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
