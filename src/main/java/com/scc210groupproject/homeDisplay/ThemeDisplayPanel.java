package com.scc210groupproject.homeDisplay;

import java.awt.BorderLayout;
// import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ThemeDisplayPanel extends HomeContentPanel {

    private String presetDirectory = "/presets/themes";
    private JButton[] themeButtons;
    private JButton setThemesDir;
    private JPanel themeButtonPanel, mainPanel;

    public ThemeDisplayPanel() {
        this.setLayout(new BorderLayout());
        themeButtonPanel = new JPanel();
        mainPanel = new JPanel();

        themeButtons = CreatePresetButtonArray.createJButtonArray(presetDirectory);

        setThemesDir = new JButton("Change Theme Directory ");
        setThemesDir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeThemeDir(mainPanel);
            }
        });

        // themeButtonPanel.add(setThemesDir);
        this.add(themeButtonPanel, BorderLayout.NORTH);

        for (JButton jButton : themeButtons) {
            mainPanel.add(jButton);
        }

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(scroll, BorderLayout.CENTER);
    }

    /**
     * @param contentPanel Panel to update
     */
    private void changeThemeDir(JPanel contentPanel) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        /////////
        // check powerpoint files are present in directory
        /////////

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();

            presetDirectory = selectedFolder.getAbsolutePath();
            File checkDir = new File(presetDirectory);

            // Only call if directoty has valid files else do not redraw panel
            if (CheckForPreset.hasValidFiles(checkDir)) {
                contentPanel.removeAll();
                themeButtons = CreatePresetButtonArray.createJButtonArray(presetDirectory);
                contentPanel.setLayout(new GridLayout(themeButtons.length / 2, 5));
                for (JButton button : themeButtons) {
                    contentPanel.add(button);
                }

                // repaint
                contentPanel.revalidate();
                contentPanel.repaint();
                this.setVisible(true);

            } else {
                NoFilesPopupWarning.noFilesPopup("Unable to find presets in selected directory", "No Presets Present");
            }
        } // Return to showing default content directory if no files in new directory
    }
}
