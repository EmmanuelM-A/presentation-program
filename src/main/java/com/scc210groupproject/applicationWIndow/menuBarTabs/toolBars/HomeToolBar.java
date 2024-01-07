package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * HomeToolBar
 *
 * @author madukaag
 * */
public class HomeToolBar extends JToolBar {
        private JButton newSlide, newFile, openFile, saveFile, clipboard, select, paste, settings, spellChecker, help;
        private JScrollPane recents;
        public HomeToolBar() {
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

                this.setName("Home");
                this.setFloatable(false);
        }
}
