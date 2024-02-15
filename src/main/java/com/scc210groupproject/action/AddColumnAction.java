package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.contextMenu.ChartContextMenu;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;

public class AddColumnAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ChartContextMenu chartContextMenu = (ChartContextMenu)ContextMenuPanel.menu;
        chartContextMenu.getTable().addColumn(null);
    }

}
