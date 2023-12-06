package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.contextMenu.ContextMenuPanel;
import com.scc210groupproject.applicationWIndow.mainDisplay.MainDisplayPanel;
import com.scc210groupproject.applicationWIndow.menuBar.MenuBarTabs;
import com.scc210groupproject.applicationWIndow.slideManager.SlideManagerPanel;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame
{
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private MenuBarTabs menuBarTabs;
    private ContextMenuPanel contextMenuPanel;
    private MainDisplayPanel mainDisplayPanel;
    private SlideManagerPanel slideManagerPanel;
    private JPanel emptySpace;

    // Colour Scheme
    private final Color backgroundColour = new Color(211, 211, 211);
    private final Color menuBarTabsColour = Color.WHITE;
    private final Color mainDisplayColour = new Color(255,255, 255);

    public ApplicationWindow() {
        setTitle("Presentation Program");
        setSize((int)size.getWidth(), (int)size.getHeight());
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout(40, 20));

        JPanel button_panel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton button_1=new JButton("+");
        JButton button_2=new JButton("?");
        JButton button_3=new JButton("-");
        JButton button_4=new JButton("system tray");

        button_panel.add(button_1);
        button_panel.add(button_2);
        button_panel.add(button_3);
        button_panel.add(button_4);

        this.getContentPane().add(button_panel,BorderLayout.NORTH);

        this.getContentPane().setBackground(backgroundColour);

        this.menuBarTabs = new MenuBarTabs(menuBarTabsColour);
        
        this.contextMenuPanel = new ContextMenuPanel(300, 100, Color.BLUE);

        this.mainDisplayPanel = new MainDisplayPanel(100, 100, mainDisplayColour);

        this.slideManagerPanel = new SlideManagerPanel(100, 300, Color.YELLOW, mainDisplayPanel.getCurrentPresentation());

        this.emptySpace = new JPanel();
        this.emptySpace.setBackground(backgroundColour);
        this.emptySpace.setPreferredSize(new Dimension(100, 100));

        this.add(menuBarTabs, BorderLayout.NORTH);
        this.add(contextMenuPanel, BorderLayout.WEST);
        this.add(mainDisplayPanel, BorderLayout.CENTER);
        this.add(slideManagerPanel, BorderLayout.SOUTH);
        this.add(emptySpace, BorderLayout.EAST);

        setVisible(true);

    }
}
