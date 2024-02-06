package com.scc210groupproject.structure.adjust;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.border.AbstractBorder;

public class SelectionBorder extends AbstractBorder
{
    private int margin = 10;
    private Rectangle[] rectangles = new Rectangle[8];

    public static enum Mode {
        FULL,
        MOVEONLY
    }

    private Mode state = Mode.FULL;

    public void setState(Mode mode) {
        state = mode;
    }

    @Override
    public Insets getBorderInsets(Component element)
    {
        return(new Insets(margin, margin, margin, margin));
    }

    @Override
    public void paintBorder(Component element, Graphics graphics, int topLeftCornerX, int topLeftCornerY, int width, int height)
    {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(topLeftCornerX + margin / 2, topLeftCornerY + margin / 2, width - margin, height - margin);

        Graphics2D graphics2d = (Graphics2D)graphics;

        rectangles[0] = new Rectangle(
            topLeftCornerX + width / 2 - margin / 2, 
            topLeftCornerY, 
            margin, margin);
        rectangles[1] = new Rectangle(
            topLeftCornerX + width - margin, 
            topLeftCornerY, 
            margin, margin);
        rectangles[2] = new Rectangle(
            topLeftCornerX + width - margin,
            topLeftCornerY + height / 2 - margin / 2, 
            margin, margin);
        rectangles[3] = new Rectangle(
            topLeftCornerX + width - margin, 
            topLeftCornerY + height - margin, 
            margin, margin);
        rectangles[4] = new Rectangle(
            topLeftCornerX + width / 2 - margin / 2, 
            topLeftCornerY + height - margin, 
            margin, margin);
        rectangles[5] = new Rectangle(
            topLeftCornerX, 
            topLeftCornerY + height - margin, 
            margin, margin);
        rectangles[6] = new Rectangle(
            topLeftCornerX, 
            topLeftCornerY + height / 2 - margin / 2, 
            margin, margin);
        rectangles[7] = new Rectangle(
            topLeftCornerX, 
            topLeftCornerY, 
            margin, margin);

        switch (state) {
            case FULL:
                graphics2d.fill(rectangles[0]);
                graphics2d.fill(rectangles[1]);
                graphics2d.fill(rectangles[2]);
                graphics2d.fill(rectangles[3]);
                graphics2d.fill(rectangles[4]);
                graphics2d.fill(rectangles[5]);
                graphics2d.fill(rectangles[6]);
                graphics2d.fill(rectangles[7]);
                break;
        
            case MOVEONLY:
                break;
        }
    }

    public int findPoint(int x, int y)
    {
        for(int i = 0; i < rectangles.length; i++)
        {
            if(rectangles[i] == null)
            {
                break;
            }
            if(rectangles[i].contains(x,y))
            {
                return i;
            }
        }
        return 8;
    }
}