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

public class TextElement extends BaseElement
{
    private JPanel textPanel;
    private JTextPane textPane;
    private Point screenPos;
    private Point componentPos;
    private Point offset;

    public TextElement()
    {
        textPanel = new JPanel();
        textPane = new JTextPane();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        textPane.setText("Add Text");
        textPanel.add(textPane, BorderLayout.CENTER);

        textPanel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                screenPos = e.getLocationOnScreen();
                offset = e.getPoint();
                componentPos = e.getPoint();
                componentPos = SwingUtilities.convertPoint(textPanel, componentPos, (SlideManager.slideManager.getCurrentSlide().asComp()));
                System.out.println("Mouse pressed");
                System.out.println(screenPos);
                System.out.println(componentPos);
                System.out.println("End");
            }
        });

        textPanel.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent e){
                Point newScreenPos = e.getLocationOnScreen();
                int changeX = (int)((newScreenPos.getX() - screenPos.getX()));
                int changeY = (int)((newScreenPos.getY() - screenPos.getY()));

                System.out.println(changeX);
                System.out.println(changeY);

                System.out.println("New location:");
                System.out.println(((int)componentPos.getX() + changeX));
                System.out.println(((int)componentPos.getY() + changeY));

                System.out.println("Component position");
                System.out.println(componentPos);

                textPanel.setLocation((int)(componentPos.getX() + changeX - offset.getX()), (int)(componentPos.getY() + changeY - offset.getY()));


            }
        });
    }

    public void setLocation(Point p) {
        textPanel.setLocation(p);
    }

    public void setSize(Dimension d) {
        textPanel.setSize(d);
    }

    public void setBackground(Color color) {
        textPanel.setBackground(color);
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
    public Component asComp() { return textPanel; }
}
