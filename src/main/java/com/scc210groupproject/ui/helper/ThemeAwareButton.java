package com.scc210groupproject.ui.helper;

import javax.swing.JButton;

import com.scc210groupproject.structure.liveness.IUpdateListener;
import com.scc210groupproject.ui.UIFrame;

public class ThemeAwareButton extends JButton implements IUpdateListener {

    private GeneralButtons buttonType;

    public ThemeAwareButton(GeneralButtons button) {
        buttonType = button;
        super.setText(buttonType.getTitle());
        super.setIcon(buttonType.getIcon());
        UIFrame.instance.addUpdateListener(this);
    }
    
    public GeneralButtons getButtonType() {
        return buttonType;
    }

    @Override
    public void onUpdate(Object object) {
        super.setIcon(buttonType.updateIcon());
    }
    
}
