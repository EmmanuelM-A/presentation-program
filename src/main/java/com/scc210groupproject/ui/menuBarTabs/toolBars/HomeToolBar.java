package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Queue;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * HomeToolBar
 *
 * @author madukaag
 * */
public class HomeToolBar extends ToolBar {

        private JButton newSlide, newFile, openFile, saveFile, clipboard, emptyClipboard, select, copy, paste, settings, spellChecker, help, toggleMode;


        public HomeToolBar(JPanel recentsPanel) {
                this.setRollover(true);

                recentsPanel.setBackground(Color.WHITE);
                JScrollPane recentsScrollPane = new JScrollPane(recentsPanel);
                recentsScrollPane.setPreferredSize(new Dimension(500,90));
                recentsScrollPane.setMaximumSize(new Dimension(500, 90));
                recentsScrollPane.setMinimumSize(new Dimension(100, 90));
                recentsScrollPane.setToolTipText("Recents");
                recentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE, recentsPanel);

                newFile = makeToolbarButton(GeneralButtons.NEW, recentsPanel);

                openFile = makeToolbarButton(GeneralButtons.OPEN, recentsPanel);

                saveFile = makeToolbarButton(GeneralButtons.SAVE, recentsPanel);

                clipboard = makeToolbarButton(GeneralButtons.CLIPBOARD, recentsPanel);

                select = makeToolbarButton(GeneralButtons.SELECT, recentsPanel);

                copy = makeToolbarButton(GeneralButtons.COPY, recentsPanel);

                paste = makeToolbarButton(GeneralButtons.PASTE, recentsPanel);

                settings = makeToolbarButton(GeneralButtons.SETTINGS, recentsPanel);

                spellChecker = makeToolbarButton(GeneralButtons.SPELL_CHECKER,  recentsPanel);

                help = makeToolbarButton(GeneralButtons.HELP, recentsPanel);

                toggleMode = makeToolbarButton(GeneralButtons.TOGGLE_THEME, recentsPanel);
                
                this.add(newSlide);

                separator(this);

                this.add(newFile);
                this.add(openFile);
                this.add(saveFile);

                separator(this);

                this.add(recentsScrollPane);

                separator(this);

                this.add(clipboard);
                this.add(select);
                this.add(copy);
                this.add(paste);

                separator(this);

                this.add(settings);
                this.add(spellChecker);

                separator(this);

                this.add(help);

                separator(this);

                this.add(toggleMode);

                this.setName("Home");
                this.setFloatable(false);

        }
}
