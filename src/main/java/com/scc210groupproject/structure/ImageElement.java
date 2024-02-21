package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class ImageElement extends ExtendedElement
{
    private JLabel label = new JLabel();
    private BufferedImage image = null;

    public ImageElement(BufferedImage loaded)
    {
        super();
        image = loaded;
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

        String encoded = "none";
        if (image != null) {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bo);
            encoded = Base64.getEncoder().encodeToString(bo.toByteArray());
        }

        writer.writeString("image", encoded);
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {

        String encoded = reader.readString("image");
        if (encoded != "none") {
            ByteArrayInputStream bi = new ByteArrayInputStream(Base64.getDecoder().decode(encoded));
            image = ImageIO.read(bi);
        }

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
