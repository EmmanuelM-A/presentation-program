package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.anchors.AnchorManager;


public class DraggableResizableElement extends BaseElement implements IAnchorProvider
{
    private JPanel panel;
    private SelectionBorder selectionBorder = new SelectionBorder();
    private Point clicked;
    private int operation;

    public DraggableResizableElement(JComponent content)
    {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.setOpaque(false);

        panel.add(content);
        
        panel.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent e)
            {
                panel.setBorder(selectionBorder);
            }

            public void mouseExited(MouseEvent e)
            {
                panel.setBorder(new EmptyBorder(10,10,10,10));
            }

            public void mousePressed(MouseEvent e)
            {
                clicked = e.getPoint();
                operation = selectionBorder.findPoint((int)clicked.getX(), (int)clicked.getY());
            }
        });

        panel.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseMoved(MouseEvent e)
            {
                switch(selectionBorder.findPoint((int)e.getX(), (int)e.getY())){
                    
                    case 0:
                        panel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                        break;
                    case 1:
                        panel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                        break;
                    case 2:
                        panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                        break;
                    case 3:
                        panel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                        break;
                    case 4:
                        panel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
                        break;
                    case 5:
                        panel.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                        break;
                    case 6:
                        panel.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                        break;
                    case 7:
                        panel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                        break;
                    case 8:
                        panel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                }
            }

            public void mouseDragged(MouseEvent e)
            {
                panel.setBorder(selectionBorder);

                int changeY = (int)(e.getY() - clicked.getY());
                int changeX = (int)(e.getX() - clicked.getX());

                System.out.println("changeX: "+ changeX + " changeY: " + changeY);
                System.out.println("e.getX: " + e.getX() + " e.getY: " + e.getY());
                System.out.println("clicked.getX: " + clicked.getX() + " clicked.getY: " + clicked.getY());

                switch(operation){

                    case 0:
                        if(panel.getHeight() - changeY > 20)
                        {
                            panel.setLocation(panel.getX(), panel.getY() + changeY);
                            panel.setSize(panel.getWidth(), panel.getHeight() - changeY);
                            panel.revalidate();
                        }
                        break;
                    case 1:
                        if(panel.getWidth() + changeX > 20 && panel.getHeight() - changeY > 20 )
                        {
                            panel.setLocation(panel.getX(), panel.getY() + changeY);
                            panel.setSize(panel.getWidth() + changeX, panel.getHeight() - changeY);
                            clicked = new Point(e.getX(), (int)clicked.getY());
                            panel.revalidate();
                        }
                        break;   
                    case 2:
                        if(panel.getWidth() + changeX > 20)
                        {
                            panel.setLocation(panel.getX(), panel.getY());
                            panel.setSize(panel.getWidth() + changeX, panel.getHeight());
                            clicked = new Point(e.getX(), (int)clicked.getY());
                            panel.revalidate();
                        }
                        break;
                    case 3:
                        if(panel.getWidth() + changeX > 20 && panel.getHeight() + changeY > 20)
                        {
                            panel.setLocation(panel.getX(), panel.getY());
                            panel.setSize(panel.getWidth() + changeX, panel.getHeight() + changeY);
                            clicked = new Point(e.getX(), e.getY());
                            panel.revalidate();
                        }
                        break;
                    case 4:
                        if(panel.getHeight() + changeY > 20)
                        {
                            panel.setLocation(panel.getX(), panel.getY());
                            panel.setSize(panel.getWidth(), panel.getHeight() + changeY);
                            clicked = new Point((int)clicked.getX(), e.getY());
                            panel.revalidate();
                        }
                        break;
                    case 5:
                        if(panel.getWidth() - changeX > 20 && panel.getHeight() + changeY > 20)
                        {
                            panel.setLocation(panel.getX() + changeX, panel.getY());
                            panel.setSize(panel.getWidth() - changeX, panel.getHeight() + changeY);
                            clicked = new Point((int)clicked.getX(), e.getY());
                            panel.revalidate();
                        }
                        break;   
                    case 6:
                        if(panel.getWidth() - changeX > 20)
                        {
                            panel.setLocation(panel.getX() + changeX, panel.getY());
                            panel.setSize(panel.getWidth() - changeX, panel.getHeight());
                            panel.revalidate();
                        }
                        break;
                    case 7:
                        if(panel.getWidth() - changeX > 20 && panel.getHeight() - changeY > 20 )
                        {
                            panel.setLocation(panel.getX() + changeX, panel.getY() + changeY);
                            panel.setSize(panel.getWidth() - changeX, panel.getHeight() - changeY);
                            panel.revalidate();
                        }
                        break;   
                    case 8:
                        panel.setLocation(panel.getX() + changeX, panel.getY() + changeY);
                }
                manager.onChangeSize();
            }
        });
    }

    private AnchorManager manager = new AnchorManager(
        this,
        new Point2D.Double(0.0, 0.5),
        new Point2D.Double(0.5, 1.0),
        new Point2D.Double(1.0, 0.5),
        new Point2D.Double(0.5, 0.0)
    );

    public void setLocation(Point p)
    {
        panel.setLocation(p);
    }

    public void setSize(Dimension d)
    {
        panel.setSize(d);
    }

    public void setBackground(Color color)
    {
        panel.setBackground(color);
    }

    @Override
    protected void writeSelf(Writer writer) throws IOException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeSelf'");
    }

    @Override
    protected void readSelf(Reader reader) throws IOException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSelf'");
    }

    @Override
    public Component asComp()
    {
        return panel;
    }

    @Override
    public AnchorManager getAnchorManager() {
        return manager;
    }

}
