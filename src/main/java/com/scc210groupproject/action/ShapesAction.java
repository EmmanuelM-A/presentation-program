package com.scc210groupproject.action;

import com.scc210groupproject.ui.ShapesPopup;
import com.scc210groupproject.ui.menuBarTabs.MenuBarTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class ShapesAction implements ActionListener { 
    @Override
    public void actionPerformed(ActionEvent discard) {

        JButton addShapeBtn = MenuBarTabs.instance.getInsertToolBar().getAddShape();

        addShapeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    ShapesPopup.popupMenu.show(addShapeBtn, 2, 76);
                }
            }
        });

    } 
}
