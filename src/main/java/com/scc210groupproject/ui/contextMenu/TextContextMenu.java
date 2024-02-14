package com.scc210groupproject.ui.contextMenu;

import com.scc210groupproject.structure.TextElement;
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
public class TextContextMenu extends ContextMenu{

    public TextContextMenu(TextElement element)
    {
        this.setPreferredSize(new Dimension(300, 350));
        GridBagLayout gBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        this.setLayout(gBagLayout);
    
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

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
        
        JColorChooser colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                element.setBackground(colorChooser.getColor());
            }    
        }); 

        JButton bold = makeContextMenuButton(GeneralButtons.BOLD);

        JButton underline = makeContextMenuButton(GeneralButtons.UNDERLINE);

        JButton italic = makeContextMenuButton(GeneralButtons.ITALIC);

        JButton strikeThrough = makeContextMenuButton(GeneralButtons.STRIKETHROUGH);

        JButton left = makeContextMenuButton(GeneralButtons.ALIGN_LEFT);
        
        JButton centre = makeContextMenuButton(GeneralButtons.ALIGN_CENTRE);

        JButton right = makeContextMenuButton(GeneralButtons.ALIGN_RIGHT);

        JButton justifed = makeContextMenuButton(GeneralButtons.JUSTIFY);

        JPanel optionsPanel = new JPanel();
        {
            optionsPanel.setLayout(gBagLayout);

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
            optionsPanel.add(fontList, gbc);

            gbc.gridx = 5; gbc.gridwidth = 1;
            optionsPanel.add(fontSize, gbc);

            gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1;
            optionsPanel.add(bold, gbc);

            gbc.gridx = 1;
            optionsPanel.add(underline, gbc);
            
            gbc.gridx = 2;
            optionsPanel.add(italic, gbc);

            gbc.gridx = 3;
            optionsPanel.add(strikeThrough, gbc);

            gbc.gridx = 0; gbc.gridy = 2;
            optionsPanel.add(left, gbc);

            gbc.gridx = 1;
            optionsPanel.add(centre, gbc);

            gbc.gridx = 2;
            optionsPanel.add(right, gbc);

            gbc.gridx = 3;
            optionsPanel.add(justifed, gbc);

            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 6; 
            optionsPanel.add(colorChooser, gbc);
        }
        gbc.gridy = 0; gbc.gridwidth = 1;
        this.add(optionsPanel, gbc);

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
        gbc.gridy = 1; gbc.weighty = 1;
        this.add(panel, gbc);
    }
}
