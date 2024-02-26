package com.scc210groupproject.ui.contextMenu;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.scc210groupproject.structure.ExtendedElement;

public class ShapeContextMenu extends ContextMenu {

    public ShapeContextMenu(ExtendedElement shape) {

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                shape.setBackground(colorChooser.getColor());
            }    
        }); 

        this.add(colorChooser);
    }
}
