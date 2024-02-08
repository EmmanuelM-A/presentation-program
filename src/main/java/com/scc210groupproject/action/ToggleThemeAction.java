package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.scc210groupproject.ui.UIFrame;

public class ToggleThemeAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent discard) {
        try {
            if (UIManager.getLookAndFeel() instanceof FlatLightLaf) {
                FlatDarkLaf.setup();
                UIManager.setLookAndFeel(new FlatDarkLaf());
            }
            else {
                FlatLightLaf.setup();
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            SwingUtilities.updateComponentTreeUI(UIFrame.instance);
            UIFrame.instance.notifyUpdate(UIFrame.instance);
        }
        catch (UnsupportedLookAndFeelException e) {
            System.out.println("Falling back to default style");
        }
    }
    
}
