package com.scc210groupproject.structure;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JTextPane;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
public class TextElement extends ExtendedElement
{
    JTextPane pane = new JTextPane();
    
    public static TextElement createEmpty() { return new TextElement(); }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);
        writer.writeString("text", pane.getText());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        pane.setText(reader.readString("text"));
        super.readSelfExtended(reader);
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
        pane = new JTextPane();
    }
}
