package com.scc210groupproject.structure.adjust;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.scc210groupproject.structure.helper.CoordinateUtils;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.menuBarTabs.toolBars.SlideMiniToolBar;

public class DragResizer implements IMousePressed, IMouseReleased, IMouseMoved, IMouseDragged, IMouseEntered, IMouseExited, IMouseClicked, IMultiMover {

    private int operation = -1;
    private SelectionBorder border = new SelectionBorder();

    private IResizable element;

    private boolean saveOnRelease = false;

    public void setMultiSelectMode(boolean multiSelect) {
        if (multiSelect) {
            border.setState(SelectionBorder.Mode.MOVEONLY);
        }
        else {
            border.setState(SelectionBorder.Mode.FULL);
        }
    }

    public DragResizer(IResizable element) {
        this.element = element;
    }

    @Override
    public void mousePressed(Object o, InputState state) {
        if (!state.isShiftDown() && !MultiController.contains(this)) 
            MultiController.clearAll();
        MultiController.add(this);
        
        IResizable resizable = (IResizable)element;
        
        Point local = CoordinateUtils.convertSlideToLocalSpace(state.getLocationInSlide(), resizable.asBaseElement());
        operation = border.findPoint(local.x, local.y);

        saveOnRelease = false;
    }

    @Override
    public void mouseReleased(Object o, InputState state) {
        if (saveOnRelease) {
            MultiController.endMove();
            saveOnRelease = false;
        }
    }

    @Override
    public void mouseMoved(Object o, InputState state) {
        IResizable resizable = (IResizable)element;

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
    public void mouseDragged(Object o, InputState state) {
        saveOnRelease = true;
        MultiController.moveAll(state);
    }
   
    @Override
    public void mouseEntered(Object o, InputState state) {
        IResizable resizable = (IResizable)element;
        resizable.setBorder(border);
    }

    @Override
    public void mouseExited(Object o, InputState state) {
        MainDisplayPanel.instance.setCursor(Cursor.getDefaultCursor());

        if (MultiController.contains(this))
            MultiController.evaluateAll(state.isShiftDown());
        else {
            IResizable resizable = (IResizable)element;
            resizable.setBorder(null);
        }
    }

    @Override
    public void mouseClicked(Object o, InputState state) {
        // not used, here to block message being taken by another element
    }

    public void x(Component element) {
        element.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    popupMenu.add(new SlideMiniToolBar());

                    popupMenu.show(element, e.getX(), e.getY());
                }
            }
        });
    }
     
    @Override
    public void move(InputState state) {
        IResizable resizable = (IResizable)element;
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
    public void evaluateState(boolean inSelectionStage) {
        if (!MultiController.contains(this)) {
            IResizable resizable = (IResizable)element;
            resizable.setBorder(null);
            border.setState(SelectionBorder.Mode.FULL);
        }
        else if (!MultiController.hasMultiple() && !inSelectionStage) {
            IResizable resizable = (IResizable)element;
            resizable.setBorder(null);
            border.setState(SelectionBorder.Mode.FULL);
        }
        else {
            border.setState(SelectionBorder.Mode.MOVEONLY);
        }
    }
}
