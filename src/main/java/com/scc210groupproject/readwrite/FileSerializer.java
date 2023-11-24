package com.scc210groupproject.readwrite;

import com.scc210groupproject.structure.Presentation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author wonge1
 * Class to Serialize the Presentation
 */
public class FileSerializer {

    /**
     * Write a presentation to a FileOutputStream
     * @param fileStream stream to write to
     * @param presentation presentation to write
     * @throws IOException thrown if ObjectOutputStream failed to start
     */
    public static void Serialize(FileOutputStream fileStream, Presentation presentation) throws IOException {
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(presentation);
        objectStream.flush();
        objectStream.close();
    }
}
