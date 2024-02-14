package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.adapters.MouseOccupancyAdapter;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import java.io.IOException;

public class CircleElement extends ExtendedElement {
    
    private CirclePanel panel = new CirclePanel();

    @Override
    public Component asComp() { return panel; }

    public static CircleElement createEmpty() { return new CircleElement(); }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        super.writeExtended(writer);
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        super.readExtended(reader);
    }

    public CircleElement() {
        super.addInputListener(new MouseOccupancyAdapter() {
            @Override
            public void mouseEntered(Object target, InputState state) {
                panel.expanded = true;
            }

            @Override
            public void mouseExited(Object target, InputState state) {
                panel.expanded = false;
            }
        });
    }

    private static class CirclePanel extends JPanel {

        public boolean expanded = false;
        @Override
        public boolean contains(int x, int y) {
            if (expanded) 
                return super.contains(x, y);
            else
                return new Ellipse2D.Double(0.0, 0.0, super.getWidth(), super.getHeight()).contains(x, y);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setColor(super.getBackground());
            g2d.fill(new Ellipse2D.Double(0.0, 0.0, super.getWidth(), super.getHeight()));
        }
    }
}
