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
    private static Point compCords;
    /**
     * The starting point of the click to resize the screen
     */
    private static JFrame frame;
    /**
     * The title of the program
     */
    private String title;
    /**
     * The instance of the class
     */
    public static TitleBar instance;

    public TitleBar(final JFrame frame) {
        TitleBar.frame = frame;

        instance = this;
    }

    /**
     * Creates a custom title bar with the three standard buttons along with a save, undo
     * and redo button (No functionality yet onclick yet).
     *
     */
    public JPanel createTitleBar() {
        // Removes the default title bar to make way for the custom title bar
        frame.setUndecorated(true);
        frame.setResizable(true);
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

        compCords = null;

        // Handles the movement of the application window
        titleBarPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                compCords = e.getPoint();

                //frame.getComponentAt(compCords);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                compCords = null;
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        titleBarPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                int deltaX = e.getX() - compCords.x;
                int deltaY = e.getY() - compCords.y;

                if(SwingUtilities.isLeftMouseButton(e)) {
                    // Move the frame
                    Point currCords = e.getLocationOnScreen();
                    frame.setLocation(currCords.x - compCords.x, currCords.y - compCords.y);
                } else if (SwingUtilities.isLeftMouseButton(e) && isOnFrameEdge(e)) {
                    // Resize the frame
                    Dimension currSize = frame.getSize();
                    frame.setSize((int) (currSize.getWidth() + deltaX), (int) (currSize.getHeight() + deltaY));

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int cursorType = Cursor.DEFAULT_CURSOR;

                if (isOnFrameEdge(e)) {
                    cursorType = Cursor.NE_RESIZE_CURSOR;
                }

                frame.setCursor(Cursor.getPredefinedCursor(cursorType));
            }
        });

        return titleBarPanel;
    }

    private boolean isOnFrameEdge(MouseEvent e) {
        Rectangle frameBounds = frame.getBounds();
        Insets insets = frame.getInsets();
        int resizeEdgeSize = 20;

        return e.getX() >= frameBounds.width - insets.right - resizeEdgeSize &&
                e.getY() >= frameBounds.height - insets.bottom - resizeEdgeSize;
    }
}
