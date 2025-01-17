package com.scc210groupproject.homeDisplay;

import javax.swing.ImageIcon;
import javax.swing.JButton;
// import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.IOException;

import javax.swing.JLabel;
// import javax.swing.JWindow;
import javax.swing.SwingConstants;
// import javax.swing.filechooser.FileFilter;
// import javax.swing.filechooser.FileNameExtensionFilter;

import com.scc210groupproject.App;
import com.scc210groupproject.PresentationApp;
// import com.scc210groupproject.readwrite.FileDeserializer;
import com.scc210groupproject.ui.menuBarTabs.toolBars.ToolBar;

public class HomeScreenToolbar extends ToolBar {

    // Homebar for use on homeScreen, does not include features
    // that would not be usable without an open tab

    // private JButton loadFile;
    private JButton blankFile;
    private JLabel homeTitle;

    public HomeScreenToolbar() {

        this.setLayout(new BorderLayout());

        homeTitle = new JLabel("Presentation Program Homescreen", SwingConstants.CENTER);

        // ImageIcon loadIcon = new
        // ImageIcon(App.class.getResource("/images/open-presentation.png"));
        // loadFile = new JButton("Load Presentation ");
        // loadFile.setIcon(new ImageIcon(loadIcon.getImage().getScaledInstance(32, 32,
        // 1)));
        // loadFile.setVerticalTextPosition(SwingConstants.BOTTOM);
        // loadFile.setHorizontalTextPosition(SwingConstants.CENTER);
        // loadFile.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {

        // // Launch the App window in a new thread

        // new Thread(new Runnable() {
        // @Override
        // public void run() {
        // try {
        // FileFilter compressedFilter = new FileNameExtensionFilter("Compressed
        // Presentation File", ".pcomp");
        // JWindow fileWindow = new JWindow();
        // JFileChooser c = new JFileChooser();
        // c.setFileFilter(compressedFilter);
        // c.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // int result = c.showSaveDialog(fileWindow);
        // if (result != JFileChooser.APPROVE_OPTION)
        // return;
        // String path = c.getSelectedFile().getAbsolutePath();

        // if (result != JFileChooser.APPROVE_OPTION)
        // return;

        // try {
        // FileDeserializer.readFromPath(path);
        // } catch (IOException | ClassNotFoundException e) {
        // return;}
        // PresentationApp.main(new String[]{});

        // FileDeserializer.readFromPath(path);

        // Thread.currentThread().interrupt();
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // }
        // }
        // }).start();
        // }
        // });
        // this.add(loadFile);

        ImageIcon blankIcon = new ImageIcon(App.class.getResource("/images/new-document.png"));
        blankFile = new JButton("Create blank file");
        blankFile.setIcon(new ImageIcon(blankIcon.getImage().getScaledInstance(32, 32, 1)));
        blankFile.setVerticalTextPosition(SwingConstants.BOTTOM);
        blankFile.setHorizontalTextPosition(SwingConstants.CENTER);
        blankFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Launch the App window in a new thread

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            PresentationApp.main(new String[] {});

                            HomeDisplay.instance.dispose();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        this.add(blankFile, BorderLayout.SOUTH);

        homeTitle.setPreferredSize(new Dimension(20, 20));

        homeTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        this.add(homeTitle, BorderLayout.CENTER);
    }
}
