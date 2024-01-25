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
        private JButton newSlide, newFile, openFile, saveFile, clipboard, select, paste, settings, spellChecker, help;

        private static final JPanel recentsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        private ArrayList<JButton> recentsList = new ArrayList<>();
        private final int capcity = 10;

        public HomeToolBar() {
                this.setRollover(true);
                this.setLayout(new FlowLayout(FlowLayout.LEFT));

                //recentsPanel.setBackground(Color.GREEN);
                JScrollPane recents = new JScrollPane(recentsPanel);
                recents.setPreferredSize(new Dimension(500, 90));
                recents.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                newSlide = makeToolbarButton(GeneralButtons.NEW_SLIDE.getTitle(), GeneralButtons.NEW_SLIDE.getIcon(), recentsPanel);

                newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon(), recentsPanel);

                openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon(), recentsPanel);

                saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon(), recentsPanel);

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

        public static JPanel getRecentPanel() {
                return recentsPanel;
        }

        @Override
        public void addToRecents(JPanel recent, JButton lastUsed) {
                // Get button title and icon
                String buttonTitle = lastUsed.getText();
                ImageIcon buttonIcon = (ImageIcon) lastUsed.getIcon();

                // Make a copy of the button
                JButton copyOfButton = makeToolbarButton(buttonTitle, buttonIcon, recent);

                // If the list is full remove the oldest button
                if(recentsList.size() == capcity) {
                        JButton oldest = recentsList.get(0);
                        recentsPanel.remove(oldest);
                        recentsList.remove(0);

                        recentsPanel.repaint();
                        recentsPanel.revalidate();
                }

                /*
                        Only add the button if it doesn't already exist in the list.
                        This is done by comparing every button's text with the copyButton's
                        text to see if there is a match.
                 */
                if(recentsList.isEmpty()) {
                        recentsList.add(copyOfButton);
                        recentsPanel.add(copyOfButton);
                } else {
                        for(int i = 0; i < recentsList.size(); i++) {
                                if(!Objects.equals(recentsList.get(i).getText(), lastUsed.getText()) && !recentsList.contains(lastUsed)) {
                                        recentsList.add(copyOfButton);
                                        recentsPanel.add(copyOfButton);
                                }
                        }
                }

                // Update recents panel
                recent.repaint();
                recent.revalidate();

        }

        /*private void checkIfButtonExists(JButton button) {

        }*/
}
