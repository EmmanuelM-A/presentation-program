package com.scc210groupproject.applicationWIndow.menuBar.toolBars;

<<<<<<< HEAD:src/main/java/com/scc210groupproject/applicationWIndow/toolBars/HomeToolBar.java
<<<<<<< HEAD
<<<<<<< HEAD
import com.scc210groupproject.applicationWIndow.mainDisplay.MainDisplayPanel;
import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;
import com.scc210groupproject.structure.*;
=======
import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;
import javax.tools.Tool;
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
=======
import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;
>>>>>>> 04267b6 (Changed the layout of the files inside application window. Also added a slide manager (NOT COMPLETE).):src/main/java/com/scc210groupproject/applicationWIndow/menuBar/toolBars/HomeToolBar.java

=======
>>>>>>> 105cca1 (Added some buttons to the home toolBar)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

<<<<<<< HEAD
public class HomeToolBar extends JToolBar implements ActionListener {
    private final JButton newSlideButton, btn2, btn3;
=======
public class HomeToolBar extends JToolBar {
    private JButton newSlide, newFile, openFile, saveFile, clipboard, select, paste, settings, spellChecker, help;
<<<<<<< HEAD
>>>>>>> 105cca1 (Added some buttons to the home toolBar)

    private JScrollPane recents;
    public HomeToolBar() {
<<<<<<< HEAD
        newSlideButton = new JButton("NEW SLIDE");
        btn2 = new JButton("BUTTON 2");
        btn3 = new JButton("BUTTON 3");

        this.setRollover(true);

        newSlideButton.setFocusable(false);
        newSlideButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlideButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(newSlideButton);
        newSlideButton.addActionListener(this);

        btn2.setFocusable(false);
        btn2.setHorizontalTextPosition(SwingConstants.CENTER);
        btn2.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn2);

        btn3.setFocusable(false);
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn3);

        this.setName("Home");
        this.setFloatable(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == newSlideButton)
        {
            MainDisplayPanel.instance.getCurrentPresentation().newSlide();
        }
=======
        // Buttons
        newSlide = new JButton("New Slide");
        newFile = new JButton("New File");
        openFile = new JButton("Open File");
        saveFile = new JButton("Save File");
        clipboard = new JButton("Clipboard");
        select = new JButton("Select");
        paste = new JButton("Paste");
        settings = new JButton("Settings");
        spellChecker = new JButton("Spell Checker");
        help = new JButton("Help");

        // Scroll
        recents = new JScrollPane();

=======
    private JScrollPane recents;
    public HomeToolBar() {
>>>>>>> 157d6c4 (Added more buttons to each toolbar)
        this.setRollover(true);

        newSlide = new JButton(GeneralButtons.NEW_SLIDE.getIcon());
        newSlide.setText(GeneralButtons.NEW_SLIDE.getTitle());
        newSlide.setFocusable(false);
        newSlide.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlide.setVerticalTextPosition(SwingConstants.BOTTOM);

        newFile = new JButton(GeneralButtons.NEW.getIcon());
        newFile.setText(GeneralButtons.NEW.getTitle());
        newFile.setFocusable(false);
        newFile.setHorizontalTextPosition(SwingConstants.CENTER);
        newFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        openFile = new JButton(GeneralButtons.OPEN.getIcon());
        openFile.setText(GeneralButtons.OPEN.getTitle());
        openFile.setFocusable(false);
        openFile.setHorizontalTextPosition(SwingConstants.CENTER);
        openFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        saveFile = new JButton(GeneralButtons.SAVE.getIcon());
        saveFile.setText(GeneralButtons.SAVE.getTitle());
        saveFile.setFocusable(false);
        saveFile.setHorizontalTextPosition(SwingConstants.CENTER);
        saveFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        clipboard = new JButton(GeneralButtons.CLIPBOARD.getIcon());
        clipboard.setText(GeneralButtons.CLIPBOARD.getTitle());
        clipboard.setFocusable(false);
        clipboard.setHorizontalTextPosition(SwingConstants.CENTER);
        clipboard.setVerticalTextPosition(SwingConstants.BOTTOM);

        select = new JButton(GeneralButtons.SELECT.getIcon());
        select.setText(GeneralButtons.SELECT.getTitle());
        select.setFocusable(false);
        select.setHorizontalTextPosition(SwingConstants.CENTER);
        select.setVerticalTextPosition(SwingConstants.BOTTOM);

        paste = new JButton(GeneralButtons.PASTE.getIcon());
        paste.setText(GeneralButtons.PASTE.getTitle());
        paste.setFocusable(false);
        paste.setHorizontalTextPosition(SwingConstants.CENTER);
        paste.setVerticalTextPosition(SwingConstants.BOTTOM);

        settings = new JButton(GeneralButtons.SETTINGS.getIcon());
        settings.setText(GeneralButtons.SETTINGS.getTitle());
        settings.setFocusable(false);
        settings.setHorizontalTextPosition(SwingConstants.CENTER);
        settings.setVerticalTextPosition(SwingConstants.BOTTOM);

        spellChecker = new JButton(GeneralButtons.SPELL_CHECKER.getIcon());
        spellChecker.setText(GeneralButtons.SPELL_CHECKER.getTitle());
        spellChecker.setFocusable(false);
        spellChecker.setHorizontalTextPosition(SwingConstants.CENTER);
        spellChecker.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(GeneralButtons.HELP.getIcon());
        help.setText(GeneralButtons.HELP.getTitle());
        help.setFocusable(false);
        help.setHorizontalTextPosition(SwingConstants.CENTER);
        help.setVerticalTextPosition(SwingConstants.BOTTOM);

        recents = new JScrollPane();
        recents.setSize(100, 100);
        recents.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(newSlide);
        this.addSeparator();

        this.add(newFile);
        this.add(openFile);

        this.add(saveFile);
        this.addSeparator();

        this.add(recents);
        this.addSeparator();

        this.add(clipboard);
        this.add(select);
        this.add(paste);
        this.addSeparator();

        this.add(settings);
        this.add(spellChecker);
        this.addSeparator();

        this.add(help);
>>>>>>> 105cca1 (Added some buttons to the home toolBar)
    }
}
