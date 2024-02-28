package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterJob;

public class PrintAction implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent discard){

        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.printDialog();
        
    }

}
