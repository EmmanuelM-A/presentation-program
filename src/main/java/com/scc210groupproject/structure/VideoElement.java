package com.scc210groupproject.structure;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.helper.CoordinateUtils;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.ui.helper.GeneralButtons;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.api.PictureWithMetadata;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.scale.AWTUtil;


public class VideoElement extends ExtendedElement {

    private PlayerPanel label;
    private File file;
    private FrameGrab grab;

    private PlayerThread player = null;

    public void play() {
        player.play();
        label.playing = true;
    }
    
    public void pause() {
        player.pause();
        label.playing = false;
    }

    public void cont() {
        player.cont();
        label.playing = true;
    }

    private void onEnd() {
        label.playing = false;
        notifyUpdate(this);
    }

    private void setup() {
        player = new PlayerThread(this, grab);
        player.start();

        addInputListener(new IMouseClicked() {

            @Override
            public void mouseClicked(Object target, InputState state) {
                if (player.position == 0) 
                    play();
                else if (player.position >= player.entries.length)
                    play();
                else if (player.shouldPlay)
                    pause();
                else
                    cont();
            }
            
        });

        label.addComponentListener(new ComponentListener() {

            private void resize() {
                Icon icon = label.getIcon();
                if (icon != null) {
                    label.setIcon(GeneralButtons.resizeIcon((ImageIcon)icon, label.getWidth(), label.getHeight()));
                }
            }

            @Override
            public void componentResized(ComponentEvent e) {
                resize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                resize();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                resize();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                resize();
            }
            
        });
    }

    public VideoElement(File video) {
        this();

        file = video;
        try {
            grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
        } catch(IOException | JCodecException e) {}

        setup();
    }

    public VideoElement() {
        label = new PlayerPanel();
    }

    public static VideoElement createEmpty() { return new VideoElement(); }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);

        writer.writeFile("video", file);
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {
        file = reader.readFile("video");
        try {
            grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
        } catch(IOException | JCodecException e) {}

        super.readSelfExtended(reader);
        
        setup();

        notifyUpdate(this);
    }

    @Override
    public Component asComp() {
        return label;
    }

    @Override
    protected void destroySelf() {
        if (player != null)
            player.exit();
    }

    private static class PlayerPanel extends JLabel {

        public boolean playing = false;

        private Polygon triangle = null;
        private Polygon boxL = null;
        private Polygon boxR = null;
        private AffineTransform transform;

        public Shape pauseA = null;
        public Shape pauseB = null;
        public Shape play = null;
        
        private PlayerPanel() {
            super();

            triangle = new Polygon();
            triangle.addPoint(-2, 1);
            triangle.addPoint(-2, -1);
            triangle.addPoint(2, 0);

            boxL = new Polygon();
            boxL.addPoint(-2, 2);
            boxL.addPoint(-2, -2);
            boxL.addPoint(-1, -2);
            boxL.addPoint(-1, 2);
            
            boxR = new Polygon();
            boxR.addPoint(1, 2);
            boxR.addPoint(1, -2);
            boxR.addPoint(2, -2);
            boxR.addPoint(2, 2);

            transform = new AffineTransform();

            super.setLayout(null);
            super.setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D)g.create();

            g2d.setColor(Color.BLACK);
            Dimension size = super.getSize();
            g2d.fillRect(0, 0, size.width, size.height);

            super.paintComponent(g);
            
            g2d.setColor(Color.WHITE);
            if (playing) {
                pauseA = CoordinateUtils.getTransformed(boxL, transform, size.width / 2, size.height / 2, 0d, 20d, 20d);
                pauseB = CoordinateUtils.getTransformed(boxR, transform, size.width / 2, size.height / 2, 0d, 20d, 20d);
                g2d.fill(pauseA);
                g2d.fill(pauseB);
                play = null;
            }
            else {
                play = CoordinateUtils.getTransformed(triangle, transform, size.width / 2, size.height / 2, 0d, 20d, 40d);
                g2d.fill(play);
                pauseA = null;
                pauseB = null;
            }

        }
    }

    private static class PlayerThread extends Thread {

        private class Entry {
            public double time;
            public BufferedImage image;
        }

        private Object lock;

        public VideoElement element;
        public Entry[] entries;

        public long start;
        public long pause;
        public int position;
        public boolean shouldPlay;
        public boolean exit;

        public void play() {
            shouldPlay = true;
            start = System.currentTimeMillis();
            position = 0;
            
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        public void pause() {
            pause = System.currentTimeMillis();
            shouldPlay = false;
        }

        public void cont() {
            start += System.currentTimeMillis() - pause;
            shouldPlay = true;
            
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        public void exit() {
            exit = true;
            
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        public PlayerThread(VideoElement owner, FrameGrab grab) {
            lock = new Object();
            element = owner;
            
            SortedSet<Entry> tree = new TreeSet<>(new Comparator<Entry>() {

                @Override
                public int compare(Entry o1, Entry o2) {
                    return Double.compare(o1.time, o2.time);
                }
                
            });
            PictureWithMetadata frame;
            try {
                while ((frame = grab.getNativeFrameWithMetadata()) != null) {
                    Entry entry = new Entry();
                    entry.time = frame.getTimestamp();
                    entry.image = AWTUtil.toBufferedImage(frame.getPicture(), frame.getOrientation());
                    tree.add(entry);
                }
            }
            catch (IOException e) {}

            entries = tree.toArray(new Entry[tree.size()]);
        }

        @Override
        public void run() {
            shouldPlay = false;
            position = 0;

            while (!exit) {
                while (true) {
                    if (!shouldPlay || exit)
                        break;
                    
                    if (!updateFrame()) {
                        element.onEnd();
                        break;
                    }

                    Thread.yield();
                }
                
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {}
                }
                    
            }
        }

        private boolean updateFrame() {
            double target = (double)(System.currentTimeMillis() - start) / 1000d;

            Entry current;
            while (true) {
                if (position >= entries.length)
                    return false;

                current = entries[position];
                if (current.time >= target)
                    break;
                
                position++;
            }

            Dimension size = element.getSize();
            ((JLabel)element.asComp()).setIcon(GeneralButtons.resizeIcon(current.image, size.width, size.height));
            element.notifyUpdate(element);

            return true;
        }
    }
    
}