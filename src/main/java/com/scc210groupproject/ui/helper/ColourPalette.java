package com.scc210groupproject.ui.helper;

import java.awt.Color;

import com.scc210groupproject.ui.Settings;

public class ColourPalette {
   
    public static ColourPalette instance;

    private Color BACKGROUD_COLOUR = Settings.instance.getIsLightMode() == true ? new Color(211, 211, 211) : new Color(000, 000, 000);

    private Color MENUBAR_COLOUR = Settings.instance.getIsLightMode() == true ? new Color(255, 255, 255) : new Color(000, 000, 000);

    private Color CONTEXT_MENU_COLOUR = Settings.instance.getIsLightMode() == true ? new Color(255, 255, 255) : new Color(000, 000, 000);

    private Color MAIN_DISPLAY_COLOUR = Settings.instance.getIsLightMode() == true ? new Color(255, 211, 211) : new Color(000, 000, 000);

    private Color SLIDE_VIEWER_COLOUR = Settings.instance.getIsLightMode() == true ? new Color(211, 211, 211) : new Color(000, 000, 000);

    public ColourPalette() {
        instance = this;
    }

    public Color getBackgroundColour() {
        return this.BACKGROUD_COLOUR;
    }

    public Color getMenuBarColour() {
        return this.MENUBAR_COLOUR;
    }

    public Color getContextMenuColour() {
        return this.CONTEXT_MENU_COLOUR;
    }

    public Color getMainDisplayColour() {
        return this.MAIN_DISPLAY_COLOUR;
    }

    public Color getSlideViewerColour() {
        return this.SLIDE_VIEWER_COLOUR;
    }

    public void setDefaultColourPalette() {
        
    }   


}
