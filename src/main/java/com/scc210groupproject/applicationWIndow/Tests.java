package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.contextMenu.ContextMenuPanel;
import com.scc210groupproject.applicationWIndow.menuBarTabs.MenuBarTabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class is used to test new features and components. NO CLASSES IMPORT THIS CLASS
 *
 * @author madukaag
 * */
public class Tests extends JFrame {
    /*GridBagConstraints gbc = new GridBagConstraints();

    int gap = 6;

    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    private MenuBarTabs menuBarTabs;
    private ContextMenuPanel contextMenuPanel;
    private MainDisplayPanel mainDisplayPanel;
    private SlideManager slideManager;

    public Tests() {
        this.setTitle("Testing Testing");
        //this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());

        this.contextMenuPanel = new ContextMenuPanel(0, 0, Color.WHITE);

        this.menuBarTabs = new MenuBarTabs(this, this.contextMenuPanel, 0, 0, Color.WHITE);

        this.mainDisplayPanel = new MainDisplayPanel(0, 0, Color.WHITE);

        this.slideManager = new SlideManager(this.mainDisplayPanel, this.mainDisplayPanel.getScaledSlide());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(0, 0, gap, 0);
        this.add(menuBarTabs, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.79;
        gbc.insets = new Insets(gap, 0, gap, gap);
        this.add(contextMenuPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.8;
        gbc.insets = new Insets(gap, gap, gap, 0);
        this.add(mainDisplayPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 0.01;
        gbc.insets = new Insets(gap, 0, 0, 0);
        this.add(slideManager.createPresentationSlider(), gbc);

        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateComponentSize();
            }
        });
    }

    /*
        TODO LIST:
        - Change the layout manager of the application window to this one and sort out positioning of components
        - Remove the sizes of each frame component/panel
        - Sort out the slide scaling/resolution - set the slide size to match the size of the main display panel
     */

    /*private void updateComponentSize()
    {
        int width = this.mainDisplayPanel.getWidth();
        int height = this.mainDisplayPanel.getHeight();

        System.out.println("Main display Width: " + width + " Height: " + height);

        this.slideManager.getCurrentSlide().setDimension(new Dimension(width, height));

        updateMainDisplaySlideSize(width, height);
    }

    private void updateMainDisplaySlideSize(int width, int height) {
        //this.mainDisplayPanel.removeAll();

        //this.slideManager.getCurrentSlide().setDiemension(new Dimension(width, height));
        BufferedImage slide = this.slideManager.getCurrentSlide().createPreview(new Dimension(width, height));

        JLabel slideImage = new JLabel(new ImageIcon(slide));
        this.mainDisplayPanel.add(slideImage);
    }

    public static void main( String[] args ) {
        new Tests();
    }*/
}
