package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import java.io.IOException;

public class CircleElement extends ExtendedElement {
    
    private CirclePanel panel = new CirclePanel();

    @Override
    public Component asComp() { return panel; }

    public static BoxElement createEmpty() { return new BoxElement(); }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        super.writeExtended(writer);
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        super.readExtended(reader);
    }

    private static class CirclePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setColor(super.getBackground());
            g2d.fill(new Ellipse2D.Double(0.0, 0.0, super.getWidth(), super.getHeight()));
        }
    }
}
