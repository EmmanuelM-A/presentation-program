package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.contextMenu.TextContextMenu;

public class TextElement extends ExtendedElement
{
    JTextPane pane;
    
    public static TextElement createEmpty() { return new TextElement(); }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        super.writeExtended(writer);

        writer.writeString("text", pane.getText());

        Font font = pane.getFont();
        writer.writeString("fontname", font.getFontName());
        writer.writeInt("fontstyle", font.getStyle());
        writer.writeInt("fontsize", font.getSize());
    }

    @Override
    public void readSelf(Reader reader) throws IOException {
        pane.setText(reader.readString("text"));
        
        Font font = new Font(
            reader.readString("fontname"), 
            reader.readInt("fontstyle"),
            reader.readInt("fontsize"));
        pane.setFont(font);

        super.readExtended(reader);
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

    public void setFontSize(int size) {
        SimpleAttributeSet attributes = new SimpleAttributeSet(pane.getParagraphAttributes());
        StyleConstants.setFontSize(attributes, size);
        applyStyle(attributes);
    }

    public void setBold(Boolean bool) {
        SimpleAttributeSet attributes = new SimpleAttributeSet(pane.getParagraphAttributes());
        StyleConstants.setBold(attributes, bool ^ this.getBold());
        applyStyle(attributes);
    }

    public void setItalic(Boolean bool) {
        SimpleAttributeSet attributes = new SimpleAttributeSet(pane.getParagraphAttributes());
        StyleConstants.setItalic(attributes, bool ^ this.getItalic());
        applyStyle(attributes);
    }

    public void setUnderline(Boolean bool) {
        SimpleAttributeSet attributes = new SimpleAttributeSet(pane.getParagraphAttributes());
        StyleConstants.setUnderline(attributes, bool ^ this.getUnderline());
        applyStyle(attributes);
    }

    public void setStrikeThrough(Boolean bool) {
        SimpleAttributeSet attributes = new SimpleAttributeSet(pane.getParagraphAttributes());
        StyleConstants.setUnderline(attributes, bool ^ this.getStrikethrough());
        applyStyle(attributes);
    }

    public void setFont(String font) {
        SimpleAttributeSet attributes = new SimpleAttributeSet(pane.getParagraphAttributes());
        StyleConstants.setFontFamily(attributes, font);
        applyStyle(attributes);
    }

    public void setAlignment(Alignment alignment) {
        SimpleAttributeSet attributes = new SimpleAttributeSet(pane.getParagraphAttributes());
        switch (alignment) {
            case LEFT:
                StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
                break;
            
            case CENTER:
                StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_CENTER);
                break;
                
            case JUSTIFIED:
                StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_JUSTIFIED);
                break;
            
            case RIGHT:
                StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_RIGHT);
                break;

            default:
                break;
        }

        applyStyle(attributes);
    }

    private void applyStyle(AttributeSet attributes) {
        StyledDocument document = pane.getStyledDocument();
        Element root = document.getDefaultRootElement();
        document.setParagraphAttributes(root.getStartOffset(), root.getEndOffset() - root.getStartOffset(), attributes, true);
        super.notifyUpdate(this);
    }

    public int getFontSize() {
        return StyleConstants.getFontSize(pane.getParagraphAttributes());
    }

    public String getFontFamily() {
        return StyleConstants.getFontFamily(pane.getParagraphAttributes());
    }

    public boolean getBold() {
        return StyleConstants.isBold(pane.getParagraphAttributes());
    }

    public boolean getItalic() {
        return StyleConstants.isItalic(pane.getParagraphAttributes());
    }

    public boolean getUnderline() {
        return StyleConstants.isUnderline(pane.getParagraphAttributes());
    }
    
    public boolean getStrikethrough() {
        return StyleConstants.isStrikeThrough(pane.getParagraphAttributes());
    }

    public static enum Alignment {
        LEFT,
        CENTER,
        JUSTIFIED,
        RIGHT,
        UNKNOWN
    }

    public Alignment getAlignment() throws Exception {
        int alignment = StyleConstants.getAlignment(pane.getParagraphAttributes());
        switch (alignment) {
            case StyleConstants.ALIGN_LEFT:
                return Alignment.LEFT;
            case StyleConstants.ALIGN_CENTER:
                return Alignment.CENTER;
            case StyleConstants.ALIGN_JUSTIFIED:
                return Alignment.JUSTIFIED;
            case StyleConstants.ALIGN_RIGHT:
                return Alignment.RIGHT;
            default:
                return Alignment.UNKNOWN;
        }
    }

    @Override
    public void destroySelf() {
        ContextMenuPanel.removeOwner(this);
    }

    public TextElement() {
        super();

        pane = new JTextPane();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attributes, 50);
        pane.setParagraphAttributes(attributes, true);

        TextElement self = this;
        super.addInputListener(new IMouseClicked() {

            @Override
            public void mouseClicked(Object target, InputState state) {
                ContextMenuPanel.setMenu(self, new TextContextMenu(self));
            }
            
        });
    }
}
