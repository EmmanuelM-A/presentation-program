package com.scc210groupproject.homeDisplay;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Makes a copy of the preset a user selects on the home screen
 * and automatically saves it to a usercopy directory then opens this copy in main
 * 
 */
public class autoCopy {
  


    /**
     * 
     * @param sourceFilePath The Path of the presentation file being coppied
     * @throws IOException   Can not find valid presentation file at given directory
     */
        public static void copyPreset(String sourceFilePath) throws IOException {
            Path sourcePath = Paths.get(sourceFilePath);
            Path destinationDirectory = Paths.get("src/main/presets/userCopyDefaultFile/");
            
            String fileName = sourcePath.getFileName().toString();
            Path destinationPath = destinationDirectory.resolve(fileName);
    
            Files.copy(sourcePath, destinationPath,StandardCopyOption.REPLACE_EXISTING);
            System.out.println(sourceFilePath +destinationPath);
        }        





}