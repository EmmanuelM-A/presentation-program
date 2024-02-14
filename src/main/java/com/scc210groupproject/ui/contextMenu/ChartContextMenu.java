package com.scc210groupproject.ui.contextMenu;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.scc210groupproject.structure.ChartElement;
import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class ChartContextMenu extends ContextMenu {

    private JTable data;
    private String[][] tableData;

    public ChartContextMenu(ChartElement element) {

        this.setPreferredSize(new Dimension(300, 350));
        GridBagLayout gBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        this.setLayout(gBagLayout);

        JButton pie = makeContextMenuButton(GeneralButtons.PIE);

        JButton bar = makeContextMenuButton(GeneralButtons.BAR);

        JButton line = makeContextMenuButton(GeneralButtons.LINE);

        JButton xy = makeContextMenuButton(GeneralButtons.SCATTER);

        this.data = new JTable();

    }

    public ChartContextMenu() {

        this.setPreferredSize(new Dimension(300, 350));
        GridBagLayout gBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        this.setLayout(gBagLayout);

        JButton pie = makeContextMenuButton(GeneralButtons.PIE);

        JButton bar = makeContextMenuButton(GeneralButtons.BAR);

        JButton line = makeContextMenuButton(GeneralButtons.LINE);

        JButton scatter = makeContextMenuButton(GeneralButtons.SCATTER);

        JTable data = new JTable(40, 4);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1;
        this.add(pie, gbc);

        gbc.gridx = 1;
        this.add(bar, gbc);

        gbc.gridx = 2;
        this.add(line, gbc);

        gbc.gridx = 3;
        this.add(scatter, gbc);

        JPanel panel = new JPanel(); {
            BorderLayout borderLayout = new BorderLayout();
            panel.setLayout(borderLayout);
            data.setGridColor(Color.BLACK);
            data.setShowGrid(true);
            panel.add(this.data, BorderLayout.CENTER);
        }
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4; gbc.weighty = 1;
        this.add(panel, gbc);
    }

    private void getTableData() {
        for(int i = 0; i < data.getRowCount(); i++) {
            for(int j = 0; j < data.getColumnCount(); j++) {
                tableData[i][j] = (String)data.getValueAt(i, j);
            }
        }
    }
}
