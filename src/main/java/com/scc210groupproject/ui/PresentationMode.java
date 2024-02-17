package com.scc210groupproject.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.scc210groupproject.action.NextSlideAction;
import com.scc210groupproject.action.PrevSlideAction;
import com.scc210groupproject.ui.helper.GeneralButtons;

/*public class PresentationMode extends JFrame {
    // * Open a new frame
    // * copy the functionality of the main display panel but have so it covers the whole screen instead
    // * each slide is assigned a list of actions
    // * actions are the events that happen to the slide like animations or transisitios
    // * next slide and previous slide buttons and arrow keys as well
    // * if there are no actions for the current slide move onto the next

    // SET THE INPUT STATE BACK TO TRUE AFTER WINDOW CLOSE

    private JFrame frame;

    private boolean isFrameActive;

    private LinkedList<BufferedImage> slidesToPresent;

    private ImageIcon slideToPresent;

    public PresentationMode() {
        this.setTitle("Presentation Mode");
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.frame = this;

        this.isFrameActive = true;
        
        this.slidesToPresent = new LinkedList<>();

        populteSlidesToPresent(SlideManager.slideManager.getSlideImages());

        this.slideToPresent = new ImageIcon(this.slidesToPresent.get(0));

        this.addMouseListener(new MouseListener() {
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

        // Next slide displayed on right arrow click
        this.frame.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
            put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), "next");
        this.getActionMap().put("next", new NextSlideAction());

        Previous slide displayed on left arrow click
        presentationDisplay.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), "previous");
        presentationDisplay.getActionMap().put("previous", new PrevSlideAction());
        
        this.add(presentationDisplay);

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_F5) {
                    toggleIsFrameActive();
                    frame.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {}

            @Override
            public void keyTyped(KeyEvent arg0) {}
        });

        // Calculate slide aspect ratio and display aspect ratio
        double slideRatio = (double)slideToPresent.getIconWidth() / (double)slideToPresent.getIconHeight();
        double displayRatio = (double) super.getWidth() / (double) super.getHeight();

        // Determine the slide dimension to be used
        Dimension slideDimension = slideRatio > displayRatio ?
                new Dimension(super.getWidth(), (int) ((double) super.getWidth() / slideRatio)) :
                new Dimension((int) ((double) super.getHeight() * slideRatio), super.getHeight());

        this.getContentPane().add(
            new JLabel(GeneralButtons.resizeIcon(this.slideToPresent, (int)slideDimension.getWidth(), (int)slideDimension.getHeight()))
        );

        setVisible(true);
    }

    public boolean getIsFrameActive() {
        return this.isFrameActive;
    }

    public void toggleIsFrameActive() {
        if(this.isFrameActive)
            this.isFrameActive = false;
        else
            this.isFrameActive = true;
    }     

    public void nextSlideOnClick() {
        if(th)
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

    public void populteSlidesToPresent(LinkedList<SlideImage> list) {
        for(int i = 0; i < list.size(); i++) {
            this.slidesToPresent.add(list.get(i).getBufferedSlideImage());
        }
    }

}*/

public class PresentationMode extends JFrame {
    
    // * Open a new frame
    // * copy the functionality of the main display panel but have so it covers the whole screen instead
    // * each slide is assigned a list of actions
    // * actions are the events that happen to the slide like animations or transisitios
    // * next slide and previous slide buttons and arrow keys as well
    // * if there are no actions for the current slide move onto the next

    // SET THE INPUT STATE BACK TO TRUE AFTER WINDOW CLOSE

    //MainDisplayPanel presentationDisplay = MainDisplayPanel.instance;

    private MainDisplayPanel presentationDisplay;

    private SlideManager slideManagerForPresentationDisplay;

    public PresentationMode() {
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
