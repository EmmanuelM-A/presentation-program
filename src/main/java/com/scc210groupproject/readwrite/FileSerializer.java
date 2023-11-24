package com.scc210groupproject.readwrite;

import com.scc210groupproject.structure.Presentation;

import java.io.*;

/**
 * @author wonge1
 * Class to Serialize the Presentation
 */
public class FileSerializer {

    /**
     * Write a presentation to a given path using
     * @see FileSerializer#Serialize(FileOutputStream, Presentation)
     * @param path file path to use
     * @param presentation item to save to file
     * @throws IOException thrown if FileOutputStream and/or ObjectOutputStream failed to start
     * @throws FileNotFoundException thrown if path is a directory (or any other reason by)
     * @see FileOutputStream
     */
    public static void WriteToPath(String path, Presentation presentation) throws IOException, FileNotFoundException {
        try (FileOutputStream fileStream = new FileOutputStream(path)) {
            Serialize(fileStream, presentation);
        } //automatically closes stream
    }

    /**
     * Write a presentation to a FileOutputStream
     * @param fileStream stream to write to
     * @param presentation presentation to write
     * @throws IOException thrown if ObjectOutputStream failed to start
     */
    public static void Serialize(FileOutputStream fileStream, Presentation presentation) throws IOException {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(presentation);
            objectStream.flush();
        } //automatically closes stream
    }
}
