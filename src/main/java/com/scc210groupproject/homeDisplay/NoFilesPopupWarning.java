package com.scc210groupproject.homeDisplay;

import javax.swing.JOptionPane;

public class NoFilesPopupWarning {
        
    public static void noFilesPopup(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage,  titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
