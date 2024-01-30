package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
        this.image = image;

        JLabel jLabel = new JLabel(this.image);

        jLabel.setPreferredSize(new Dimension(400, 400));

        System.out.println(panel.getWidth() + panel.getHeight());

        panel.add(jLabel, BorderLayout.CENTER);

        class ResizeListener extends ComponentAdapter
        {
            public void componentResized(ComponentEvent e)
            {
                ImageElement.this.image = GeneralButtons.resizeIcon(image, panel.getWidth(), panel.getHeight());
                ImageElement.this.panel.removeAll();
                JLabel jLabel = new JLabel(ImageElement.this.image);
                jLabel.setPreferredSize(new Dimension(400, 400));
                ImageElement.this.panel.add(jLabel, BorderLayout.CENTER);
                ImageElement.this.panel.revalidate();
            }
        }

        panel.addComponentListener(new ResizeListener());
    }
}
