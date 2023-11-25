package com.scc210groupproject.toolBarOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateToolBarOption  {
    public static void createToolBarOption(String title, String file) {
        Action action = new AbstractAction("Open", getIcon(file)) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action Created!");
            }
        };
    }

    public static ImageIcon getIcon(String file) {
        String filePath = "src/main/resources/images/" + file;
        try {
            return new ImageIcon(filePath);
        } catch (Exception e) {
            return null;
        }
    }
}
