package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.action.OpenAction;
import com.scc210groupproject.action.SaveAction;
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
        private JButton newSlide, newFile, openFile, saveFile, clipboard, select, paste, settings, spellChecker, help;

        public HomeToolBar(JPanel recentsPanel) {
                this.setRollover(true);
                this.setMaximumSize(new Dimension(2000, 140));
                this.setMinimumSize(new Dimension(1000, 100));

                recentsPanel.setBackground(Color.WHITE);
                JScrollPane recentsScrollPane = new JScrollPane(recentsPanel);
                recentsScrollPane.setMaximumSize(new Dimension(500, 90));
                recentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE.getTitle(), GeneralButtons.NEW_SLIDE.getIcon(), GeneralButtons.NEW_SLIDE.getEvent(), recentsPanel);

                newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon(), GeneralButtons.NEW.getEvent(), recentsPanel);

                openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon(), GeneralButtons.OPEN.getEvent(), recentsPanel);
                //openFile.addActionListener(new OpenAction());

                saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon(), GeneralButtons.SAVE.getEvent(), recentsPanel);
                //saveFile.addActionListener(new SaveAction());

                clipboard = makeToolbarButton(GeneralButtons.CLIPBOARD.getTitle(), GeneralButtons.CLIPBOARD.getIcon(), null, recentsPanel);

                select = makeToolbarButton(GeneralButtons.SELECT.getTitle(), GeneralButtons.SELECT.getIcon(), null, recentsPanel);

                paste = makeToolbarButton(GeneralButtons.PASTE.getTitle(), GeneralButtons.PASTE.getIcon(), null, recentsPanel);

                settings = makeToolbarButton(GeneralButtons.SETTINGS.getTitle(), GeneralButtons.SETTINGS.getIcon(), null, recentsPanel);

                spellChecker = makeToolbarButton(GeneralButtons.SPELL_CHECKER.getTitle(), GeneralButtons.SPELL_CHECKER.getIcon(), null,  recentsPanel);

                help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), null, recentsPanel);

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
