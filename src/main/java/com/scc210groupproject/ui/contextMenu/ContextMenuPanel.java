package com.scc210groupproject.ui.contextMenu;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the where the context menus will be added - STILL IN PROGRESS
 *
 * @author madukaag
 * */
public class ContextMenuPanel{

    public static JPanel contextMenuPanel = new JPanel(new BorderLayout());

    public ContextMenuPanel(JPanel contextMenu) {
        contextMenuPanel.add(contextMenu, BorderLayout.CENTER);
    }
}
