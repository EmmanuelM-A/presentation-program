package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class is used to test new features and components. NO CLASSES IMPORT THIS CLASS
 *
 * @author madukaag
 * */
public class Tests {
    static Point compCoords;
    static JFrame frame = new JFrame();
    public Tests() {
        frame.setTitle("Testing whats and what not!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1000, 700));
        //frame.setUndecorated(true);
        //frame.setLayout(new BorderLayout());
        //createTitleBar();

        createPresentationSlider();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createTitleBar() {
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

    private static void createPresentationSlider() {
        JPanel slide = new JPanel();
        //List<JPanel> slides = new ArrayList<>();
        int currentSlideIndex = 0;

        JPanel presentationSliderPanel = new JPanel(new BorderLayout());
        presentationSliderPanel.setPreferredSize(new Dimension(1000, 180));
        presentationSliderPanel.setBackground(Color.YELLOW);

        JButton prevSlide = new JButton("<");
        prevSlide.setPreferredSize(new Dimension(30, 160));

        JButton nextSlide = new JButton(">");
        nextSlide.setPreferredSize(new Dimension(30, 160));

        JPanel mainView = new JPanel();
        mainView.setPreferredSize(new Dimension(1000, 160));
        mainView.setBackground(Color.green);

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000, 20));
        bottom.setBackground(Color.blue);

        presentationSliderPanel.add(prevSlide, BorderLayout.WEST);
        presentationSliderPanel.add(nextSlide, BorderLayout.EAST);
        presentationSliderPanel.add(mainView, BorderLayout.CENTER);
        presentationSliderPanel.add(bottom, BorderLayout.SOUTH);

        frame.add(presentationSliderPanel, BorderLayout.SOUTH);
    }

    public static void main( String[] args ) {
        new Tests();
    }
}
