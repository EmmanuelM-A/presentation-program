package com.scc210groupproject.ui.menuBarTabs.toolBars;

import javax.swing.*;

import com.scc210groupproject.ui.helper.GeneralButtons;
import com.scc210groupproject.ui.helper.ThemeAwareButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author madukaag
 */
public abstract class ToolBar extends JToolBar {

    /**
     * Represents the recents panel in the home tab
     */
    protected static JPanel recentsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    /**
     * The max number of buttons allowed in the recents panel
     */
    private final int CAPACITY = 10;

    ////////////////// Getters //////////////////

    /**
     * Gets the recents panel
     * @return The recents JPanel
     */
    public static JPanel getRecentsPanel() {
        return recentsPanel;
    }

    /**
     * Gets the capacity of the recents section
     * @return The current capacity
     */
    public int getCAPACITY() {
        return this.CAPACITY;
    }

    ////////////////// Setters //////////////////


    ////////////////// Methods //////////////////

    /**
     * Creates and returns a toolbar button with its title and icon set
     * @param title The name/title of the button
     * @param icon The button's icon
     * @return JButton
     */
    protected JButton makeToolbarButton(GeneralButtons buttonType, JPanel recents) {
        ThemeAwareButton button = new ThemeAwareButton(buttonType);

        button.setPreferredSize(new Dimension(76, 76));
        button.setMaximumSize(new Dimension(76, 76));
        button.setMinimumSize(new Dimension(54, 54));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        //button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(buttonType.getEvent());
        if(recents != null) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    addToRecents(recents, button);
                }
            });
        }

        return button;
    }

    /**
     * Creates and adds a vertical separator to the given toolbar
     * @param toolBar A JToolBar
     */
    protected void separator(JToolBar toolBar) {
        JSeparator separator = new JSeparator();

        separator.setMaximumSize(new Dimension(1, 80));
        separator.setOrientation(JSeparator.VERTICAL);
        separator.setBackground(Color.BLACK);

        toolBar.add(separator);
        toolBar.addSeparator();
        toolBar.add(separator);
    }

    /**
     * Adds (a copy of) the lastUsed button clicked to the recents JPanel (located in the home toolbar tab in a
     * JScrollPane).
     * @param recents The recents JPanel
     * @param lastUsed The last clicked button
     */
    private void addToRecents(JPanel recents, ThemeAwareButton lastUsed) {
        // Make a copy of the button
        JButton copyOfButton = makeToolbarButton(lastUsed.getButtonType(), recents);

        // If there are anymore action listeners added to the original button also add them to it's copy
        for(ActionListener listener : lastUsed.getActionListeners()) {
            copyOfButton.addActionListener(listener);
        }

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

    /**
     * Checks if the button is present in the recents panel.
     * @param recents The recents panel
     * @param button The button to check for
     * @return True if the button is present and false otherwise
     */
    private boolean containsButton(JPanel recents, JButton button) {
        for(Component component : recents.getComponents()) {
            if (component instanceof JButton && Objects.equals(((JButton) component).getText(), button.getText())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the button if found in the recents panel
     * @param recents The recents panel
     * @param button The button to check for
     * @return The button found
     */
    private JButton getButtonIfMatch(JPanel recents, JButton button) {
        for(Component component : recents.getComponents()) {
            if (component instanceof JButton && Objects.equals(((JButton) component).getText(), button.getText())) {
                return (JButton) component;
            }
        }
        return null;
    }
}
