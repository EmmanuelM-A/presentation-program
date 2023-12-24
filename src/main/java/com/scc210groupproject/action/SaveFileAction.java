package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.scc210groupproject.readwrite.FileSerializer;

public class SaveFileAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent _)
    {
        //temporary file path, should have a menu to select the path instead
        try {
            FileSerializer.writeToPath("./file");
        }
        catch (IOException e) {
            return;
        }
    }

}
