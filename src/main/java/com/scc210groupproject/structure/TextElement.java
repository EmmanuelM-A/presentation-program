package com.scc210groupproject.structure;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JTextPane;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.anchors.AnchorManager;

public class TextElement extends ExtendedElement
{
    JTextPane pane = new JTextPane();
    
    public static TextElement createEmpty() { return new TextElement(); }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        writer.writeObject("manager", manager);

        Point p = pane.getLocation();
        writer.writeInt("x", p.x);
        writer.writeInt("y", p.y);

        Dimension d = pane.getSize();
        writer.writeInt("width", d.width);
        writer.writeInt("height", d.height);

        writer.writeInt("background", pane.getBackground().getRGB());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        pane = new JTextPane();

        manager = (AnchorManager)reader.readObject("manager");

        Point p = new Point();
        p.setLocation(
            reader.readDouble("x"),
            reader.readDouble("y"));
        pane.setLocation(p);

        Dimension d = new Dimension();
        d.setSize(
            reader.readDouble("width"),
            reader.readDouble("height"));
        pane.setSize(d);

        pane.setBackground(new Color(reader.readInt("background")));
    }

    @Override
    public Component asComp() {
        return pane;
    }

    public String getText() {
        return pane.getText();
    }


    public void setText(String text) {
        pane.setText(text);
        super.notifyUpdate(this);
    }

    public TextElement() {
        super();
    }
}
