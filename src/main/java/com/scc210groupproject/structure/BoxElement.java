package com.scc210groupproject.structure;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.contextMenu.ShapeContextMenu;
import com.scc210groupproject.ui.contextMenu.TextContextMenu;

import java.awt.Component;
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

    public BoxElement() {

        BoxElement self = this;
        super.addInputListener(new IMouseClicked() {

            @Override
            public void mouseClicked(Object target, InputState state) {
                ContextMenuPanel.setMenu(self, new ShapeContextMenu(self));
            }
            
        });
    }

    @Override
    public void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        super.readSelfExtended(reader);
    }
}
