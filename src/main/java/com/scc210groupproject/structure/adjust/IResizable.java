package com.scc210groupproject.structure.adjust;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.border.Border;

import com.scc210groupproject.structure.BaseElement;

public interface IResizable {
    public Point getLocation();
    public Dimension getSize();

    public void setLocation(Point p);
    public void setSize(Dimension d);

    public void setBorder(Border b);
    public BaseElement asBaseElement();
}
