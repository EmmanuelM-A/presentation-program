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

    public TextContextMenu(TextElement element)
    {
        this.setPreferredSize(new Dimension(300, 350));
        GridBagLayout gBagLayout = new GridBagLayout();
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        this.setLayout(boxLayout);
    
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String[] alignment = {"Left", "Center", "Right", "Justify"};

        JComboBox<String> fontList = new JComboBox<>(fonts);
        fontList.setSelectedItem(element.getFontFamily());
        fontList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setFont((String)fontList.getSelectedItem());
            }    
        });            

        JSpinner fontSize = new JSpinner(new SpinnerNumberModel(element.getFontSize(), 5, 400, 1));
        fontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                element.setFontSize((int)fontSize.getValue());
            }    
        });  

        JButton bold = new JButton(GeneralButtons.BOLD.getIcon());
        bold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setBold(true);
            }    
        }); 

        JButton underline = new JButton(GeneralButtons.UNDERLINE.getIcon());
        underline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setUnderline(true);
            }    
        });

        JButton italic = new JButton(GeneralButtons.ITALIC.getIcon());
        italic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setItalic(true);
            }    
        }); 

        JButton strikeThrough = new JButton(GeneralButtons.STRIKETHROUGH.getIcon());
        italic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setStrikeThrough(true);
            }    
        }); 

        JButton left = new JButton(GeneralButtons.ALIGN_LEFT.getIcon());
            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    element.setAlignment(Alignment.LEFT);
                }
            });
        
        JButton center = new JButton(GeneralButtons.ALIGN_CENTRE.getIcon());
        center.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setAlignment(Alignment.CENTER);
            }
        });

        JButton right = new JButton(GeneralButtons.ALIGN_RIGHT.getIcon());
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setAlignment(Alignment.RIGHT);
            }
        });   

        JButton justifed = new JButton(GeneralButtons.JUSTIFY.getIcon());
        justifed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                element.setAlignment(Alignment.JUSTIFIED);
            }
        });

        JPanel optionsPanel = new JPanel();
        {
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            optionsPanel.add(fontList, gbc);

            gbc.gridx = 5;
            gbc.gridwidth = 1;
            optionsPanel.add(fontSize, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 1;
            optionsPanel.add(bold , gbc);

            gbc.gridx = 1;
            optionsPanel.add(underline, gbc);
            
            gbc.gridx = 2;
            optionsPanel.add(italic, gbc);

            gbc.gridx = 3;
            optionsPanel.add(strikeThrough, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            optionsPanel.add(left, gbc);

            gbc.gridx = 1;
            optionsPanel.add(center, gbc);

            gbc.gridx = 2;
            optionsPanel.add(right, gbc);

            gbc.gridx = 3;
            optionsPanel.add(justifed, gbc);

        }
        this.add(optionsPanel, BoxLayout.Y_AXIS);
    
        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 0;
        // gbc.gridy = 1;
        // this.add(new JButton("Rotate", null), gbc);
    
        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 0;
        // gbc.gridy = 1;
        // this.add(new JButton("Rotate", null), gbc);

        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 1;
        // gbc.gridy = 1;
        // this.add(new JButton("Move", null), gbc);

        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 2;
        // gbc.gridy = 2;
        // this.add(new JButton("Change Colour", null), gbc);

        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 0;
        // gbc.gridy = 3;
        // this.add(new JButton("Border", null), gbc);

        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 1;
        // gbc.gridy = 3;
        // this.add(new JButton("Text Spacing", null), gbc);

        // gbc.fill = GridBagConstraints.VERTICAL;
        // gbc.gridx = 0;
        // gbc.gridy = 4;
        // gbc.gridwidth = 3;
        // this.add(new JColorChooser(), gbc);

        JPanel panel = new JPanel();
        {
            BorderLayout borderLayout = new BorderLayout();
            panel.setLayout(borderLayout);
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
            panel.add(area, BorderLayout.CENTER);
        }
        this.add(panel, BoxLayout.Y_AXIS);
    }
}
