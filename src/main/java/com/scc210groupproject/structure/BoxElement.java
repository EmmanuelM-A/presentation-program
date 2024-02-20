package com.scc210groupproject.structure;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.ui.menuBarTabs.toolBars.ElementMiniToolBar;
import com.scc210groupproject.ui.menuBarTabs.toolBars.SlideMiniToolBar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * @author wonge1
 * Use this as a reference (dont extend from this) for elements
 */
public class BoxElement extends ExtendedElement {
    
    private JPanel panel = new JPanel();

    @Override
    public Component asComp() { return panel; }

    public static BoxElement createEmpty() { return new BoxElement(); }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        super.readSelfExtended(reader);
    }
}
