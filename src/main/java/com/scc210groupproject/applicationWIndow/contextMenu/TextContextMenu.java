package com.scc210groupproject.applicationWIndow.contextMenu;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends the JPopupMenu and contains the context menus relevant information and
 * JElements. STILL IN PROGRESS
 *
 * @author madukaag
 * */
public class TextContextMenu extends ContextMenu {
    // Other menu items to add: selectFont, selectFontSize
    private JMenuItem insertText, deleteText, rotateText, alignText, moveText, boldText, italicText, underlineText, colourText, border, textSpacing;
    public TextContextMenu(JFrame frame, int x, int y) {
        this.setPreferredSize(new Dimension(300, 350));

        // Menu items created here
        this.insertText = makeMenuItem(
            "Add Text",
            GeneralButtons.resizeIcon(GeneralButtons.ADD_TEXT.getIcon(), 20, 20)
        );
        this.deleteText = makeMenuItem("Delete Text", null);
        this.alignText = makeMenuItem("Align Text", null);
        this.rotateText = makeMenuItem("Rotate", null);
        this.moveText = makeMenuItem("Move", null);
        this.boldText = makeMenuItem("Bold", null);
        this.italicText = makeMenuItem("Italics", null);
        this.underlineText = makeMenuItem("Underline", null);
        this.colourText = makeMenuItem("Change Colour", null);
        this.border = makeMenuItem("Border", null);
        this.textSpacing = makeMenuItem("Text Spacing", null);

        // Menu items added here
        this.add(this.insertText);
        this.add(this.deleteText);
        this.add(this.alignText);
        this.add(this.rotateText);
        this.add(this.moveText);
        this.add(this.boldText);
        this.add(this.italicText);
        this.add(this.underlineText);
        this.add(this.colourText);
        this.add(this.border);
        this.add(this.textSpacing);

        // On some action display PopupMenu
        this.show(frame, x, y);
    }



}
