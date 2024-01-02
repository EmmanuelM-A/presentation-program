package com.scc210groupproject.applicationWIndow.menuBarTabs.toolBars;

import com.scc210groupproject.applicationWIndow.helper.GeneralButtons;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends JToolBar and contains all the buttons that will be displayed on the
 * FileToolBar
 *
 * @author madukaag
 * */
public class FileToolBar extends JToolBar {
    private JButton newFile, openFile, saveFile, saveAs, export, format, print, help;

    private JSeparator separator1, separator2, separator3;

    public FileToolBar() {
        this.setRollover(true);
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));

        separator1 = makeToolbarSeparator(Color.BLACK);
        separator2 = makeToolbarSeparator(Color.BLACK);
        separator3 = makeToolbarSeparator(Color.BLACK);

        newFile = makeToolbarButton(GeneralButtons.NEW.getTitle(), GeneralButtons.NEW.getIcon());

        openFile = makeToolbarButton(GeneralButtons.OPEN.getTitle(), GeneralButtons.OPEN.getIcon());

        saveFile = makeToolbarButton(GeneralButtons.SAVE.getTitle(), GeneralButtons.SAVE.getIcon());

        saveAs = makeToolbarButton(GeneralButtons.SAVE_AS.getTitle(), GeneralButtons.SAVE_AS.getIcon());

        export = makeToolbarButton(GeneralButtons.EXPORT.getTitle(), GeneralButtons.EXPORT.getIcon());

        format = makeToolbarButton(GeneralButtons.FORMAT.getTitle(), GeneralButtons.FORMAT.getIcon());

        print = makeToolbarButton(GeneralButtons.PRINT.getTitle(), GeneralButtons.PRINT.getIcon());

        help = makeToolbarButton(GeneralButtons.HELP.getTitle(), GeneralButtons.HELP.getIcon());

        this.add(newFile);
        this.add(openFile);
        this.add(saveFile);
        this.add(saveAs);
        this.add(this.separator1);

        this.add(export);
        this.add(format);
        this.add(print);
        this.add(this.separator2);

        this.add(help);

        this.setName("File");
        this.setFloatable(false);
    }

    /**
     * Creates and returns a toolbar button with its title and icon set
     * @param title The name/title of the button
     * @param icon The button's icon
     * @return JButton
     */
    public JButton makeToolbarButton(String title, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setText(title);

        // For button focus frame colour use Look & Feel to change its focus colour
        button.setSize(32, 32);
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setOpaque(false);
        button.setBorderPainted(false);

        return button;
    }

    public JSeparator makeToolbarSeparator(Color colour) {
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBackground(colour);
        //separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        return separator;
    }
}
