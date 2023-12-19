package com.scc210groupproject.applicationWIndow.contextMenu;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends the JPopupMenu and contains the context menus relevant information and
 * JElements. STILL IN PROGRESS
 *
 * @author madukaag
 * */
public class TextContextMenu extends JPopupMenu {
    private GridBagConstraints gbc = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();
    // A list of fonts
    private final String[] fonts = {"one", "two", "three"};
    // A list of font sizes
    private final Double[] fontSizes = {1.0, 2.0, 3.0, 4.0, 5.0};
    public TextContextMenu(JFrame frame, int x, int y) {

        JPanel firstRow = rowItemsOne();
        JPanel secondRow = rowItemsTwo();
        JMenuItem insert = new JMenuItem("Insert");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem rotate = new JMenuItem("Rotate");
        JMenuItem spaceAround = new JMenuItem("Space Around");

        this.add(firstRow);
        this.add(secondRow);
        this.add(insert);
        this.add(delete);
        this.add(rotate);
        this.add(spaceAround);

        this.show(frame, x, y);
    }

    private JPanel rowItemsOne() {
        JPanel row = new JPanel();
        row.setLayout(this.layout);
        row.setPreferredSize(new Dimension(300, 35));

        JComboBox fonts = new JComboBox(this.fonts);
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.gridwidth = 3;
        row.add(fonts, this.gbc);

        JComboBox fontSizes = new JComboBox(this.fontSizes);
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.gridwidth = 1;
        row.add(fontSizes, this.gbc);

        JButton move = new JButton("Move");
        this.gbc.gridx = 2;
        this.gbc.gridy = 0;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.gridwidth = 1;
        row.add(move, this.gbc);

        return row;
    }

    private JPanel rowItemsTwo() {
        JPanel row = new JPanel();
        row.setLayout(this.layout);

        this.gbc.gridheight = 1;
        this.gbc.gridwidth = 1;
        this.gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton bold = new JButton("B");
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        row.add(bold, this.gbc);

        JButton italics = new JButton("I");
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        row.add(italics, this.gbc);

        JButton underline = new JButton("U");
        this.gbc.gridx = 2;
        this.gbc.gridy = 0;
        row.add(underline, this.gbc);

        JButton colour = new JButton("Colour");
        this.gbc.gridx = 3;
        this.gbc.gridy = 0;
        row.add(colour, this.gbc);

        JButton align = new JButton("Align");
        this.gbc.gridx = 4;
        this.gbc.gridy = 0;
        row.add(align, this.gbc);

        return row;
    }

}
