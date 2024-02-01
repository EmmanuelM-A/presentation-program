package com.scc210groupproject.structure.adjust;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;

import com.scc210groupproject.structure.helper.CoordinateUtils;
import com.scc210groupproject.structure.helper.SelectionBorder;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.ui.MainDisplayPanel;

public class DragResizer implements IMousePressed, IMouseReleased, IMouseMoved, IMouseDragged, IMouseEntered, IMouseExited, IMouseClicked {

    private Point last = null;
    private int operation = -1;
    private SelectionBorder border = null;

    @Override
    public void mousePressed(Object target, InputState state) {
        IResizable resizable = (IResizable)target;
        
        Point local = CoordinateUtils.convertSlideToLocalSpace(state.getLocationInSlide(), resizable.asBaseElement());
        operation = border.findPoint(local.x, local.y);
        System.out.println(operation);
        
    }

    @Override
    public void mouseReleased(Object target, InputState state) {

        last = null;
    }

    @Override
    public void mouseMoved(Object target, InputState state) {
        IResizable resizable = (IResizable)target;

        Point local = CoordinateUtils.convertSlideToLocalSpace(state.getLocationInSlide(), resizable.asBaseElement());

        switch (border.findPoint(local.x, local.y)) {
            
            case 0:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                break;
            case 1:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                break;
            case 2:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                break;
            case 3:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                break;
            case 4:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                break;
            case 5:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                break;
            case 6:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                break;
            case 7:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                break;
            case 8:
                MainDisplayPanel.instance.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }
    }

    @Override
    public void mouseDragged(Object target, InputState state) {
        IResizable resizable = (IResizable)target;
        
        Point global = state.getLocationInSlide();

        int changeY = last != null ? global.y - last.y : 0;
        int changeX = last != null ? global.x - last.x : 0;

        last = global;

        Point oldPosition = resizable.getLocation();
        Dimension oldSize = resizable.getSize();

        switch (operation) {

            case 0:
                if (oldSize.height - changeY > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y + changeY));
                    resizable.setSize(new Dimension(oldSize.width, oldSize.height - changeY));
                }
                break;
            case 1:
                if (oldSize.width + changeX > 20 && oldSize.height - changeY > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y + changeY));
                    resizable.setSize(new Dimension(oldSize.width + changeX, oldSize.height - changeY));
                }
                break;
            case 2:
                if (oldSize.width + changeX > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width + changeX, oldSize.height));
                }
                break;
            case 3:
                if (oldSize.width + changeX > 20 && oldSize.height + changeY > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width + changeX, oldSize.height + changeY));
                }
                break;
            case 4:
                if (oldSize.height + changeY > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width, oldSize.height + changeY));
                }
                break;
            case 5:
                if (oldSize.width - changeX > 20 && oldSize.height + changeY > 20) {
                    resizable.setLocation(new Point(oldPosition.x + changeX, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width - changeX, oldSize.height + changeY));
                }
                break;
            case 6:
                if (oldSize.width - changeX > 20) {
                    resizable.setLocation(new Point(oldPosition.x + changeX, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width - changeX, oldSize.height));
                }
                break;
            case 7:
                if (oldSize.width - changeX > 20 && oldSize.height - changeY > 20) {
                    resizable.setLocation(new Point(oldPosition.x + changeX, oldPosition.y + changeY));
                    resizable.setSize(new Dimension(oldSize.width - changeX, oldSize.height - changeY));
                }
                break;
            case 8:
                resizable.setLocation(new Point(oldPosition.x + changeX, oldPosition.y + changeY));
        }
    }

    @Override
    public void mouseEntered(Object target, InputState state) {
        IResizable resizable = (IResizable)target;

        last = null;
        border = new SelectionBorder();
        resizable.setBorder(border);
    }

    @Override
    public void mouseExited(Object target, InputState state) {
        IResizable resizable = (IResizable)target;

        resizable.setBorder(null);
    }

    @Override
    public void mouseClicked(Object target, InputState state) {
        // not used, here to block message being taken by another element
    }
}
