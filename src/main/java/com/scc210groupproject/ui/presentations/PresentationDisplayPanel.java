package com.scc210groupproject.ui.presentations;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.eventListeners.IUpdateSlideListener;
import com.scc210groupproject.structure.input.InputEmulator;
import com.scc210groupproject.structure.state.SnapshotManager;
import com.scc210groupproject.ui.SlideImage;

/**
 * This class is just a copy of the Main Display Panel but is only used for presentation mode
 */
public class PresentationDisplayPanel extends JPanel implements IUpdateSlideListener 
{
    // The slideImage to be displayed
    private SlideImage currentSlideImage;

    // The corresponding bufferedSlideImage for the slideImage
    private BufferedImage bufferedSlideImage;

    // The main display panel
    public static PresentationDisplayPanel instance;

    // The current presentation being viewed/used in the program
    private Presentation currentPresentation;
    
    private InputEmulator emulator;

    private Dimension slideDimension;
    
    private Point newOffset;

    public InputEmulator getInputEmulator()
    {
        return emulator;
    }

    public PresentationDisplayPanel()
    {
        emulator = new InputEmulator();
        setInputState(true);
        
        super.setFocusable(true);

        super.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "undo");
        super.getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnapshotManager.restorePriorState();
            }
        });
        
        super.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()), "redo");
        super.getActionMap().put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnapshotManager.restoreAfterState();
            }
        });
        
        super.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        super.getActionMap().put("delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputEmulator emulator = InputEmulator.instance;
                if (emulator == null)
                    return;
                
                BaseElement element = emulator.getFocusedElement();
                if (element == null)
                    return;

                element.destroy();
            }
        });


        Presentation.addUpdateSlideListener(this);

        super.setLayout(new GridLayout(0, 1));

        this.currentPresentation = null;


        
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                updateBufferedSlideImage();
            }

            @Override
            public void componentMoved(ComponentEvent componentEvent) {

            }

            @Override
            public void componentShown(ComponentEvent componentEvent) {

            }

            @Override
            public void componentHidden(ComponentEvent componentEvent) {

            }
        });

        instance = this;
    }

    /**
     * Gets the slideImage currently being displayed
     * @return SlideImage
     */
    public SlideImage getCurrentSlideImage() {
        return currentSlideImage;
    }

    /**
     * Gets the bufferedSlideImage that is painted
     * @return BufferImage
     */
    public BufferedImage getBufferedSlideImage() {
        return bufferedSlideImage;
    }

    /**
     * Gets the current presentation
     * @return Presentation
     */
    public Presentation getCurrentPresentation()
    {
        return this.currentPresentation;
    }

    /**
     * Allows you to change the slideImage as well as set the bufferedSlideImage.
     * @param newslideImage The newSlideImage you wish to change to
     */
    public void setCurrentSlideImage(SlideImage newslideImage) {
            
        Slide newSlide = newslideImage != null ? newslideImage.getSlide() : null;
        Slide currentSlide = currentSlideImage != null ? currentSlideImage.getSlide() : null;
        if (newSlide != currentSlide)
            emulator.setTargetSlide(newSlide);

        currentSlideImage = newslideImage;
        setBufferedSlideImage(currentSlideImage != null ? currentSlideImage.getBufferedSlideImage() : null);
    }

    /**
     * Allows you to change the bufferedSlideImage being painted to the display.
     * @param newBufferedSlideImage The bufferImage you wish to paint
     */
    public void setBufferedSlideImage(BufferedImage newBufferedSlideImage) {
        this.bufferedSlideImage = newBufferedSlideImage;
    }

    /**
     * Resizes the current slide image being displayed so that it grows and shrinks proportionally to the size of the frame.
     * The offset of the image is also maintained as frame changes size.
     */
    public void updateBufferedSlideImage() {

        if (currentSlideImage == null)
            return;

        Component slideComp = currentSlideImage.getSlide().asComp();

        // Get the dimensions of the display
        int width = this.getWidth();
        int height = this.getHeight();

        if (width > 0 && height > 0) {
            updateBufferedSlideImage(currentSlideImage, width, height);
            
            emulator.setPositioning(newOffset, (double)slideComp.getWidth() / (double)slideDimension.width);

            repaint();
        }
    }

    public void updateBufferedSlideImage(SlideImage slideImage, int width, int height) {

        Component slideComp = slideImage.getSlide().asComp();

        // Calculate slide aspect ratio and display aspect ratio
        double slideRatio = (double)slideComp.getWidth() / (double)slideComp.getHeight();
        double displayRatio = (double) width / (double) height;

        // Determine the slide dimension to be used
        slideDimension = slideRatio > displayRatio ?
                new Dimension(width, (int) ((double) width / slideRatio)) :
                new Dimension((int) ((double) height * slideRatio), height);

        // Calculate and set the new offset
        newOffset = new Point(
                (super.getWidth() - slideDimension.width) / 2,
                (super.getHeight() - slideDimension.height) / 2);
        slideImage.setOffset(newOffset);

        // Set the new bufferedSlideImage to the newly created buffer image with the new slide dimensions
        slideImage.setBufferedSlideImage(slideImage.getSlide().createPreview(slideDimension));

        // Calculate and set the new scale
        double newScale = (double)bufferedSlideImage.getWidth() / (double) slideDimension.width;
        slideImage.setScale(newScale);
    }

    public void setInputState(boolean truthy) {
        if(truthy) {
            super.addMouseListener(emulator);
            super.addMouseMotionListener(emulator);
            super.addMouseWheelListener(emulator);
            super.addKeyListener(emulator);
        } else {
            super.removeMouseListener(emulator);
            super.removeMouseMotionListener(emulator);
            super.removeMouseWheelListener(emulator);
            super.removeKeyListener(emulator);
        }
    }

    /**
     * Removes the painted image from display
     */
    public void clearPaintedSlide() {
        bufferedSlideImage = null;
        if (currentSlideImage != null)
            currentSlideImage.setSlide(null);

        super.repaint();
    }

    @Override
    public void onUpdateSlide(int index, Slide slide) {
        updateBufferedSlideImage();
    }
}
