package com.scc210groupproject.ui.contextMenu;

import com.scc210groupproject.structure.TextElement;
import com.scc210groupproject.structure.TextElement.Alignment;
import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends the JPopupMenu and contains the context menus relevant information and
 * JElements. STILL IN PROGRESS
 *
 * @author madukaag
 * */
public class TextContextMenu extends ContextMenu {

    public TextContextMenu(TextElement element) {
        this.setPreferredSize(new Dimension(300, 350));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(element.getFontSize(), 5, 400, 1));
        spinner.setPreferredSize(new Dimension(300, 20));
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                element.setFontSize((int)spinner.getValue());
            }
            
        });
        this.add(spinner);

        JPanel panel = new JPanel();
        {
            JButton left = new JButton(GeneralButtons.getIconFromFile("align-left.png"));
            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    element.setAlignment(Alignment.LEFT);
                }
            });
            panel.add(left);
            
            JButton center = new JButton(GeneralButtons.getIconFromFile("align-center.png"));
            center.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    element.setAlignment(Alignment.CENTER);
                }
            });
            panel.add(center);
    
            JButton justifed = new JButton(GeneralButtons.getIconFromFile("align-justified.png"));
            justifed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    element.setAlignment(Alignment.JUSTIFIED);
                }
            });
            panel.add(justifed);
    
            JButton right = new JButton(GeneralButtons.getIconFromFile("align-right.png"));
            right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    element.setAlignment(Alignment.RIGHT);
                }
            });
            panel.add(right);
        }
        this.add(panel);

        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setText(element.getText());
        area.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                element.setText(area.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                element.setText(area.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                element.setText(area.getText());
            }
            
        });
        this.add(area);
    }
public class TextContextMenu extends JPanel
{
    public TextContextMenu() {
        this.setPreferredSize(new Dimension(300, 350));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        this.setLayout(layout);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        Integer[] fontSizes = {8,9, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        String[] alignment = {"Left", "Center", "Right", "Justify"};

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        JComboBox<String> fontList = new JComboBox<>(fonts);
        this.add(fontList, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        JComboBox<Integer> fontSizeList = new JComboBox<>(fontSizes);
        this.add(fontSizeList, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JButton bold = new JButton(GeneralButtons.BOLD.getIcon());
        this.add(bold , gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JButton italic = new JButton(GeneralButtons.ITALIC.getIcon());
        this.add(italic, gbc);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(new JButton(GeneralButtons.UNDERLINE.getIcon()), gbc);

        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 0;
        // gbc.gridy = 1;
        // this.add(new JButton("Rotate", null), gbc);



    //     gbc.fill = GridBagConstraints.VERTICAL;
    //     gbc.gridx = 2;
    //     gbc.gridy = 0;
    //     JComboBox<String> alignmentList = new JComboBox<>(alignment);
    //     this.add(alignmentList, gbc);

    //     gbc.fill = GridBagConstraints.VERTICAL;
    //     gbc.gridx = 0;
    //     gbc.gridy = 1;
    //     this.add(new JButton("Rotate", null), gbc);

    //     gbc.fill = GridBagConstraints.VERTICAL;
    //     gbc.gridx = 1;
    //     gbc.gridy = 1;
    //     this.add(new JButton("Move", null), gbc);



    //     gbc.fill = GridBagConstraints.VERTICAL;
    //     gbc.gridx = 2;
    //     gbc.gridy = 2;
    //     this.add(new JButton("Change Colour", null), gbc);

    //     gbc.fill = GridBagConstraints.VERTICAL;
    //     gbc.gridx = 0;
    //     gbc.gridy = 3;
    //     this.add(new JButton("Border", null), gbc);

    //     gbc.fill = GridBagConstraints.VERTICAL;
    //     gbc.gridx = 1;
    //     gbc.gridy = 3;
    //     this.add(new JButton("Text Spacing", null), gbc);

    //     gbc.fill = GridBagConstraints.VERTICAL;
    //     gbc.gridx = 0;
    //     gbc.gridy = 4;
    //     gbc.gridwidth = 3;
    //     this.add(new JColorChooser(), gbc);
    }


}
