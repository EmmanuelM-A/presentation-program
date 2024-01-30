package com.scc210groupproject.readwrite;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.scc210groupproject.structure.Presentation;

import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author wonge1
 *         Class to Serialize the Presentation
 */
public class FileSerializer {

    /**
     * Write a presentation to a given path using
     * 
     * @see FileSerializer#serialize(FileOutputStream, Presentation)
     * @param path         file path to use
     * @param presentation item to save to file
     * @throws IOException           thrown if FileOutputStream and/or
     *                               ObjectOutputStream failed to start
     * @throws FileNotFoundException thrown if path is a directory (or any other
     *                               reason by)
     * @see FileOutputStream
     */
    public static void writeToPath(String path) throws IOException {

        Presentation current = Presentation.get();
        if (current == null)
            return;

        try (FileOutputStream fileStream = new FileOutputStream(path)) {
            if (path.endsWith(".pcomp"))
                try (GZIPOutputStream compressedStream = new GZIPOutputStream(fileStream)) {
                    serialize(compressedStream, current);
                } // automatically closes stream
            else
                serialize(fileStream, current);
        } // automatically closes stream
    }

    /**
     * Write a presentation to a FileOutputStream
     * 
     * @param outputStream stream to write to
     * @param presentation presentation to write
     * @throws IOException thrown if ObjectOutputStream failed to start
     */
    public static void serialize(OutputStream outputStream, Presentation presentation) throws IOException {
        Writer writer = new Writer(outputStream);
        writer.writeFrom(presentation);
    }

    public static class Writer {

        private JsonGenerator generator;

        // not constraint by int max but will return wrong value for size() if beyond
        // max
        private HashMap<IJsonSerializable, BigInteger> writtenObjects;
        private BigInteger availableIndex;

        private LinkedList<IJsonSerializable> queuedObjects;

        public Writer(OutputStream stream) throws IOException {
            this();
            generator = new JsonFactory().createGenerator(stream);
            generator.useDefaultPrettyPrinter();
        }

        private Writer() {
            writtenObjects = new HashMap<>();
            availableIndex = BigInteger.ZERO;
            queuedObjects = new LinkedList<>();
        }

        public void writeString(String name, String value) throws IOException {
            generator.writeStringField(name, value);
        }

        public void writeInt(String name, int value) throws IOException {
            generator.writeNumberField(name, value);
        }

        public void writeFloat(String name, float value) throws IOException {
            generator.writeNumberField(name, value);
        }

        public void writeDouble(String name, double value) throws IOException {
            generator.writeNumberField(name, value);
        }

        public void writeBoolean(String name, boolean value) throws IOException {
            generator.writeBooleanField(name, value);
        }

        private BigInteger updateObjectLists(IJsonSerializable object) {

            if (object == null)
                return new BigInteger("-1");

            BigInteger index = writtenObjects.get(object);

            if (index == null) {
                index = availableIndex;
                writtenObjects.put(object, index);
                queuedObjects.add(object);
                availableIndex = availableIndex.add(BigInteger.ONE);
            }

            return index;
        }

        public <T> void writeObjectList(String name, List<T> values) throws IOException {
            writeObjectList(name, values, false);
        }

        public <T> void writeObjectList(String name, List<T> values, boolean ignoreNonSerializable) throws IOException {
            generator.writeObjectFieldStart(name);

            generator.writeStringField("type", values.getClass().getName());

            generator.writeArrayFieldStart("values");
            for (Object value : values) {
                if (IJsonSerializable.class.isAssignableFrom(value.getClass())) {
                    IJsonSerializable element = (IJsonSerializable) value;
                    BigInteger index = updateObjectLists(element);
                    generator.writeString(index.toString());

                    continue;
                }

                if (ignoreNonSerializable)
                    continue;

                throw new IllegalArgumentException(
                        "trying to serialize values that does not implement IJsonSerializable");
            }
            generator.writeEndArray();

            generator.writeEndObject();
        }

        public <T extends IJsonSerializable> void writeObject(String name, T object) throws IOException {
            BigInteger index = updateObjectLists(object);
            generator.writeStringField(name, index.toString());
        }

        public void writeFrom(IJsonSerializable firstItem) throws IOException {
            generator.writeStartObject();
            updateObjectLists(firstItem);
            iterate();
        }

        private void iterate() throws IOException {

            IJsonSerializable next = queuedObjects.poll();
            if (next == null) {
                generator.writeEndObject();
                generator.flush();
                return;
            }

            generator.writeObjectFieldStart(writtenObjects.get(next).toString());
            generator.writeStringField("type", next.getClass().getName());
            next.writeValue(this);
            generator.writeEndObject();

            iterate();
        }
    }
}
