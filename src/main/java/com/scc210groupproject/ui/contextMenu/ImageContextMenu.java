package com.scc210groupproject.ui.contextMenu;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;

public class ImageContextMenu extends ContextMenu {
    private JMenuItem insertImage, deleteImage, rotateImage, resizeImage, border, insertText, insertLink, draw, shapes, lines;
    public ImageContextMenu(JFrame frame, int x, int y) {
        this.setPreferredSize(new Dimension(300, 350));

        this.insertImage = makeMenuItem(
                "Insert Image",
                GeneralButtons.resizeIcon(GeneralButtons.ADD_IMAGE.getIcon(), 20, 20)
        );
        this.deleteImage = makeMenuItem("Delete Image", null);
        this.rotateImage = makeMenuItem("Rotate", null);
        this.resizeImage = makeMenuItem("Resize", null);
        this.border = makeMenuItem("Border", null);
        this.insertText = makeMenuItem("Add Text", null);
        this.insertLink = makeMenuItem("Add Link", null);
        this.draw = makeMenuItem("Draw", null);
        this.shapes = makeMenuItem("Add Shapes", null);
        this.lines = makeMenuItem("Add Lines", null);


        this.add(insertImage);
        this.add(deleteImage);
        this.add(rotateImage);
        this.add(resizeImage);
        this.add(border);
        this.add(insertText);
        this.add(insertLink);
        this.add(draw);
        this.add(shapes);
        this.add(lines);

        //this.show(frame, x, y);
    }
}
