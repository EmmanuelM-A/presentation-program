package com.scc210groupproject.homeDisplay;

import java.io.File;

public class CheckForPreset {
    public static boolean hasValidFiles(File directory) {
        File[] recognisedFileType = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".pcomp"));

        if (recognisedFileType != null && recognisedFileType.length > 0) {
            return true;
        } else {
            return false;
        }
    }
}
