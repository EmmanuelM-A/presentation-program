package com.scc210groupproject.ui.helper;

import java.awt.*;

/**
 * This enum contains all the colours that will be used in the program. Will be used in the application window
 * settings.
 *
 * MORE COLOURS WILL BE ADDED!
 * */
public enum ColourPalette {
    BACKGROUND_COLOUR(new Color(211, 211, 211));
    private final Color colour;
    private ColourPalette(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return this.colour;
    }
}
