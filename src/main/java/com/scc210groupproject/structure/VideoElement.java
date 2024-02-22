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
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.helper.CoordinateUtils;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.ui.helper.GeneralButtons;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.api.PictureWithMetadata;
import org.jcodec.common.DemuxerTrackMeta.Orientation;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;


public class VideoElement extends ExtendedElement {

    private PlayerPanel label;
    private File file;

    private PlayerThread player = null;

    public void play() {
        player.play();
        label.playing = true;
        label.loading = false;
    }
    
    public void pause() {
        player.pause();
        label.playing = false;
        label.loading = false;
    }

    public void cont() {
        player.cont();
        label.playing = true;
        label.loading = false;
    }

    private void onResume() {
        label.playing = true;
        label.loading = false;
    }

    private void onBuffer() {
        label.loading = true;
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    notifyUpdate(this);
                }
                
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println(e);
        } 
    }

    private void onEnd() {
        label.playing = false;
        label.loading = false;
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    notifyUpdate(this);
                }
                
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println(e);
        } 
    }

    private void setup() {
        player = new PlayerThread(this);
        player.start();

        addInputListener(new IMouseClicked() {

            @Override
            public void mouseClicked(Object target, InputState state) {
                if (label.loading) 
                    return;
                if (!player.started) 
                    play();
                else if (player.ended)
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

        super.readSelfExtended(reader);
        
        setup();

        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    notifyUpdate(this);
                }
                
            });
        } catch (InterruptedException | InvocationTargetException e) {
            System.err.println(e);
        } 
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
        public boolean loading = false;

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

            if (!loading) {
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
            else {
                Map<TextAttribute, Object> attributes = new HashMap<>();
                attributes.put(TextAttribute.SIZE, 40);
                attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
                g2d.setFont(g.getFont().deriveFont(attributes));
                g2d.drawString("buffering", size.width / 2 - 90, size.height / 2);
            }

        }
    }

    private static class PlayerThread extends Thread {

        private static class Entry {
            public double time;
            public File image;
        }

        private Object lock;
        private Path folder;

        private VideoElement element;
        private ConcurrentSkipListSet<Entry> tree = null;
        private Iterator<Entry> it = null;

        public long start;
        public long pause;
        public boolean shouldPlay;
        public boolean exit;

        public boolean fullyloaded = false;

        public boolean started = false;
        public boolean ended = false;

        private LinkedList<Thread> threads = new LinkedList<>();

        public void play() {
            started = true;
            ended = false;

            shouldPlay = true;
            start = System.currentTimeMillis();
            it = tree.iterator();
            
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

            Thread cleanup = new Thread() {
                @Override
                public void run() {
                    while (true) {

                        boolean outstanding;
                        synchronized (threads) {
                            outstanding = threads.size() > 0;
                        }

                        if (outstanding)
                            super.yield();
                        else 
                            break;
                    }
                    
                    synchronized (tree) {
                        for (Entry entry : tree) {
                            entry.image.delete();
                        }
                    }
                    
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                    
                    if (folder != null)
                        folder.toFile().delete();
                }
            };

            cleanup.start();
            
        }

        public void load() {
            tree = new ConcurrentSkipListSet<>(new Comparator<Entry>() {
        
                @Override
                public int compare(Entry o1, Entry o2) {
                    return Double.compare(o1.time, o2.time);
                }
                
            });

            CoordinateThread loader = new CoordinateThread();

            loader.start();
            
        }

        // Coordinate loading all frames 
        private class CoordinateThread extends Thread {
            @Override
            public void run() {
                FrameGrab grab = null;
                try {
                    grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(element.file));
                } catch(IOException | JCodecException e) {
                    return;
                }
    
                try {

                    folder = Files.createTempDirectory("imagecache-" + System.currentTimeMillis());
    
                    PictureWithMetadata frame;
                    while ((frame = grab.getNativeFrameWithMetadata()) != null) {

                        if (exit)
                            return;

                        while (threads.size() > 50)
                            super.yield();
    
                        double time = frame.getTimestamp();
                        Picture picture = frame.getPicture().cloneCropped();
                        Orientation orientation = frame.getOrientation();
                        
                        WriteThread thread = new WriteThread(time, picture, orientation, folder);
    
                        synchronized (threads) {
                            threads.add(thread);
                        }

                        thread.start();
                    }

                    while (true) {

                        boolean outstanding;
                        synchronized (threads) {
                            outstanding = threads.size() > 0;
                        }

                        if (outstanding)
                            super.yield();
                        else 
                            break;
                    }
                }
                catch (IOException e) {}
    
                fullyloaded = true;
            }
        }

        // Write one frame to file 
        private class WriteThread extends Thread {

            double time;
            Picture picture;
            Orientation orientation;
            Path folder;

            public WriteThread(double time, Picture picture, Orientation orientation, Path folder) {
                this.time = time;
                this.picture = picture;
                this.orientation = orientation;
                this.folder = folder;
            }

            @Override
            public void run() {

                try {

                    Entry entry = new Entry();
                    entry.time = time;

                    Path path = Files.createTempFile(folder, System.currentTimeMillis() + "", ".tmp");
                    entry.image = path.toFile();

                    FileOutputStream out = new FileOutputStream(entry.image);
                    ImageIO.write(AWTUtil.toBufferedImage(picture, orientation), "jpg", out);
                    out.close();
                    
                    synchronized (tree) {
                        tree.add(entry);
                    }
        
                } catch (IOException e) {}
                

                synchronized (threads) {
                    threads.remove(this);
                } 
            }
        }

        public PlayerThread(VideoElement owner) {
            lock = new Object();
            element = owner;

            load();
        }

        @Override
        public void run() {
            shouldPlay = false;

            while (!exit) {
                while (true) {
                    if (!shouldPlay || exit)
                        break;
                    
                    if (!updateFrame()) {
                        if (fullyloaded) {
                            started = false;
                            ended = true;
                            element.onEnd();
                        }
                        else {
                            long buffer = System.currentTimeMillis();
                            element.onBuffer();
                            Thread delay = new Thread() {
                                public void run() {
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {}
                                    start += System.currentTimeMillis() - buffer;
                                    element.onResume();

                                    it = tree.iterator();
                                    shouldPlay = true;
                                    synchronized (lock) {
                                        lock.notifyAll();
                                    }
                                };
                            };
                            delay.start();
                        }
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
            if (tree == null)
                return false;

            if (it == null)
                return false;

            double target = (double)(System.currentTimeMillis() - start) / 1000d;
            
            synchronized (tree) {
                Entry current;

                while (true) {
                    if (!it.hasNext())
                        return false;
    
                    current = it.next();
                    if (current.time >= target)
                        break;
                }
                
                Dimension size = element.getSize();
                try {
                    FileInputStream in = new FileInputStream(current.image);
                    BufferedImage image = ImageIO.read(in);
                    ((JLabel)element.asComp()).setIcon(GeneralButtons.resizeIcon(image, size.width, size.height));
                    in.close();
                } catch (IOException e) {
                    return false;
                }
                
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
        
                        @Override
                        public void run() {
                            element.notifyUpdate(element);
                        }
                        
                    });
                } catch (InterruptedException | InvocationTargetException e) {
                    return false;
                }
            }

            

            return true;
        }
    }
    
}