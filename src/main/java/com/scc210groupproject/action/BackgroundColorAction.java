package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;

public class BackgroundColorAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {

        System.out.println(ContextMenuPanel.currentMenuOwner.getClass());
        
    }
    
}