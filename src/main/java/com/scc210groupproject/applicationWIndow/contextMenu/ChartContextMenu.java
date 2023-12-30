package com.scc210groupproject.applicationWIndow.contextMenu;

import javax.swing.*;
import java.awt.*;

public class ChartContextMenu extends ContextMenu {

    private JScrollPane chartSelectionScrollPane;
    private JPanel chartSelectionPanel;
    private JMenuItem insertChart, deleteChart, editChart, insertText, drawChart;

    public ChartContextMenu(JFrame frame, int x, int y) {
        this.setPreferredSize(new Dimension(300, 350));

        // Menu items created here
        this.insertChart = makeMenuItem("Insert Chart", null);
        this.deleteChart = makeMenuItem("Delete Chart", null);
        this.editChart = makeMenuItem("Edit Chart", null);
        this.insertText = makeMenuItem("Insert Text", null);
        this.drawChart = makeMenuItem("Draw Chart", null);

        // Menu items added here
        this.add(this.insertChart);
        this.add(this.deleteChart);
        this.add(this.editChart);
        this.add(this.insertText);
        this.add(this.drawChart);

        // On some action display PopupMenu
        this.show(frame, x, y);
    }


}
