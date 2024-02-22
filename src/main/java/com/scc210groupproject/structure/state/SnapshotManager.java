package com.scc210groupproject.structure.state;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Stack;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.Presentation;

public class SnapshotManager {

    private static Stack<byte[]> steps = new Stack<>();
    private static int count = 0;
    private static int max = 250;
    private static Stack<byte[]> redos = new Stack<>();

    public static boolean saveState() {
        
        redos.clear();

        Presentation presentation = Presentation.get();
        if (presentation == null)
            return false;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Writer writer = new Writer(outputStream, true);
            writer.writeFrom(presentation);
        } catch (IOException e) {
            return false;
        }
        
        if (count >= max) {
            steps.remove(steps.size() - 1);
        }
        else
            count++;
        steps.push(outputStream.toByteArray());

        return true;
    }

    public static boolean restorePriorState() {
        if (steps.size() <= 0)
            return false;
            

        if (redos.size() <= 0) {
            saveState();
            redos.push(steps.pop());
        }

        byte[] entry = steps.pop();
        redos.push(entry);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(entry);
        try {
            Reader reader = new Reader(inputStream, true);
            Presentation.get().destroy(false);
            Presentation.set((Presentation)reader.loadHierarchy());
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public static boolean restoreAfterState() {
        if (redos.size() <= 0)
            return false;

        byte[] entry = redos.pop();
        steps.push(entry);
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(entry);
        try {
            Reader reader = new Reader(inputStream, true);
            Presentation.set((Presentation)reader.loadHierarchy());
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }
}
