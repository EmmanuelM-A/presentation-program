package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.contextMenu.ChartContextMenu;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;

public class AddRowAction implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ChartContextMenu menu = (ChartContextMenu)ContextMenuPanel.menu;
        Object[] rows = new Object[menu.getTable().getColumnCount()];
        for(int i = 0; i < rows.length; i++) {
            rows[i] = null;
        }
        menu.getTableModel().addRow(rows);

        ContextMenuPanel.instance.revalidate();

    }

}
