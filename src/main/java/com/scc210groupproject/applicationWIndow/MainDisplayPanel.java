package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import com.scc210groupproject.structure.*;
import com.scc210groupproject.ui.ScaledPanel;

public class MainDisplayPanel extends JPanel {

    public static MainDisplayPanel instance;

    private ScaledPanel scaledPanel;

    private Presentation currentPresentation;

    public MainDisplayPanel(int width, int height, Color colour)
    {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));
        //this.setLayout(new GridBagLayout());
        this.setLayout(new BorderLayout());

        this.scaledPanel = new ScaledPanel();
        this.currentPresentation = null;
        createNewPresentation();

        instance = this;
    }

    public void createNewPresentation()
    {
        currentPresentation = Presentation.getOrCreate();
        insertIntoDisplay();
    }

    private void insertIntoDisplay()
    {
        this.removeAll();
        JPanel panel = (JPanel)currentPresentation.getSlideAtIndex(0).asComp();
        panel.setBackground(Color.ORANGE);
        /*panel.setPreferredSize(panel.getSize());
        panel.setMinimumSize(panel.getSize());
        panel.setMaximumSize(panel.getSize());*/

        this.add(panel);
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
