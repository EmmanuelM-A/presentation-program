package com.scc210groupproject.readwrite;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.scc210groupproject.structure.Presentation;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.HashMap;
import java.util.Iterator;

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
            if (path.endsWith(".compressed"))
                try (GZIPInputStream compressedStream = new GZIPInputStream(fileStream)) {
                    result = deserialize(compressedStream);
                }
            else
                result = deserialize(fileStream);
        }

        Presentation.set(result);
    }

    /**
     * Read a presentation to a FileOutputStream
     * @param inputStream stream to write to
     * @return presentation read
     * @throws IOException thrown if ObjectOutputStream failed to start
     * @throws ClassNotFoundException thrown if ObjectOutputStream cannot find Presentation object in the file
     */
    public static Presentation deserialize(InputStream inputStream) throws IOException {
        Reader reader = new Reader(inputStream);
        return (Presentation)reader.loadHierarchy();
    }


    public static class Reader {

        private ObjectMapper mapper;
        private JsonNode root;

        private HashMap<String, IJsonSerializable> resolvedObjects;

        private JsonNode current;

        public Reader(InputStream stream) throws IOException {
            this();
            mapper = new ObjectMapper();
            root = mapper.readTree(stream);
        }
        private Reader() {
            resolvedObjects = new HashMap<>();
        }

        public boolean hasField(String name) {
            return current.has(name);
        }

        public int readInt(String name) throws IOException {
            return current.get(name).asInt();
        }

        public float readFloat(String name) throws IOException {
            return (float)current.get(name).asDouble();
        }

        public double readDouble(String name) throws IOException {
            return current.get(name).asDouble();
        }

        public boolean readBoolean(String name) throws IOException {
            return current.get(name).asBoolean();
        }

        public IJsonSerializable readObject(String name) throws IOException, IllegalArgumentException {
            String id = current.get(name).asText();
            return resolve(id);
        }

        @SuppressWarnings("unchecked") // error caught
        public List<?> readObjectList(String name) throws IOException {

            JsonNode construct = current.get(name);
            String listClassName = construct.get("type").asText();

            Class<?> type;
            try {
                type = Class.forName(listClassName);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Cannot find class of " + listClassName);
            }

            if (!List.class.isAssignableFrom(type)) {
                throw new IllegalArgumentException(listClassName + " does not implement IJsonSerializable");
            }

            List<?> intermediate;
            try {
                intermediate = (List<?>)type.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
                throw new IllegalArgumentException("Cannot call 0 argument constructor on " + listClassName + ", make sure it is public");
            }

            List<Object> list;
            try {
                list = (List<Object>)intermediate;
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Failed to cast generic lsit");
            }

            ArrayNode values = (ArrayNode)construct.get("values");

            Iterator<JsonNode> iterator = values.iterator();
            while (iterator.hasNext())
                list.add(resolve(iterator.next().asText()));

            return list;
        }

        private IJsonSerializable resolve(String id) throws IOException, IllegalArgumentException {

            if (id.equals("-1")) {
                return null;
            }

            IJsonSerializable hit = resolvedObjects.get(id);
            if (hit != null)
                return hit;

            JsonNode node = root.get(id);
            String className = node.get("type").asText();

            Class<?> type;
            try {
                type = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Cannot find class of " + className);
            }

            if (!IJsonSerializable.class.isAssignableFrom(type)) {
                throw new IllegalArgumentException(className + " does not implement IJsonSerializable");
            }

            IJsonSerializable object;
            try {
                Method method = type.getDeclaredMethod("createEmpty");
                method.setAccessible(true);
                object = (IJsonSerializable)method.invoke(null);
            } catch (IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException e) {
                throw new IllegalArgumentException("Cannot call static method createEmpty() on " + className + ", make sure it is declared and return Object implementing IJsonSerializable");
            }

            resolvedObjects.put(id, object);

            JsonNode original = current;
            current = node;
            object.readValue(this);
            current = original;

            return object;
        }

        public IJsonSerializable loadHierarchy() throws IOException {
            Iterator<String> iterator = root.fieldNames();
            if (iterator.hasNext())
                return resolve(iterator.next());
            else
                return null;
        }
    }
}
