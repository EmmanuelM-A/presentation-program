package com.scc210groupproject.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class creates a custom title bar with additional buttons included in the title bar as well as
 * the three standard buttons. It also allows for normal window actions like window repositioning or resizing.
 *
 * @author madukaag
 *
 * */
public class TitleBar {
    /**
     * Gets the location of the mouse - Important for moving the window frame.
     */
    static Point compCoords;
    public TitleBar(JFrame frame) {
        createTitleBar(frame);
    }

    /**
     * Creates a custom title bar with the three standard buttons along with a save, undo
     * and redo button (No functionality yet onclick yet).
     *
     * @param frame The frame the title bar should be added to
     */
    private static void createTitleBar(final JFrame frame) {
        /**
         * Removes the default title bar to make way for the custom title bar
         */
        frame.setUndecorated(true);
        // Create custom title bar panel
        JPanel titleBarPanel = new JPanel(new BorderLayout());
        titleBarPanel.setPreferredSize(new Dimension(1000, 35));
        titleBarPanel.setBackground(Color.GRAY);

        // Add buttons to the left side of the title bar
        JPanel leftButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtonsPanel.setOpaque(false);

        JButton button1 = new JButton("Save");
        JButton button2 = new JButton("Undo");
        JButton button3 = new JButton("Redo");

        leftButtonsPanel.add(button1);
        leftButtonsPanel.add(button2);
        leftButtonsPanel.add(button3);

        titleBarPanel.add(leftButtonsPanel, BorderLayout.WEST);

        // Add standard window buttons to the right side of the title bar
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonsPanel.setOpaque(false);

        JButton minimizeButton = new JButton("-");
        JButton maximizeRestoreButton = new JButton("□");
        JButton closeButton = new JButton("X");

        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle minimize button action
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });

        maximizeRestoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle maximize/restore button action
                int state = frame.getExtendedState();
                if ((state & JFrame.MAXIMIZED_BOTH) == 0) {
                    frame.setExtendedState(state | JFrame.MAXIMIZED_BOTH);
                } else {
                    frame.setExtendedState(state & ~JFrame.MAXIMIZED_BOTH);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle close button action
                System.exit(0);
            }
        });

        rightButtonsPanel.add(minimizeButton);
        rightButtonsPanel.add(maximizeRestoreButton);
        rightButtonsPanel.add(closeButton);

        titleBarPanel.add(rightButtonsPanel, BorderLayout.EAST);

        JLabel title = new JLabel("Presentation Program");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        titleBarPanel.add(title, BorderLayout.CENTER);

        compCoords = null;

        // Handles the movement of the application window
        titleBarPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                compCoords = e.getPoint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                compCoords = null;
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // Handles the movement of the application window
        titleBarPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                frame.setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        // Add the custom title bar to the frame
        frame.add(titleBarPanel, BorderLayout.NORTH);
    }
}
