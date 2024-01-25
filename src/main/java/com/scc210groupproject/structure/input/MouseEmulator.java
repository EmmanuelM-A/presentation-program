package com.scc210groupproject.structure.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.structure.input.listeners.IMouseWheel;

public class MouseEmulator implements MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean active = false;

    private Slide currentSlide = null;
    private BaseElement draggedElement = null;
    private BaseElement currentElement = null;
    private MouseState currentState = new MouseState();

    private Point offset = new Point();
    private double scale = 1f;

    public void setTargetSlide(Slide slide) {
        currentSlide = slide;
        currentElement = null;

        tryEnableMovement();
    }

    public void setPositioning(Point offsetFromFrame, double scaleOfSlide) {
        
        offset = offsetFromFrame;
        scale = scaleOfSlide;

        tryEnableMovement();
    }

    public void clearTargetSlide() {
        currentSlide = null;
        currentElement = null;

        disableMovement();
    }

    public static class MouseState {
        protected int lastChangedButton;

        protected HashMap<Integer, Boolean> buttons = new HashMap<>();
        protected Point locationInSlide = new Point();
        protected int clickCount = 0;
        protected double wheelDelta = 0.0;

        public int getLastChangedButton() {
            return lastChangedButton;
        }

        public boolean getButtonState(int button) {
            Boolean result = buttons.get(button);

            if (result == null) {
                buttons.put(button, false);
                return false;
            }

            return result;
        }

        public Point getLocationInSlide() {
            return locationInSlide;
        }

        public int getClickCount() {
            return clickCount;
        }

        public double getWheelDelta() {
            return wheelDelta;
        }
    }

    private void tryEnableMovement() {
        if (currentSlide == null)
            return;
        
        active = true;
    }

    private void processMovement(MouseEvent event) {
        if (active == false)
            return;

        currentState.locationInSlide = new Point(
                (int) ((double) (event.getX() - offset.x) * scale),
                (int) ((double) (event.getY() - offset.y) * scale));

        BaseElement located = currentSlide != null ? currentSlide.findElmentAt(currentState.locationInSlide) : null;

        if (located != currentElement) {
            if (currentElement != null)
                currentElement.passMouseEvent(IMouseExited.class, currentElement, currentState);
                
            currentElement = located;
            
            if (currentElement != null)
                currentElement.passMouseEvent(IMouseEntered.class, currentElement, currentState);

        }
    }

    private void disableMovement() {
        if (currentElement != null) {
            currentElement.passMouseEvent(IMouseExited.class, currentElement, currentState);
            currentElement = null;
        }

        active = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        currentState.buttons.put(button, true);
        currentState.lastChangedButton = button;

        if (currentElement != null)
            currentElement.passMouseEvent(IMousePressed.class, currentElement, currentState);
            
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        currentState.buttons.put(button, false);
        currentState.lastChangedButton = button;

        if (currentElement != null) {
            currentElement.passMouseEvent(IMouseReleased.class, currentElement, currentState);
            currentElement.passMouseEvent(IMouseClicked.class, currentElement, currentState);
        }

        draggedElement = null;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        currentState.wheelDelta = e.getPreciseWheelRotation();

        if (currentElement != null)
            currentElement.passMouseEvent(IMouseWheel.class, currentElement, currentState);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        tryEnableMovement();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        disableMovement();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        processMovement(e);

        // draggedElement unset by mouseReleased
        if (draggedElement == null)
            draggedElement = currentElement;

        if (draggedElement != null)
            draggedElement.passMouseEvent(IMouseDragged.class, draggedElement, currentState);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        processMovement(e);

        if (currentElement != null)
            currentElement.passMouseEvent(IMouseMoved.class, currentElement, currentState);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // not used
    }

}
