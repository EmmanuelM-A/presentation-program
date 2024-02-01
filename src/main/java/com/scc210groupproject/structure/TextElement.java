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
        super.writeExtended(writer);

        writer.writeString("text", pane.getText());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        pane = new JTextPane();

        pane.setText(reader.readString("text"));

        super.readExtended(reader);
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
