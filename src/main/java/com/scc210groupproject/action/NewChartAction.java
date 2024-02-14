package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.contextMenu.ChartContextMenu;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.contextMenu.TextContextMenu;

public class NewChartAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ContextMenuPanel.setMenu(null, new ChartContextMenu());
    }

}
