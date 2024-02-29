package com.scc210groupproject.ui.contextMenu;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.scc210groupproject.structure.ArrowElement;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class ArrowContextMenu extends ContextMenu {

    JButton line;

    public ArrowContextMenu(ArrowElement element) {

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                element.setColor(colorChooser.getColor());
                element.notifyUpdate(element);
            }    
        }); 

        this.add(colorChooser);

        JButton arrowA = makeContextMenuButton(GeneralButtons.TOGGLEARROWA);

        JButton arrowB = makeContextMenuButton(GeneralButtons.TOGGLEARROWB);

        if (element.getLineType()) {
            line = makeContextMenuButton(GeneralButtons.TOGGLESOLID);
        } else {
            line = makeContextMenuButton(GeneralButtons.TOGGLEDOTTED);
        }

        this.add(arrowA);
        this.add(line);
        this.add(arrowB); 
    }
    
}
