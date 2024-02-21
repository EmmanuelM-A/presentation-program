package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class ImageElement extends ExtendedElement
{
    private JLabel label = new JLabel();
    private File file = null;
    private BufferedImage image = null;

    public ImageElement(File loaded)
    {
        super();
        file = loaded;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {}
    }

    @Override
    public void setSize(Dimension d) {
        if (d.width > 0 && d.height > 0)
            label.setIcon(GeneralButtons.resizeIcon(image, d.width, d.height));
        super.setSize(d);
    }

    private ImageElement() {}

    public static ImageElement createEmpty() { return new ImageElement(); }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        
        super.writeSelfExtended(writer);

        writer.writeFile("image", file);
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {

        file = reader.readFile("image");
        image = ImageIO.read(file);

        super.readSelfExtended(reader);

        Dimension size = super.getSize();
        if (size.width > 0 && size.height > 0)
            label.setIcon(GeneralButtons.resizeIcon(image, size.width, size.height));
    }

    @Override
    public Component asComp() {
        return label;
    }
}
