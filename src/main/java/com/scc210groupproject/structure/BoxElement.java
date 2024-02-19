package com.scc210groupproject.structure;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.ui.menuBarTabs.toolBars.ElementMiniToolBar;
import com.scc210groupproject.ui.menuBarTabs.toolBars.SlideMiniToolBar;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * @author wonge1
 * Use this as a reference (dont extend from this) for elements
 */
public class BoxElement extends ExtendedElement implements MouseListener {
    
    private JPanel panel = new JPanel();

    @Override
    public Component asComp() {
        /*panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Box Clicked!");
            }
        });*/
        return panel;
    }

    public BoxElement() {
        System.out.println("Box Created!");
        //displayElementMiniToolBar(this.panel);
    }

    public static BoxElement createEmpty() { return new BoxElement(); }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        super.readSelfExtended(reader);
    }

    public void displayElementMiniToolBar(JPanel element) {
        System.out.println("Clicked Instead!");
        element.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Clicked box!");
                    JPopupMenu popupMenu = new JPopupMenu();
                    popupMenu.add(new ElementMiniToolBar());
                    popupMenu.show(element, e.getX(), e.getY());
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)) {
            System.out.println("Clicked box!");
            JPopupMenu popupMenu = new JPopupMenu();
            popupMenu.add(new ElementMiniToolBar());
            popupMenu.show(this.panel, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}
