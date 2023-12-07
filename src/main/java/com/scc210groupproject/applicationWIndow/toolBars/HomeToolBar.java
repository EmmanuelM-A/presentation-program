package com.scc210groupproject.applicationWIndow.toolBars;

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

        newSlide = new JButton(ToolBarOptions.NEW_SLIDE.getIcon());
        newSlide.setText(ToolBarOptions.NEW_SLIDE.getTitle());
        newSlide.setFocusable(false);
        newSlide.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlide.setVerticalTextPosition(SwingConstants.BOTTOM);

        newFile = new JButton(ToolBarOptions.NEW.getIcon());
        newFile.setText(ToolBarOptions.NEW.getTitle());
        newFile.setFocusable(false);
        newFile.setHorizontalTextPosition(SwingConstants.CENTER);
        newFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        openFile = new JButton(ToolBarOptions.OPEN.getIcon());
        openFile.setText(ToolBarOptions.OPEN.getTitle());
        openFile.setFocusable(false);
        openFile.setHorizontalTextPosition(SwingConstants.CENTER);
        openFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        saveFile = new JButton(ToolBarOptions.SAVE.getIcon());
        saveFile.setText(ToolBarOptions.SAVE.getTitle());
        saveFile.setFocusable(false);
        saveFile.setHorizontalTextPosition(SwingConstants.CENTER);
        saveFile.setVerticalTextPosition(SwingConstants.BOTTOM);

        clipboard = new JButton(ToolBarOptions.CLIPBOARD.getIcon());
        clipboard.setText(ToolBarOptions.CLIPBOARD.getTitle());
        clipboard.setFocusable(false);
        clipboard.setHorizontalTextPosition(SwingConstants.CENTER);
        clipboard.setVerticalTextPosition(SwingConstants.BOTTOM);

        select = new JButton(ToolBarOptions.SELECT.getIcon());
        select.setText(ToolBarOptions.SELECT.getTitle());
        select.setFocusable(false);
        select.setHorizontalTextPosition(SwingConstants.CENTER);
        select.setVerticalTextPosition(SwingConstants.BOTTOM);

        paste = new JButton(ToolBarOptions.PASTE.getIcon());
        paste.setText(ToolBarOptions.PASTE.getTitle());
        paste.setFocusable(false);
        paste.setHorizontalTextPosition(SwingConstants.CENTER);
        paste.setVerticalTextPosition(SwingConstants.BOTTOM);

        settings = new JButton(ToolBarOptions.SETTINGS.getIcon());
        settings.setText(ToolBarOptions.SETTINGS.getTitle());
        settings.setFocusable(false);
        settings.setHorizontalTextPosition(SwingConstants.CENTER);
        settings.setVerticalTextPosition(SwingConstants.BOTTOM);

        spellChecker = new JButton(ToolBarOptions.SPELL_CHECKER.getIcon());
        spellChecker.setText(ToolBarOptions.SPELL_CHECKER.getTitle());
        spellChecker.setFocusable(false);
        spellChecker.setHorizontalTextPosition(SwingConstants.CENTER);
        spellChecker.setVerticalTextPosition(SwingConstants.BOTTOM);

        help = new JButton(ToolBarOptions.HELP.getIcon());
        help.setText(ToolBarOptions.HELP.getTitle());
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
