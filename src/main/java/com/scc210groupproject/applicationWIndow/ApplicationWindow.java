package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.contextMenu.ContextMenuPanel;
import com.scc210groupproject.applicationWIndow.menuBarTabs.MenuBarTabs;

import javax.swing.*;
import java.awt.*;

<<<<<<< HEAD
public class ApplicationWindow extends JFrame
{
=======
/**
 * This class creates the JFrame and adds the components onto the frame.
 */
public class ApplicationWindow extends JFrame {
<<<<<<< HEAD
>>>>>>> 04267b6 (Changed the layout of the files inside application window. Also added a slide manager (NOT COMPLETE).)
=======
    /**
     * Gets the dimensions of the screen the program is run on. Allows for the program dimensions
     * to be set to the size of the screen no matter the computer.
     * */
>>>>>>> 09e0396 (Added a bunch of comments to all my files explaining what things do)
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Application Window Components
     * */
    private TitleBar customTitleBar;
    private MenuBarTabs menuBarTabs;
    private ContextMenuPanel contextMenuPanel;
    private MainDisplay mainDisplay;
<<<<<<< HEAD
    private SlideManagerPanel slideManagerPanel;
<<<<<<< HEAD
    private JPanel emptySpace;

    // Colour Scheme
    private final Color backgroundColour = new Color(211, 211, 211);
<<<<<<< HEAD
    private final Color menuBarTabsColour = Color.WHITE;
=======

>>>>>>> d4d0405 (Made a menuBarPanel where I have added the menuBarTabs and TopBar (contains save, redo, undo buttons) to the panel)
    private final Color mainDisplayColour = new Color(255,255, 255);

=======
>>>>>>> 04267b6 (Changed the layout of the files inside application window. Also added a slide manager (NOT COMPLETE).)
=======
    private SlideManager slideManagerPanel;
>>>>>>> 76b45dd (Slides can now be added and traversed but there a quite few issue not fixed yet)
    public ApplicationWindow() {
<<<<<<< HEAD
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

=======
        this.setTitle("Presentation Program");
        //this.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout(40, 20));
<<<<<<< HEAD
>>>>>>> d4d0405 (Made a menuBarPanel where I have added the menuBarTabs and TopBar (contains save, redo, undo buttons) to the panel)
        this.getContentPane().setBackground(backgroundColour);
=======
        this.getContentPane().setBackground(new Color(211, 211, 211));
>>>>>>> 04267b6 (Changed the layout of the files inside application window. Also added a slide manager (NOT COMPLETE).)

        //this.customTitleBar = new TitleBar(this); // Instantiates the class and adds a custom title bar to the frame

        this.menuBarTabs = new MenuBarTabs(100, 100, Color.WHITE);
        
        this.contextMenuPanel = new ContextMenuPanel(300, 100, Color.WHITE);

<<<<<<< HEAD
        this.mainDisplayPanel = new MainDisplayPanel(100, 100, mainDisplayColour);
<<<<<<< HEAD

        this.slideManagerPanel = new SlideManagerPanel(100, 300, Color.YELLOW, mainDisplayPanel.getCurrentPresentation());
=======
        
        this.slideManagerPanel = new SlideManagerPanel(100, 180, Color.YELLOW);
>>>>>>> d4d0405 (Made a menuBarPanel where I have added the menuBarTabs and TopBar (contains save, redo, undo buttons) to the panel)
=======
        this.mainDisplay = new MainDisplay(100, 100, new Color(255,255, 255));
        
<<<<<<< HEAD
<<<<<<< HEAD
        this.slideManagerPanel = new SlideManagerPanel(this, mainDisplay);
>>>>>>> 04267b6 (Changed the layout of the files inside application window. Also added a slide manager (NOT COMPLETE).)
=======
        this.slideManagerPanel = new SlideManager(this); // Instantiates the object and adds the slide manger to the frame
>>>>>>> 76b45dd (Slides can now be added and traversed but there a quite few issue not fixed yet)
=======
        this.slideManagerPanel = new SlideManager(this); // Instantiates the class and adds the slide manger to the frame
>>>>>>> 09e0396 (Added a bunch of comments to all my files explaining what things do)

        /**
         * The adds and positions each panel/section/element/component onto the window
         * */
        this.add(menuBarTabs, BorderLayout.NORTH);
        this.add(contextMenuPanel, BorderLayout.WEST);
        //this.add(mainDisplay, BorderLayout.CENTER);

        this.setVisible(true);

    }
}
