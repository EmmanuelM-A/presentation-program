package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.ui.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * HomeToolBar
 *
 * @author madukaag
 * */
public class HomeToolBar extends ToolBar {
        private JButton newSlide, newFile, openFile, saveFile, clipboard, select, paste, settings, spellChecker, help;
        private JScrollPane recents;

        private JPanel recentsPanel = new JPanel();
        public HomeToolBar() {
                this.setRollover(true);

                newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE.getTitle(), GeneralButtons.NEW_SLIDE.getIcon());

                newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon());

                openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon());

                saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon());

                clipboard = makeToolbarButton(GeneralButtons.CLIPBOARD.getTitle(), GeneralButtons.CLIPBOARD.getIcon());

                select = makeToolbarButton(GeneralButtons.SELECT.getTitle(), GeneralButtons.SELECT.getIcon());

                paste = makeToolbarButton(GeneralButtons.PASTE.getTitle(), GeneralButtons.PASTE.getIcon());

                settings = makeToolbarButton(GeneralButtons.SETTINGS.getTitle(), GeneralButtons.SETTINGS.getIcon());

                spellChecker = makeToolbarButton(GeneralButtons.SPELL_CHECKER.getTitle(), GeneralButtons.SPELL_CHECKER.getIcon());

                help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon());

                recentsPanel.setBackground(Color.GREEN);
                recents = new JScrollPane(recentsPanel);
                recents.setPreferredSize(new Dimension(300, 100));
                recents.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                this.add(newSlide);

                separator(this);

                this.add(newFile);
                this.add(openFile);
                this.add(saveFile);

                separator(this);

                this.add(recents);

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
