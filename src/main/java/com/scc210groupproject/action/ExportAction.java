package com.scc210groupproject.action;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.io.image.*;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*; 
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.AreaBreakType;
import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.UIFrame; 

public class ExportAction implements ActionListener {

    String path = new String();
    FileFilter pdfFilter = new FileNameExtensionFilter("PDF", ".pdf");

    @Override
    public void actionPerformed(ActionEvent e) {
        JWindow fileWindow = new JWindow();

        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(pdfFilter);
        int result = chooser.showSaveDialog(fileWindow);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        path = chooser.getSelectedFile().getAbsolutePath();
        String extension = ((FileNameExtensionFilter) chooser.getFileFilter()).getExtensions()[0];

        if (!path.endsWith(extension)) {
            path += extension;
        }

        try {
            export();
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(UIFrame.instance, e, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void export() throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfDocument);

        Image[] slides = new Image[SlideManager.slideManager.getSlideCount()];

        for(int i = 0; i < SlideManager.slideManager.getSlideCount(); i++) {

            try {
                ImageData imageData = ImageDataFactory.create(SlideManager.slideManager.getSlideImageAt(i).getSlide().createPreview(Toolkit.getDefaultToolkit().getScreenSize()), null);
                slides[i] = new Image(imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }

            slides[i].setAutoScale(true);
            document.add(slides[i]);
            if(i < SlideManager.slideManager.getSlideCount() - 1) { document.add(new AreaBreak(AreaBreakType.NEXT_PAGE)); }
        }

        document.close(); 
    }
}
