package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.Presentation;

public class NewFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {
        Presentation.createNewAsCurrent();
    }
    
}
