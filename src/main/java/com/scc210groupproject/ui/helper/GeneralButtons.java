package com.scc210groupproject.ui.helper;

import com.scc210groupproject.action.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
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
    CLIPBOARD("Clipboard", "clipboard.png", 32, 32, new ClipboardAction()),
    SELECT("Select", "selection.png", 32, 32, new SelectAction()),
    COPY("Copy", "copy.png", 32, 32, null),
    PASTE("Paste", "paste.png", 32, 32, new PasteAction()),
    SETTINGS("Settings", "gear.png", 32, 32, new SettingsAction()),
    SPELL_CHECKER("Spell Checker", "search.png", 32, 32, new SpellCheckerAction()),
    HELP("Help", "question.png", 32, 32, new HelpAction()),
    NEW_SLIDE("New Slide", "new-slide.png", 32, 32, new NewSlideAction()),
    ADD_TEXT("Text", "add-text.png", 32, 32, new NewTextElementAction()),
    ADD_IMAGE("Image", "add-image.png", 32, 32, new NewImageElementAction()),
    ADD_VIDEO("Video", "add-video.png", 32, 32, new NewVideoElementAction()),
    ADD_AUDIO("Audio", "add-audio.png", 32, 32, new NewAudioElementAction()),
    ADD_DIAG("Diagrams", "add-diagram.png", 32, 32, new NewDiagramElementAction()),
    ADD_CHART("Charts", "add-chart.png", 32, 32, new NewChartAction()),
    PIE("", "pie-chart.png", 32, 32, new NewPieChartAction()),
    BAR("", "bar-chart.png", 32, 32, new NewBarChartAction()),
    LINE("", "line-chart.png", 32, 32, new NewLineChartAction()),
    SCATTER("", "scatter-chart.png", 32, 32, new NewScatterChartAction()),
    ADDCOLUMN("", "add-column.png", 32, 32, new AddColumnAction()),
    ADDROW("", "add-row.png", 32, 32, new AddRowAction()),
    ADD_LINE("Line", "add-line.png", 32, 32, new NewArrowElementAction()),
    SHAPES("Shapes", "add-shapes.png", 32, 32, new ShapesAction()),
    EXPORT("Export", "export.png", 32, 32, new ExportAction()),
    IMPORT("Import", "import.png", 32, 32, new ImportAction()),
    SHARE("Share", "share.png", 32, 32, new ShareAction()),
    FORMAT("Format", "format.png", 32, 32, new FormatAction()),
    PRESENT("Present", "projector-screen.png", 32, 32, new PresentAction()),
    PRESENT_AT("Present From", "present-at.png", 32, 32, new PresentAtAction()),
    DELETE_SLIDE("Delete Slide", "delete-slide.png", 32, 32, null),
    DELETE_ELEMENT("Delete", "delete-element.png", 32, 32, null),
    AUTOMATE("Automate Slides", "", 32, 32, null),
    HIDE_SLIDE("Hide Slides", "", 32, 32, null),
    /*ANIMATIONS("Animations", "", 32, 32, null),
    TRANSITIONS("Transitions", "", 32, 32, null),*/
    LICENSE("License", "certificate.png", 32, 32, new LicenseAction()),
    SHORTCUTS("Shortcuts", "shortcuts.png", 32, 32, new ShortcutsAction()),
    TOGGLE_THEME("Theme", "toggle-theme.png", 32, 32, new ToggleThemeAction()),
    BOLD("", "bold.png", 32, 32, new StyleAction("BOLD")),
    ITALIC("", "italic.png", 32, 32, new StyleAction("ITALIC")),
    UNDERLINE("", "underline.png", 32, 32, new StyleAction("UNDERLINE")),
    STRIKETHROUGH("", "strikethrough.png", 32, 32, new StyleAction("STRIKETHROUGH")),
    ALIGN_LEFT("", "align-left.png", 32, 32, new StyleAction("ALIGNLEFT")),
    ALIGN_RIGHT("", "align-right.png", 32, 32, new StyleAction("ALIGNRIGHT")),
    ALIGN_CENTRE("", "align-centre.png", 32, 32, new StyleAction("ALIGNCENTRE")),
    JUSTIFY("", "justify.png", 32, 32, new StyleAction("JUSTIFY"));


    private final String title; // Button title - will be used for hover text
    private ImageIcon icon; // Button icon - will be used to set the icon of a button
    private final String iconName;
    private final ActionListener event; //
    private GeneralButtons(String title, String filename, int width, int height, ActionListener event) {
        this.title = "<html><center>"+title+"</center></html>";
        this.icon = resizeIcon( // Default dimensions are 32x32px
                Objects.requireNonNull(GeneralButtons.getIconFromFile(filename)),
                width <= 0 ? 32 : width,
                height <= 0 ? 32 : height
        );
        this.iconName = filename;
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
    
    public ImageIcon updateIcon() {
        this.icon = getIconFromFile(this.iconName);
        return getIcon();
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
    public static ImageIcon getIconFromFile(String file) {
        String filePath = "/home/tloxley/Year2/SCC210/scc210-2324-grp-62/src/main/resources/images/" + file;
        BufferedImage source;
        try {
            source = ImageIO.read(new File(filePath));
        } catch (Exception e) {
            System.out.println("Could not load icons: " + filePath);
            source = null;
        }
        BufferedImage result = source != null ?
            new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TRANSLUCENT) :
            new BufferedImage(32, 32, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = result.createGraphics();
        Color color = UIManager.getColor("Button.foreground");
        graphics.setColor(color);
        graphics.fillRect(0, 0, result.getWidth(), result.getHeight());
        if (source != null) {
            graphics.setComposite(AlphaComposite.DstIn);
            graphics.drawImage(source, null, 0, 0);
        }
        graphics.dispose();
        return new ImageIcon(result);
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

        return resizeIcon(image, width, height);
    }

    /**
     * Allows ImageIcons to be resized to a certain width and height
     * @param iamge - Image
     * @param width The new width of the ImageIcon
     * @param height The height of the ImageIcon
     * @return ImageIcon
     */
    public static ImageIcon resizeIcon(Image image, int width, int height) {

        // Resize the Image
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Return the resized ImageIcon
        return new ImageIcon(resizedImage);
    }
}
