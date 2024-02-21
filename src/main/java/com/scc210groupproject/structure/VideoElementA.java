package com.scc210groupproject.structure;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;


public class VideoElementA extends ExtendedElement {

    private JLabel label;
    private File file;
    private FrameGrab grab;

    public VideoElementA(File video) {
        label = new JLabel();

        file = video;
        try {
            grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
        } catch(IOException | JCodecException e) {

        }

        VideoElementA self = this;
        Thread task = new Thread() {

            @Override
            public void run() {
                Picture picture;
                try {
                    while ((picture = grab.getNativeFrame()) != null) {
                        label.setIcon(new ImageIcon(AWTUtil.toBufferedImage(picture)));
                        SwingUtilities.invokeAndWait(new Runnable() {

                            @Override
                            public void run() {
                                self.notifyUpdate(self);
                            }
                            
                        });
                        
                        Thread.sleep(17);
                    }
                }
                catch (IOException | InterruptedException | InvocationTargetException e) {

                }
            }
            
        };
        
        task.start();
    }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {
        super.readSelfExtended(reader);
    }

    @Override
    public Component asComp() {
        return label;
    }
    
}