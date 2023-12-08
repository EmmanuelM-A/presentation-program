package com.scc210groupproject.applicationWIndow.mainDisplay;

import javax.swing.*;
import java.awt.*;
import com.scc210groupproject.structure.*;

public class MainDisplayPanel extends JPanel {

    public static MainDisplayPanel instance;

    private Presentation currentPresentation;

    public MainDisplayPanel(int width, int height, Color colour)
    {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.currentPresentation = null;
        createNewPresentation();

        instance = this;
    }

    public void createNewPresentation()
    {
        //currentPresentation = new Presentation();
        insertIntoDisplay();
    }

    private void insertIntoDisplay()
    {
        this.removeAll();
        //this.add(currentPresentation.getContainer());
        this.revalidate();
    }  

    public Presentation getCurrentPresentation()
    {
        return this.currentPresentation;
    }

    public void setCurrentPresentation(Presentation currentPresentation)
    {
        this.currentPresentation = currentPresentation;
        insertIntoDisplay();
    }

}