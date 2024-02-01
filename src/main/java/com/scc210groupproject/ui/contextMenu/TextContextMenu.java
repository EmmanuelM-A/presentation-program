package com.scc210groupproject.ui.contextMenu;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;

public class TextContextMenu extends JPanel
{
    public TextContextMenu() {
        this.setPreferredSize(new Dimension(300, 350));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(layout);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        Integer[] fontSizes = {8,9, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JComboBox<String> fontList = new JComboBox<>(fonts);
        this.add(fontList, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        JComboBox<Integer> fontSizeList = new JComboBox<>(fontSizes);
        this.add(fontSizeList, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(new JButton("Align Text", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JButton("Rotate", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(new JButton("Move", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(new JButton("Bold", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new JButton("Italics", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(new JButton("Underline", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(new JButton("Change Colour", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new JButton("Border", null), gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(new JButton("Text Spacing", null), gbc);
    }



}
