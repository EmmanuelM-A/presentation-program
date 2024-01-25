package com.scc210groupproject.structure;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.border.Border;

public interface IResizable {
    public Point getLocation();
    public Dimension getSize();

    public void setLocation(Point p);
    public void setSize(Dimension d);

    public void setBorder(Border b);
    public BaseElement asBaseElement();
}
