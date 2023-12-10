package com.scc210groupproject.applicationWIndow.helper;

import java.awt.*;
public enum ColourScheme {
    BACKGROUND_COLOUR(new Color(211, 211, 211));
    private final Color colour;
    private ColourScheme(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return this.colour;
    }
}
