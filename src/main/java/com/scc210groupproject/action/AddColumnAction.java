package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.contextMenu.ChartContextMenu;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;

public class AddColumnAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ChartContextMenu menu = (ChartContextMenu)ContextMenuPanel.menu;
        Object[] columns = new Object[menu.getTable().getRowCount()];
        for(int i = 0; i < columns.length; i++) {
            columns[i] = "cheese";
        }
        menu.getTableModel().addColumn(columns);

        ContextMenuPanel.instance.revalidate();

    }

}
