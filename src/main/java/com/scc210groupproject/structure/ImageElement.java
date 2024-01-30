package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class ImageElement extends ExtendedElement
{
    JLabel label = new JLabel();

    public ImageElement(ImageIcon image)
    {
        super();

        label.setIcon(image);

        class ResizeListener extends ComponentAdapter
        {
            public void componentResized(ComponentEvent e)
            {
                label.setIcon(GeneralButtons.resizeIcon((ImageIcon)label.getIcon(), label.getWidth(), label.getHeight()));
            }
        }

        label.addComponentListener(new ResizeListener());
    }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeSelf'");
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSelf'");
    }

    @Override
    public Component asComp() {
        return label;
    }
}
