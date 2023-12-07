package com.scc210groupproject.applicationWIndow.contextMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContextMenuPanel extends JPanel {
    JButton a, b, c, d, e, f, g, h;
    JLabel text = new JLabel("TEXT HERE");
    JButton n;

    JPopupMenu pm;
    public ContextMenuPanel(int width, int height, Color colour) {
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(width, height));

        /*GridBagLayout gb = new GridBagLayout();
        this.setLayout(gb);

        a = new JButton("A");
        b = new JButton("B");
        c = new JButton("C");
        d = new JButton("D");
        e = new JButton("E");
        f = new JButton("F");
        g = new JButton("G");
        h = new JButton("H");

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        //constraints.gridheight = 1;

        gb.setConstraints(a, constraints);
        this.add(a);

        constraints.gridy = 0;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        //constraints.gridheight = 1;

        gb.setConstraints(b, constraints);
        this.add(b);*/

        n = new JButton("Click");

        /*pm = new JPopupMenu("Message");

        pm.add(text);

        pm.show();*/



    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == n) {
            // create a popup menu
            pm = new JPopupMenu("Message");

            // create a label
            JLabel l = new JLabel("this is the popup menu");

            // add the label to the popup
            pm.add(l);

            // add the popup to the frame
            pm.show(f, 100, 100);
        }
    }
}
