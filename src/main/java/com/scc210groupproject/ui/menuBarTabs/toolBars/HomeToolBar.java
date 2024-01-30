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
                this.setMaximumSize(new Dimension(1500, 100));

                recentsPanel.setBackground(Color.WHITE);
                JScrollPane recentsScrollPane = new JScrollPane(recentsPanel);
                recentsScrollPane.setMaximumSize(new Dimension(500, 90));
                recentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE.getTitle(), GeneralButtons.NEW_SLIDE.getIcon(), recentsPanel);

                newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon(), recentsPanel);

                openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon(), recentsPanel);
                openFile.addActionListener(new OpenAction());

                saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon(), recentsPanel);
                saveFile.addActionListener(new SaveAction());

                clipboard = makeToolbarButton(GeneralButtons.CLIPBOARD.getTitle(), GeneralButtons.CLIPBOARD.getIcon(), recentsPanel);

                select = makeToolbarButton(GeneralButtons.SELECT.getTitle(), GeneralButtons.SELECT.getIcon(), recentsPanel);

                paste = makeToolbarButton(GeneralButtons.PASTE.getTitle(), GeneralButtons.PASTE.getIcon(), recentsPanel);

                settings = makeToolbarButton(GeneralButtons.SETTINGS.getTitle(), GeneralButtons.SETTINGS.getIcon(), recentsPanel);

                spellChecker = makeToolbarButton(GeneralButtons.SPELL_CHECKER.getTitle(), GeneralButtons.SPELL_CHECKER.getIcon(), recentsPanel);

                help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon(), recentsPanel);

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

        public static JPanel getRecentPanel() {
                return null;
        }

        /*@Override
        public void addToRecents(JPanel recents, JButton lastUsed) {
                // Get button title and icon
                String buttonTitle = lastUsed.getText();
                ImageIcon buttonIcon = (ImageIcon) lastUsed.getIcon();

                // Make a copy of the button
                JButton copyOfButton = makeToolbarButton(buttonTitle, buttonIcon, recents);

                // Check if the capacity has not been reached but if so remove the oldest button
                if (recents.getComponentCount() >= CAPACITY) {
                        recents.remove(recents.getComponentCount() - 1);
                }

                // If button is present in recents, remove it at its position then add it again to the start of recents.
                // If it isn't just add it to the start of recents.

                if(containsButton(recents, lastUsed)) {
                        recents.remove(getButtonIfMatch(recents, lastUsed));
                        recents.add(copyOfButton, 0);
                } else {
                        recents.add(copyOfButton, 0);
                }

                // Update recents panel
                recents.repaint();
                recents.revalidate();
        }

        private boolean containsButton(JPanel recents, JButton button) {
                for(Component component : recents.getComponents()) {
                        if (component instanceof JButton && Objects.equals(((JButton) component).getText(), button.getText())) {
                                return true;
                        }
                }
                return false;
        }

        private JButton getButtonIfMatch(JPanel recents, JButton button) {
                for(Component component : recents.getComponents()) {
                        if (component instanceof JButton && Objects.equals(((JButton) component).getText(), button.getText())) {
                                return (JButton) component;
                        }
                }
                return null;
        }*/
}
