package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.Presentation;

public class NewFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {

        // Commented out because this currently seems to just create a new - undeletable - slide and it's not clear why.

        //Presentation.createNewAsCurrent();
    }
    
}
