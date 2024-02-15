package com.scc210groupproject.ui.contextMenu;

import javax.swing.*;
import java.awt.*;

import com.scc210groupproject.structure.ChartElement;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class ChartContextMenu extends ContextMenu {

    private JTable table;

    public ChartContextMenu(ChartElement element) {

        this.makeChartContextMenu();

        String[][] data = element.getData();

        for(int i = 0; i < table.getRowCount(); i++) {
            for(int j = 0; j < table.getColumnCount(); j++) {
                if (data[i][j] != null) {
                    this.table.setValueAt(data[i][j], i, j);
                }
            }
        }
    }

    public ChartContextMenu() {

        this.makeChartContextMenu(); 
    }

    private void makeChartContextMenu() {

        this.setPreferredSize(new Dimension(300, 350));
        GridBagLayout gBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        this.setLayout(gBagLayout);

        JButton pie = makeContextMenuButton(GeneralButtons.PIE);

        JButton bar = makeContextMenuButton(GeneralButtons.BAR);

        JButton line = makeContextMenuButton(GeneralButtons.LINE);

        JButton scatter = makeContextMenuButton(GeneralButtons.SCATTER);

        JButton addColumn = makeContextMenuButton(GeneralButtons.ADDCOLUMN);

        JButton addRow = makeContextMenuButton(GeneralButtons.ADDROW);

        this.table = new JTable(50, 10);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1;
        this.add(pie, gbc);

        gbc.gridx = 1;
        this.add(bar, gbc);

        gbc.gridx = 2;
        this.add(line, gbc);

        gbc.gridx = 3;
        this.add(scatter, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(addColumn, gbc);

        gbc.gridx = 2;
        this.add(addRow, gbc);

        JScrollPane scrollPane = new JScrollPane(); {
            //BorderLayout borderLayout = new BorderLayout();
            //scrollPane.setLayout(borderLayout);
            table.setGridColor(Color.BLACK);
            table.setShowGrid(true);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setViewportView(this.table);
        }
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; gbc.weighty = 1;
        this.add(scrollPane, gbc);
    }

    public JTable getTable() {
        return this.table;
    }
}
