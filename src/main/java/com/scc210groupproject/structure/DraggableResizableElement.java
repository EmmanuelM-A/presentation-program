package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.anchors.AnchorManager;
import com.scc210groupproject.structure.anchors.IAnchorProvider;
import com.scc210groupproject.structure.helper.SelectionBorder;
import com.scc210groupproject.structure.input.MouseEmulator.MouseState;
import com.scc210groupproject.structure.input.adapters.MouseButtonAdapter;
import com.scc210groupproject.structure.input.adapters.MouseMotionAdapter;
import com.scc210groupproject.structure.input.adapters.MouseOccupancyAdapter;


public class DraggableResizableElement extends BaseElement implements IAnchorProvider
{
    protected JPanel panel;
    private SelectionBorder selectionBorder = new SelectionBorder();
    private Point last = null;
    private int operation;

    public DraggableResizableElement()
    {
        super();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(null);
        //panel.setOpaque(true);

        BaseElement self = this;
        super.addMouseListener(new MouseButtonAdapter() {
            @Override
            public void mousePressed(MouseState state) {
                Point local = CoordinateUtils.convertSlideToLocalSpace(state.getLocationInSlide(), self);

                System.out.println(local);
                operation = selectionBorder.findPoint(local.x, local.y);
                System.out.println(operation);
            }

            @Override
            public void mouseReleased(MouseState state) {
                last = null;
            }
        });
        
        super.addMouseListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseState state) {
                Point local = CoordinateUtils.convertSlideToLocalSpace(state.getLocationInSlide(), self);

                switch(selectionBorder.findPoint(local.x, local.y)){
                    
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

            @Override
            public void mouseDragged(MouseState state) {
                Point global = state.getLocationInSlide();

                int changeY = last != null ? global.y - last.y : 0;
                int changeX = last != null ? global.x - last.x : 0;

                last = global;

                //System.out.println("changeX: "+ changeX + " changeY: " + changeY);
                System.out.println("e.getX: " + global.x + " e.getY: " + global.y);
                System.out.println("clicked.getX: " + last.x + " clicked.getY: " + last.y);

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
                            panel.revalidate();
                        }
                        break;   
                    case 2:
                        if(panel.getWidth() + changeX > 20)
                        {
                            panel.setLocation(panel.getX(), panel.getY());
                            panel.setSize(panel.getWidth() + changeX, panel.getHeight());
                            panel.revalidate();
                        }
                        break;
                    case 3:
                        if(panel.getWidth() + changeX > 20 && panel.getHeight() + changeY > 20)
                        {
                            panel.setLocation(panel.getX(), panel.getY());
                            panel.setSize(panel.getWidth() + changeX, panel.getHeight() + changeY);
                            panel.revalidate();
                        }
                        break;
                    case 4:
                        if(panel.getHeight() + changeY > 20)
                        {
                            panel.setLocation(panel.getX(), panel.getY());
                            panel.setSize(panel.getWidth(), panel.getHeight() + changeY);
                            panel.revalidate();
                        }
                        break;
                    case 5:
                        if(panel.getWidth() - changeX > 20 && panel.getHeight() + changeY > 20)
                        {
                            panel.setLocation(panel.getX() + changeX, panel.getY());
                            panel.setSize(panel.getWidth() - changeX, panel.getHeight() + changeY);
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
                self.notifyUpdate(self);
                manager.onChangeSize();
            }
        });
        
        super.addMouseListener(new MouseOccupancyAdapter() {
            @Override
            public void mouseEntered(MouseState state) {
                last = null;
                panel.setBorder(selectionBorder);
                self.notifyUpdate(self);
            }
        
            @Override
            public void mouseExited(MouseState state) {
                panel.setBorder(null);
                self.notifyUpdate(self);
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
