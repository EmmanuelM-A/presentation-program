package com.scc210groupproject.applicationWIndow.contextMenu;

import javax.swing.*;
import java.awt.*;

public class CodeContextMenu extends ContextMenu {
    private JMenuItem insertCode, editCode, deleteCode, importFile, exportFile, git, colourScheme;
    public CodeContextMenu(JFrame frame, int x, int y) {
        this.setPreferredSize(new Dimension(300, 350));

        this.insertCode = makeMenuItem("Insert Code Block", null);
        this.editCode = makeMenuItem("Edit Code Block", null);
        this.deleteCode = makeMenuItem("Delete Code Block", null);
        this.importFile = makeMenuItem("Import File", null);
        this.exportFile = makeMenuItem("Export File", null);
        this.git = makeMenuItem("Git", null);
        this.colourScheme = makeMenuItem("Colour Format", null);

        this.add(this.insertCode);
        this.add(this.editCode);
        this.add(this.deleteCode);
        this.add(this.importFile);
        this.add(this.exportFile);
        this.add(this.git);
        this.add(this.colourScheme);

        this.show(frame, x, y);
    }
}
