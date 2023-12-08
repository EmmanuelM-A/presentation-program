package com.scc210groupproject.ui.toolBars.helper;

import javax.swing.*;

public enum ResourceReference {

    NEW_FILE(
        "NEW\nFILE", 
        "new-document.png"),
    OPEN_FILE(
        "OPEN\nFILE", 
        "folder.png"),
    SAVE_FILE(
        "SAVE\nFILE", 
        "diskette.png"),
    CREATE_SLIDE(
        "NEW\nSLIDE",
        "create-slide.png"),
    ADD_TEXT(
        "TEXT", 
        "add-text.png"),
    ADD_IMAGE(
        "IMAGE", 
        "add-image.png"),
    ADD_VIDEO(
        "VIDEO", 
        "add-video.png"),
    EXPORT(
        "EXPORT", 
        "export.png"),
    IMPORT(
        "IMPORT", 
        "import.png"),
    SHARE(
        "SHARE", 
        "share.png"),
    PRESENT(
        "PRESENT", 
        "projector-screen.png");

    private static final String imageFolderPath = "src/main/resources/images/";

    public final String multiLineTitle;
    public final ImageIcon icon;

    private ResourceReference(String title, String filename)
    {
        String[] lines = title.split("\n");
        this.multiLineTitle = "<html><center>" + String.join("<br>", lines) + "</center></html>";
        this.icon = new ImageIcon(imageFolderPath + filename);
    }
}