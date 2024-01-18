package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.scc210groupproject.structure.optionalAnchors.IAnchorListener;
import com.scc210groupproject.applicationWIndow.SlideManager;
import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.optionalAnchors.AnchorReference;

public class DraggableResizableElement extends BaseElement
{
    private JPanel panel;
    private Point screenPos;
    private Point componentPos;
    private Point offset;

    public DraggableResizableElement()
    {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                screenPos = e.getLocationOnScreen();
                offset = e.getPoint();
                componentPos = e.getPoint();
                componentPos = SwingUtilities.convertPoint(panel, componentPos, (SlideManager.slideManager.getCurrentSlide().asComp()));
            }
        });

        panel.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                Point newScreenPos = e.getLocationOnScreen();

                int changeX = (int)((newScreenPos.getX() - screenPos.getX()));
                int changeY = (int)((newScreenPos.getY() - screenPos.getY()));

                panel.setLocation((int)(componentPos.getX() + changeX - offset.getX()), (int)(componentPos.getY() + changeY - offset.getY()));


            }
        });
    }

    public void setLocation(Point p) {
        panel.setLocation(p);
    }

    public void setSize(Dimension d) {
        panel.setSize(d);
    }

    public void setBackground(Color color) {
        panel.setBackground(color);
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
    public Component asComp() { return panel; }
}
