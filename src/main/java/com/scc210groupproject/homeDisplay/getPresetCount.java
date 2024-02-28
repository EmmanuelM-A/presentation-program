package com.scc210groupproject.homeDisplay;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * Method to count presets in file 
 */
public class getPresetCount {

    public int  countFiles(String presetDir) {
        int amountOfFiles = 0;
        try {
            Stream<Path> files = Files.list(Paths.get("src/main/presets"+presetDir));
            Iterable<Path> iterable = files::iterator;
    
            String fileName = "";
            for (Path p: iterable) {
                fileName = p.getFileName().toString();
                if(fileName.endsWith(".pcomp"))
                    amountOfFiles++;
            }
    
            System.out.println(amountOfFiles);
    
            files.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return amountOfFiles;
    }
  }
