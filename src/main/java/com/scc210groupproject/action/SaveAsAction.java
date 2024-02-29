package com.scc210groupproject.action;

import com.scc210groupproject.readwrite.FileSerializer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAsAction implements ActionListener{
    
    FileFilter compressedFilter = new FileNameExtensionFilter("Compressed Presentation File", ".pcomp");

    @Override
    public void actionPerformed(ActionEvent discard) {
        SaveAction.save(true);
    }

}
