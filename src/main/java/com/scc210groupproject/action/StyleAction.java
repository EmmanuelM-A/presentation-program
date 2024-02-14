package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.TextElement;
import com.scc210groupproject.structure.TextElement.Alignment;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;

public class StyleAction implements ActionListener
{
    String type;

    public StyleAction(String type) {
        this.type = type;
    }

    interface StyleMethod {
        void applyStyle();
    }

    @Override
    public void actionPerformed(ActionEvent discard) {
        TextElement element = (TextElement)ContextMenuPanel.currentMenuOwner;

        StyleMethod method = new StyleMethod() { public void applyStyle(){

        switch (type) {
            case "BOLD":
                element.setBold(true); 
                break;
            case "ITALIC":
                element.setItalic(true);
                break;
            case "UNDERLINE":
                element.setUnderline(true);
                break;
            case "STRIKETHROUGH":
                element.setStrikeThrough(true);
                break;
            case "ALIGNLEFT":
                element.setAlignment(Alignment.LEFT);
                break;
            case "ALIGNRIGHT":
                element.setAlignment(Alignment.RIGHT);
                break;
            case "ALIGNCENTRE":
                element.setAlignment(Alignment.CENTER);
                break;
            case "JUSTIFY":
                element.setAlignment(Alignment.JUSTIFIED);
                break;
        } } };

        method.applyStyle();
    
    }

}