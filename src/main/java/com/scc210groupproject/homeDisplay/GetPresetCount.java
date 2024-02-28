package com.scc210groupproject.homeDisplay;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

import com.scc210groupproject.App;

/**
 * Method to count presets in file 
 */
public class GetPresetCount {

    public int countFiles(String presetDir) {
        int amountOfFiles = 0;
        try {
            Stream<Path> files = Files.list(Paths.get(App.class.getResource("presets/"+presetDir).toString()));
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
