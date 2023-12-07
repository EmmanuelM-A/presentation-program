package com.scc210groupproject.applicationWIndow.toolBars;

<<<<<<< HEAD
import com.scc210groupproject.applicationWIndow.mainDisplay.MainDisplayPanel;
import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;
import com.scc210groupproject.structure.*;

=======
>>>>>>> 105cca1 (Added some buttons to the home toolBar)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

<<<<<<< HEAD
public class HomeToolBar extends JToolBar implements ActionListener {
    private final JButton newSlideButton, btn2, btn3;
=======
public class HomeToolBar extends JToolBar {
    private JButton newSlide, newFile, openFile, saveFile, clipboard, select, paste, settings, spellChecker, help;
>>>>>>> 105cca1 (Added some buttons to the home toolBar)

    private JScrollPane recents;
    public HomeToolBar() {
<<<<<<< HEAD
        newSlideButton = new JButton("NEW SLIDE");
        btn2 = new JButton("BUTTON 2");
        btn3 = new JButton("BUTTON 3");

        this.setRollover(true);

        newSlideButton.setFocusable(false);
        newSlideButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlideButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(newSlideButton);
        newSlideButton.addActionListener(this);

        btn2.setFocusable(false);
        btn2.setHorizontalTextPosition(SwingConstants.CENTER);
        btn2.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn2);

        btn3.setFocusable(false);
        btn3.setHorizontalTextPosition(SwingConstants.CENTER);
        btn3.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(btn3);

        this.setName("Home");
        this.setFloatable(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == newSlideButton)
        {
            MainDisplayPanel.instance.getCurrentPresentation().newSlide();
        }
=======
        // Buttons
        newSlide = new JButton("New Slide");
        newFile = new JButton("New File");
        openFile = new JButton("Open File");
        saveFile = new JButton("Save File");
        clipboard = new JButton("Clipboard");
        select = new JButton("Select");
        paste = new JButton("Paste");
        settings = new JButton("Settings");
        spellChecker = new JButton("Spell Checker");
        help = new JButton("Help");

        // Scroll
        recents = new JScrollPane();

        this.setRollover(true);

        //newSlide.setText("Btn1");
        newSlide.setFocusable(false);
        newSlide.setHorizontalTextPosition(SwingConstants.CENTER);
        newSlide.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(newSlide);

        this.addSeparator();

        //newFile.setText("Btn2");
        newFile.setFocusable(false);
        newFile.setHorizontalTextPosition(SwingConstants.CENTER);
        newFile.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(newFile);

        //openFile.setText("Btn3");
        openFile.setFocusable(false);
        openFile.setHorizontalTextPosition(SwingConstants.CENTER);
        openFile.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(openFile);

        this.add(saveFile);
        this.addSeparator();

        this.add(recents);
        recents.setSize(100, 100);
        recents.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.addSeparator();

        this.add(clipboard);
        this.add(select);
        this.add(paste);
        this.addSeparator();

        this.add(settings);
        this.add(spellChecker);
        this.add(help);
>>>>>>> 105cca1 (Added some buttons to the home toolBar)
    }
}
