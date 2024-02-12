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
}
