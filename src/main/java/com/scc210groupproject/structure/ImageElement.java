package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.state.Snapshot;
import com.scc210groupproject.ui.helper.GeneralButtons;

public class ImageElement extends ExtendedElement
{
    JLabel label = new JLabel();
    BufferedImage image = null;

    private class ResizeListener extends ComponentAdapter
    {
        private void updateIcon() {
            if (image != null)
                label.setIcon(GeneralButtons.resizeIcon(image, label.getWidth(), label.getHeight()));
        }

        public void componentResized(ComponentEvent e) {
            updateIcon();
        }

        @Override
        public void componentShown(ComponentEvent e) {
            updateIcon();
        }
    }

    public ImageElement(BufferedImage loaded)
    {
        super();

        image = loaded;

        label.addComponentListener(new ResizeListener());
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
    public void writeSnapshot(Snapshot snapshot) {
        super.writeSnapshotExtended(snapshot);
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {
        
        label.addComponentListener(new ResizeListener());

        String encoded = reader.readString("image");
        if (encoded != "none") {
            ByteArrayInputStream bi = new ByteArrayInputStream(Base64.getDecoder().decode(encoded));
            image = ImageIO.read(bi);
        }

        super.readSelfExtended(reader);
    }

    @Override
    public void readSnapshot(Snapshot snapshot) {
        super.readSnapshotExtended(snapshot);
    }

    @Override
    public Component asComp() {
        return label;
    }
}
