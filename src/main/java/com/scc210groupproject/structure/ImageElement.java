package com.scc210groupproject.structure;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageElement extends DraggableResizableElement
{
        public ImageElement()
    {
        super();

        ImageIcon imageY = new ImageIcon("/home/tloxley/Year2/SCC210/scc210-2324-grp-62/src/main/resources/images/add-image.png");

        super.panel.add(new JLabel(imageY), BorderLayout.CENTER);
    }
}
