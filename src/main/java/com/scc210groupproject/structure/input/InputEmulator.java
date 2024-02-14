package com.scc210groupproject.structure.input;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.LinkedList;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.input.listeners.IMultSelect;
import com.scc210groupproject.structure.input.listeners.IMultiDrag;
import com.scc210groupproject.structure.input.listeners.IKeyPressed;
import com.scc210groupproject.structure.input.listeners.IKeyReleased;
import com.scc210groupproject.structure.input.listeners.IKeyTyped;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.structure.input.listeners.IMouseDragged;
import com.scc210groupproject.structure.input.listeners.IMouseEntered;
import com.scc210groupproject.structure.input.listeners.IMouseExited;
import com.scc210groupproject.structure.input.listeners.IMouseMoved;
import com.scc210groupproject.structure.input.listeners.IMousePressed;
import com.scc210groupproject.structure.input.listeners.IMouseReleased;
import com.scc210groupproject.structure.input.listeners.IMouseWheel;
import com.scc210groupproject.structure.input.listeners.IMultRelease;

public class InputEmulator implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

    private boolean active = false;

    private Slide currentSlide = null;
    private BaseElement draggedElement = null;
    private BaseElement focusedElement = null;
    private BaseElement currentElement = null;
    private LinkedList<BaseElement> selectedElements = new LinkedList<>();
    private InputState currentState = new InputState();

    private Point offset = new Point();
    private double scale = 1f;

    public BaseElement getDraggedElement() {
        return draggedElement;
    }

    public BaseElement getFocusedElement() {
        return focusedElement;
    }

    public BaseElement getCurrentElement() {
        return currentElement;
    }

    public LinkedList<BaseElement> getSelectedElements() {
        return selectedElements;
    }

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

    public double getScale() {
        return scale;
    }

    public void clearTargetSlide() {
        currentSlide = null;
        currentElement = null;

        disableMovement();
    }

    public static class InputState {
        protected int lastChangedButton;

        protected HashMap<Integer, Boolean> buttons = new HashMap<>();
        protected boolean altDown, metaDown, shiftDown, ctrlDown, graphDown = false;
        protected Point locationInSlide = new Point();
        protected Dimension mouseDelta = new Dimension();
        protected int clickCount = 0;
        protected double wheelDelta = 0.0;

        protected int standardKeyCode = KeyEvent.VK_UNDEFINED;
        protected int extendedKeyCode = KeyEvent.VK_UNDEFINED;
        protected char keyChar = KeyEvent.CHAR_UNDEFINED;
        protected int keyLocation = KeyEvent.KEY_LOCATION_UNKNOWN;

        public boolean isAltDown() {
            return altDown;
        }
        
        public boolean isMetaDown() {
            return metaDown;
        }
        
        public boolean isShiftDown() {
            return shiftDown;
        }
        
        public boolean isControlDown() {
            return ctrlDown;
        }
        
        public boolean isAltGraphDown() {
            return graphDown;
        }

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

        public Dimension getMouseDelta() {
            return mouseDelta;
        }

        public int getClickCount() {
            return clickCount;
        }

        public double getWheelDelta() {
            return wheelDelta;
        }

        public int getStandardKeyCode() {
            return standardKeyCode;
        }

        public int getExtendedKeyCode() {
            return extendedKeyCode;
        }

        public char getKeyChar() {
            return keyChar;
        }

        public int getKeyLocation() {
            return keyLocation;
        }
    }

    private void tryEnableMovement() {
        if (currentSlide == null)
            return;
        
        active = true;
    }

    private void updateModifier(MouseEvent event) {
        currentState.altDown = event.isAltDown();
        currentState.metaDown = event.isMetaDown();
        currentState.shiftDown = event.isShiftDown();
        currentState.ctrlDown = event.isControlDown();
        currentState.graphDown = event.isAltGraphDown();
    }

    private void processMovement(MouseEvent event) {
        if (active == false)
            return;

        Point newPosition = new Point(
            (int) ((double) (event.getX() - offset.x) * scale),
            (int) ((double) (event.getY() - offset.y) * scale));

        if (currentState.locationInSlide != null)
            currentState.mouseDelta = new Dimension(
                newPosition.x - currentState.locationInSlide.x,
                newPosition.y - currentState.locationInSlide.y);
        else
            currentState.mouseDelta = new Dimension();

        currentState.locationInSlide = newPosition;

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
        currentState.mouseDelta = new Dimension();
        currentState.locationInSlide = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        updateModifier(e);
        int button = e.getButton();
        currentState.buttons.put(button, true);
        currentState.lastChangedButton = button;

        if (currentElement == null || currentElement instanceof Slide) {
            for (BaseElement selectedElement : selectedElements)
                selectedElement.passMouseEvent(IMultRelease.class, selectedElement, currentState);
            selectedElements.clear();
        }
        else if (button == MouseEvent.BUTTON1 && e.isShiftDown()) {
            if (!selectedElements.contains(currentElement)) {
                selectedElements.add(currentElement);
                currentElement.passMouseEvent(IMultSelect.class, currentElement, currentState);
            }
        }

        if (currentElement != null)
            currentElement.passMouseEvent(IMousePressed.class, currentElement, currentState);
            
        focusedElement = currentElement;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        updateModifier(e);
        int button = e.getButton();
        currentState.buttons.put(button, false);
        currentState.lastChangedButton = button;

        if (currentElement != null) {
            currentElement.passMouseEvent(IMouseReleased.class, currentElement, currentState);
            currentElement.passMouseEvent(IMouseClicked.class, currentElement, currentState);
        }

        if (draggedElement != null) {
            draggedElement.passMouseEvent(IMouseReleased.class, draggedElement, currentState);
        }

        draggedElement = null;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        updateModifier(e);
        currentState.wheelDelta = e.getPreciseWheelRotation();

        if (currentElement != null)
            currentElement.passMouseEvent(IMouseWheel.class, currentElement, currentState);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        updateModifier(e);
        tryEnableMovement();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        updateModifier(e);
        disableMovement();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateModifier(e);
        processMovement(e);

        for (BaseElement selectedElement : selectedElements)
            selectedElement.passMouseEvent(IMultiDrag.class, selectedElement, currentState);

        // draggedElement unset by mouseReleased
        if (draggedElement == null)
            draggedElement = currentElement;

        if (draggedElement != null)
            draggedElement.passMouseEvent(IMouseDragged.class, draggedElement, currentState);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateModifier(e);
        processMovement(e);

        if (currentElement != null)
            currentElement.passMouseEvent(IMouseMoved.class, currentElement, currentState);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // not used
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentState.keyChar = e.getKeyChar();

        currentState.keyLocation = e.getKeyLocation();

        if (focusedElement != null)
            focusedElement.passMouseEvent(IKeyTyped.class, focusedElement, currentState);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentState.standardKeyCode = e.getKeyCode();
        currentState.extendedKeyCode = e.getExtendedKeyCode();

        currentState.keyLocation = e.getKeyLocation();

        if (focusedElement != null)
            focusedElement.passMouseEvent(IKeyPressed.class, focusedElement, currentState);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentState.standardKeyCode = e.getKeyCode();
        currentState.extendedKeyCode = e.getExtendedKeyCode();

        currentState.keyLocation = e.getKeyLocation();

        if (focusedElement != null)
            focusedElement.passMouseEvent(IKeyReleased.class, focusedElement, currentState);
    }
}
