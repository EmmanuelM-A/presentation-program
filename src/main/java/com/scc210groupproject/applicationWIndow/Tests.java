package com.scc210groupproject.applicationWIndow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class is used to test new features and components. NO CLASSES IMPORT THIS CLASS
 *
 * @author madukaag
 * */
public class Tests extends JFrame {
    JPanel sectionOne, sectionTwo, sectionThree, sectionFour;

    GridBagConstraints c = new GridBagConstraints();

    public Tests() {
        this.setTitle("Testing Testing");
        this.setMinimumSize(new Dimension(1000, 700));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridBagLayout());

        sectionOne = new JPanel();
        sectionOne.setBackground(Color.YELLOW);
        sectionOne.setPreferredSize(new Dimension(1000, 100));

        sectionTwo = new JPanel();
        sectionTwo.setBackground(Color.GREEN);
        sectionTwo.setPreferredSize(new Dimension(100, 700));

        sectionThree = new JPanel();
        sectionThree.setBackground(Color.BLUE);
        sectionThree.setPreferredSize(new Dimension(900, 700));

        sectionFour = new JPanel();
        sectionFour.setBackground(Color.RED);
        sectionFour.setPreferredSize(new Dimension(1000, 100));

        /*c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;*/

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        this.add(sectionOne, c);

        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.3;
        c.weighty = 1.0;
        this.add(sectionTwo, c);

        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.7;
        c.weighty = 1.0;
        this.add(sectionThree, c);

        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 1;
        this.add(sectionFour, c);

        setVisible(true);
    }

    public static void main( String[] args ) {
        new Tests();
    }
}
