package com.scc210groupproject.structure;

import java.awt.Component;
import java.io.IOException;

import javax.swing.JTextPane;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.state.Snapshot;
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
    public void writeSnapshot(Snapshot snapshot) {
        super.writeSnapshotExtended(snapshot);
        snapshot.addEntry("text", pane.getText());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        pane.setText(reader.readString("text"));
        super.readSelfExtended(reader);
    }

    @Override
    public void readSnapshot(Snapshot snapshot) {
        pane.setText((String)snapshot.readEntry("text"));
        super.readSnapshotExtended(snapshot);
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
