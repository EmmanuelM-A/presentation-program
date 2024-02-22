package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

import com.itextpdf.io.image.*; 
import com.itextpdf.kernel.pdf.*; 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Image;
import com.scc210groupproject.ui.UIFrame; 

public class ExportAction implements ActionListener {

    String path = new String();

    @Override
    public void actionPerformed(ActionEvent e) {
        JWindow fileWindow = new JWindow();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION)
            return;

        path = fileChooser.getSelectedFile().getAbsolutePath();
        try {
            export();
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(UIFrame.instance, e, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void export() throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
    }
}
