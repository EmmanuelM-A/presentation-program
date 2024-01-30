package com.scc210groupproject.ui.menuBarTabs.toolBars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author madukaag
 */
public abstract class ToolBar extends JToolBar {

    // Represents the recents panel in the home tab
    protected static JPanel recentsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    // Default size can be changed in settings
    private int CAPACITY = 10;

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

    /**
     * Set the new capacity of the recents section (panel)
     * @param newCAPACITY The new max for the number of buttons to be stored at a time
     */
    public void setCAPACITY(int newCAPACITY) {
        this.CAPACITY = newCAPACITY;
    }

    ////////////////// Methods //////////////////

    /**
     * Creates and returns a toolbar button with its title and icon set
     * @param title The name/title of the button
     * @param icon The button's icon
     * @return JButton
     */
    protected JButton makeToolbarButton(String title, ImageIcon icon, JPanel recents) {
        JButton button = new JButton(title, icon);

        // For button focus frame colour use Look & Feel to change its focus colour
        //button.setPreferredSize(new Dimension(54, 54));
        button.setSize(new Dimension(54, 54));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addToRecents(recents, button);
            }
        });

        return button;
    }

    /**
     * Creates and adds a vertical separator to the given toolbar
     * @param toolBar A JToolBar
     */
    protected void separator(JToolBar toolBar) {
        JSeparator separator = new JSeparator();
        // REMINDER - USE LOOK AND FEEL TO CHANGE SEPARATOR COLOUR
        separator.setMaximumSize(new Dimension(1, 80));
        separator.setOrientation(JSeparator.VERTICAL);

        toolBar.addSeparator();
        toolBar.add(separator);
        toolBar.addSeparator();
    }

    /**
     * Adds (a copy of) the lastUsed button clicked to the recents JPanel (located in the home toolbar tab in a
     * JScrollPane).
     * @param recents The recents JPanel
     * @param lastUsed The last clicked button
     */
    private void addToRecents(JPanel recents, JButton lastUsed) {
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
