package com.scc210groupproject.ui.presentations;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.scc210groupproject.action.NextSlideAction;
import com.scc210groupproject.action.PrevSlideAction;
import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.SlideManager;

public class PresentationModeOne extends JFrame {
    private MainDisplayPanel presentationDisplay;

    private SlideManager slideManagerForPresentationDisplay;

    public PresentationModeOne() {
        this.setTitle("Presentation Mode");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        this.presentationDisplay = new MainDisplayPanel(super.getWidth(), super.getHeight());

        this.slideManagerForPresentationDisplay = new SlideManager(this.presentationDisplay);

        //presentationDisplay.setPreferredSize(new Dimension(super.getWidth(), super.getHeight()));

        //this.presentationDisplay.setBufferedSlideImage(MainDisplayPanel.instance.getBufferedSlideImage());

        this.slideManagerForPresentationDisplay.setSlideImages(SlideManager.slideManager.getSlideImages());

        //presentationDisplay.setInputState(false);

        slideManagerForPresentationDisplay.displaySlide(slideManagerForPresentationDisplay.getSlideImages().getFirst(), presentationDisplay);

        presentationDisplay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new NextSlideAction();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
            }
        });

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowActivated(WindowEvent arg0) {
                presentationDisplay.setInputState(false);
            }

            @Override
            public void windowClosed(WindowEvent arg0) {
                presentationDisplay.setInputState(true);
            }

            @Override
            public void windowClosing(WindowEvent arg0) {
                
            }

            @Override
            public void windowDeactivated(WindowEvent arg0) {
                presentationDisplay.setInputState(true);
            }

            @Override
            public void windowDeiconified(WindowEvent arg0) {
                presentationDisplay.setInputState(false);
            }

            @Override
            public void windowIconified(WindowEvent arg0) {
                presentationDisplay.setInputState(true);
            }

            @Override
            public void windowOpened(WindowEvent arg0) {

            }
            
        });

        // Next slide displayed on right arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
            put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "next");
        presentationDisplay.getActionMap().put("next", new NextSlideAction());

        // Previous slide displayed on left arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "previous");
        presentationDisplay.getActionMap().put("previous", new PrevSlideAction());
        
        this.add(presentationDisplay);

        setVisible(true);
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            presentationDisplay.setInputState(true);
        }
    }
     

    public void nextSlideOnClick() {
        SlideManager.slideManager.showNextSlide();
    }

    public void prevSlideOnClick() {
        SlideManager.slideManager.showPrevSlide();
    }


    // * Check if slide has actions
    // * If so get those actions
    // * On click run first action is actions
    // * then incrment to the next action
    // * If run again, run the next action in actions
    // * keep track of current action
    public void runAction() {
        if(presentationDisplay.getCurrentSlideImage().getActions().size() > 0) {
            for(Integer action : presentationDisplay.getCurrentSlideImage().getActions()) {
                System.out.println("Action " + action + " completed!");
            }
        }
    }
}
