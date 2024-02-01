package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAsAction implements ActionListener{
    
    FileFilter plainFilter = new FileNameExtensionFilter("Debug Presentation File", ".pjson");
    FileFilter compressedFilter = new FileNameExtensionFilter("Compressed Presentation File", ".pcomp");

    @Override
    public void actionPerformed(ActionEvent _){

        // Needs to be completed

    }

}
