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
        private JButton newSlide, newFile, openFile, saveFile, clipboard, select, copy, paste, settings, spellChecker, help;

        public HomeToolBar(JPanel recentsPanel) {
                this.setRollover(true);

                recentsPanel.setBackground(Color.WHITE);
                JScrollPane recentsScrollPane = new JScrollPane(recentsPanel);
                recentsScrollPane.setPreferredSize(new Dimension(500,90));
                recentsScrollPane.setMaximumSize(new Dimension(500, 90));
                recentsScrollPane.setMinimumSize(new Dimension(100, 90));
                recentsScrollPane.setToolTipText("Recents");
                recentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE.getTitle(), GeneralButtons.NEW_SLIDE.getIcon(), GeneralButtons.NEW_SLIDE.getEvent(), recentsPanel);

                newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon(), GeneralButtons.NEW.getEvent(), recentsPanel);

                openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon(), GeneralButtons.OPEN.getEvent(), recentsPanel);

                saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon(), GeneralButtons.SAVE.getEvent(), recentsPanel);

                clipboard = makeToolbarButton(GeneralButtons.CLIPBOARD.getTitle(), GeneralButtons.CLIPBOARD.getIcon(), GeneralButtons.CLIPBOARD.getEvent(), recentsPanel);

                select = makeToolbarButton(GeneralButtons.SELECT.getTitle(), GeneralButtons.SELECT.getIcon(), GeneralButtons.SELECT.getEvent(), recentsPanel);

                copy = makeToolbarButton(GeneralButtons.COPY.getTitle(), GeneralButtons.COPY.getIcon(), GeneralButtons.COPY.getEvent(), recentsPanel);

                paste = makeToolbarButton(GeneralButtons.PASTE.getTitle(), GeneralButtons.PASTE.getIcon(), GeneralButtons.PASTE.getEvent(), recentsPanel);

                settings = makeToolbarButton(GeneralButtons.SETTINGS.getTitle(), GeneralButtons.SETTINGS.getIcon(), GeneralButtons.SETTINGS.getEvent(), recentsPanel);

                spellChecker = makeToolbarButton(GeneralButtons.SPELL_CHECKER.getTitle(), GeneralButtons.SPELL_CHECKER.getIcon(), GeneralButtons.SPELL_CHECKER.getEvent(),  recentsPanel);

                help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), GeneralButtons.HELP.getEvent(), recentsPanel);

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

                this.setName("Home");
                this.setFloatable(false);

        }
}
