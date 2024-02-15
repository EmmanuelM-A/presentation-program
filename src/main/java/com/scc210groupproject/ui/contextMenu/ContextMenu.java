package com.scc210groupproject.ui.contextMenu;

import javax.swing.*;

import com.scc210groupproject.ui.helper.GeneralButtons;
import com.scc210groupproject.ui.helper.ThemeAwareButton;

public class ContextMenu extends JPanel{

    public JButton makeContextMenuButton(GeneralButtons buttonType) {

        ThemeAwareButton button = new ThemeAwareButton(buttonType);

        button.addActionListener(buttonType.getEvent());
        
        return button;
    }
}
