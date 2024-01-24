package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.scc210groupproject.ui.helper.GeneralButtons;

public class ImageElement extends DraggableResizableElement
{
    private ImageIcon image;

    public ImageElement(ImageIcon image)
    {
        panel.add(new JLabel(image), BorderLayout.CENTER);

        class ResizeListener extends ComponentAdapter
        {
            public void componentResized(ComponentEvent e)
            {
                ImageElement.this.image = GeneralButtons.resizeIcon(image, panel.getWidth(), panel.getHeight());
                ImageElement.this.panel.removeAll();
                ImageElement.this.panel.add(new JLabel(image), BorderLayout.CENTER);
                ImageElement.this.panel.revalidate();
            }
        }

        panel.addComponentListener(new ResizeListener());
    }
}
