package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.ui.Settings;

public class SettingsAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Settings.instance.createSettingsWindow();
    }
}
