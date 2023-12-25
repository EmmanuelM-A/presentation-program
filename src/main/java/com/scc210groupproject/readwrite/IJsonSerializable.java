package com.scc210groupproject.readwrite;

import java.io.IOException;

/**
 * @author wonge1
 * Class implementing this MUST ALSO implement:
 * ANY_ACCESS_MODIFIER static Object createEmpty()
 * that returns an empty version of the class
 */
public interface IJsonSerializable {
    public abstract void writeValue(FileSerializer.Writer writer) throws IOException;
    public abstract void readValue(FileDeserializer.Reader reader) throws IOException;
}
