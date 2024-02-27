package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.ArrowElement;
import com.scc210groupproject.structure.ArrowElement.Side;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.contextMenu.ArrowContextMenu;

public class ToggleAction implements ActionListener{

    String feature;

    public ToggleAction(String feature) {
        this.feature = feature;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrowElement element = ((ArrowElement)ContextMenuPanel.currentMenuOwner);
        switch (feature) {
            case ("A"):
                element.toggleArrow(Side.A);
                break;
            case ("B"):
                element.toggleArrow(Side.B);
                break;
            case("DOTTED"):
            case("SOLID"):
                element.toggleLine();
                ContextMenuPanel.setMenu(element, new ArrowContextMenu(element));
                break;    
        }
        element.notifyUpdate(element);
    }

}
