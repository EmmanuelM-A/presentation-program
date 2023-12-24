package com.scc210groupproject.readwrite;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.optionalAnchors.AnchorManager;
import com.scc210groupproject.structure.optionalAnchors.AnchorReference;

import java.io.*;

/**
 * @author wonge1
 * Class to Deserialize the Presentation
 */
public class FileDeserializer {

    /**
     * Read a presentation from path
     * @param path file path to read from
     * @return presentation read
     * @throws IOException thrown if FileInputStream and/or ObjectOutputStream failed to start
     * @throws ClassNotFoundException thrown if ObjectOutputStream cannot find Presentation object in the file
     */
    public static void readFromPath(String path) throws IOException, ClassNotFoundException {
        Presentation result;
        try (FileInputStream fileStream = new FileInputStream(path)) {
            result = deserialize(fileStream);
        }

        Presentation.set(result);
    }

    /**
     * Read a presentation to a FileOutputStream
     * @param fileStream stream to write to
     * @return presentation read
     * @throws IOException thrown if ObjectOutputStream failed to start
     * @throws ClassNotFoundException thrown if ObjectOutputStream cannot find Presentation object in the file
     */
    public static Presentation deserialize(FileInputStream fileStream) throws IOException, ClassNotFoundException {
        //ObjectInputFilter.Config.createFilter("com.")

        Presentation result;
        try (ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            result = (Presentation)objectStream.readObject();
        } //automatically closes stream

        return result;
    }
}
