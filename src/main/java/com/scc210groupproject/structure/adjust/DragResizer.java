package com.scc210groupproject.structure.adjust;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.helper.CoordinateUtils;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.structure.input.listeners.IMultRelease;
import com.scc210groupproject.structure.input.listeners.IMultSelect;
import com.scc210groupproject.structure.input.listeners.IMultiDrag;
import com.scc210groupproject.structure.state.SnapshotManager;
import com.scc210groupproject.ui.MainDisplayPanel;

public class DragResizer implements IMousePressed, IMouseReleased, IMouseMoved, IMouseDragged, IMouseEntered, IMouseExited, IMouseClicked, IMultSelect, IMultRelease, IMultiDrag {

    private int operation = -1;
    private SelectionBorder border = new SelectionBorder();
    private boolean inMultSelect = false;

    private boolean firstMove = true;

    public static HashMap<BaseElement, DragResizer> instances = new HashMap<>();

    public void setMultiSelectMode(boolean multiSelect) {
        if (multiSelect) {
            border.setState(SelectionBorder.Mode.MOVEONLY);
        }
        else {
            border.setState(SelectionBorder.Mode.FULL);
        }
        inMultSelect = multiSelect;
    }

    public DragResizer(BaseElement element) {
        instances.put(element, this);
    }

    @Override
    public void mousePressed(Object target, InputState state) {
        if (inMultSelect)
            return;
        
        IResizable resizable = (IResizable)target;
        
        Point local = CoordinateUtils.convertSlideToLocalSpace(state.getLocationInSlide(), resizable.asBaseElement());
        operation = border.findPoint(local.x, local.y);
    }

    @Override
    public void mouseReleased(Object target, InputState state) {
        firstMove = true;
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
                break;
            default:
                MainDisplayPanel.instance.setCursor(Cursor.getDefaultCursor());
                break;
        }
    }

    @Override
    public void mouseDragged(Object target, InputState state) {
        if (inMultSelect)
            return;

        if (firstMove) {
            SnapshotManager.saveState((BaseElement)target);
            firstMove = false;
        }
            
        IResizable resizable = (IResizable)target;
        Dimension delta = state.getMouseDelta();

        Point oldPosition = resizable.getLocation();
        Dimension oldSize = resizable.getSize();

        switch (operation) {

            case 0:
                if (oldSize.height - delta.height > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y + delta.height));
                    resizable.setSize(new Dimension(oldSize.width, oldSize.height - delta.height));
                }
                break;
            case 1:
                if (oldSize.width + delta.width > 20 && oldSize.height - delta.height > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y + delta.height));
                    if (state.isControlDown()) {
                        int greater = delta.width > delta.height ? delta.width : delta.height;
                        double aspect = (double)oldSize.height / (double)oldSize.width;
                        int width = oldSize.width - greater;
                        int height = (int)((double)width * aspect);
                        resizable.setSize(new Dimension(width, height));
                    }
                    else
                        resizable.setSize(new Dimension(oldSize.width + delta.width, oldSize.height - delta.height));
                }
                break;
            case 2:
                if (oldSize.width + delta.width > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width + delta.width, oldSize.height));
                }
                break;
            case 3:
                if (oldSize.width + delta.width > 20 && oldSize.height + delta.height > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width + delta.width, oldSize.height + delta.height));
                }
                break;
            case 4:
                if (oldSize.height + delta.height > 20) {
                    resizable.setLocation(new Point(oldPosition.x, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width, oldSize.height + delta.height));
                }
                break;
            case 5:
                if (oldSize.width - delta.width > 20 && oldSize.height + delta.height > 20) {
                    resizable.setLocation(new Point(oldPosition.x + delta.width, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width - delta.width, oldSize.height + delta.height));
                }
                break;
            case 6:
                if (oldSize.width - delta.width > 20) {
                    resizable.setLocation(new Point(oldPosition.x + delta.width, oldPosition.y));
                    resizable.setSize(new Dimension(oldSize.width - delta.width, oldSize.height));
                }
                break;
            case 7:
                if (oldSize.width - delta.width > 20 && oldSize.height - delta.height > 20) {
                    resizable.setLocation(new Point(oldPosition.x + delta.width, oldPosition.y + delta.height));
                    resizable.setSize(new Dimension(oldSize.width - delta.width, oldSize.height - delta.height));
                }
                break;
            case 8:
                resizable.setLocation(new Point(oldPosition.x + delta.width, oldPosition.y + delta.height));
        }
    }

    @Override
    public void mouseEntered(Object target, InputState state) {
        if (inMultSelect)
            return;
            
        IResizable resizable = (IResizable)target;
        resizable.setBorder(border);
    }

    @Override
    public void mouseExited(Object target, InputState state) {
        if (inMultSelect)
            return;
            
        MainDisplayPanel.instance.setCursor(Cursor.getDefaultCursor());

        IResizable resizable = (IResizable)target;
        resizable.setBorder(null);
    }

    @Override
    public void mouseClicked(Object target, InputState state) {
        // not used, here to block message being taken by another element
    }

    @Override
    public void multiRelease(Object target, InputState state) {
        IResizable resizable = (IResizable)target;
        resizable.setBorder(null);
        setMultiSelectMode(false);
        
        firstMove = true;
    }

    @Override
    public void multiSelect(Object target, InputState state) {
        setMultiSelectMode(true);
        IResizable resizable = (IResizable)target;
        resizable.setBorder(border);
    }

    @Override
    public void multiDrag(Object target, InputState state) {
        
        if (firstMove) {
            SnapshotManager.saveState((BaseElement)target);
            firstMove = false;
        }

        IResizable resizable = (IResizable)target;
        
        Dimension delta = state.getMouseDelta();
        Point oldPosition = resizable.getLocation();

        resizable.setLocation(new Point(oldPosition.x + delta.width, oldPosition.y + delta.height));
    }
}
