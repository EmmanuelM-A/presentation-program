package com.scc210groupproject.homeDisplay;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.scc210groupproject.App;
import com.scc210groupproject.PresentationApp;
import com.scc210groupproject.readwrite.FileDeserializer;
import com.scc210groupproject.structure.Presentation;

/**Allow  setting the filetypes that will be displayed on a homeContentPanel */
/**
 * @author @leewelto
 */
public class CreatePresetButtonArray {

    // Credit Eyal Roth
    // https://stackoverflow.com/questions/1429172

    public static JButton[] createJButtonArray(String directoryPath) {
        // Get a list of "pcomp and pjson " files in the specified directory
        try {
            URI uri = App.class.getResource(directoryPath).toURI();
            Path myPath;
            FileSystem fileSystem = null;
            boolean inJar = uri.getScheme().equals("jar");
            if (inJar) {
                fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
                myPath = fileSystem.getPath(directoryPath);
            } else {
                myPath = Paths.get(uri);
            }

            List<JButton> buttons = new LinkedList<>();

            Stream<Path> walk = Files.walk(myPath, 1);
            for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
                Path path = it.next();

                if (!path.toString().endsWith(".pcomp"))
                    continue;

                File presentationFile = inJar ? new File(App.class.getResource(path.toString()).getFile())
                        : path.toFile();
                String fileName = presentationFile.getName();
                JButton button = new JButton(fileName.replace(".pcomp", ""));
                String tmpfn = fileName.replace(".pcomp", ".png");

                URL tmpurl = App.class.getResource(directoryPath + "/" + tmpfn);
                // Setting button icons-Gets preview image matching files name
                // Set to specific icon

                if (tmpurl != null) {

                    ImageIcon finalImageIcon = new ImageIcon(tmpurl);
                    button.setIcon(new ImageIcon(finalImageIcon.getImage().getScaledInstance(100, 32, 1)));

                }
                // if cant find image set to default slide preview c
                else {

                    ImageIcon icon = new ImageIcon(App.class.getResource("/presets/themes/defaultPresetCover.png"));
                    button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(100, 32, 1)));
                }

                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                button.setSize(new Dimension(72, 72));

                // Add listener to openFile,load from button path at time of creation

                // Launch the main program through open to absolute path of file for button
                // selected
                button.addActionListener(new ActionListener() {

                    private boolean clicked = false;

                    public void actionPerformed(ActionEvent e) {
                        if (clicked)
                            return;

                        clicked = true;
                        // Launch the App window in a new thread

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    PresentationApp.main(new String[] {});

                                    File target;
                                    if (inJar) {
                                        Path tmpPath = Files.createTempFile(System.currentTimeMillis() + "", ".tmp");
                                        target = tmpPath.toFile();
                                        target.deleteOnExit();

                                        FileOutputStream out = new FileOutputStream(target);
                                        out.write(App.class.getResourceAsStream(path.toString()).readAllBytes());
                                        out.close();
                                    } else
                                        target = presentationFile;

                                    Presentation result;
                                    try (FileInputStream fileStream = new FileInputStream(target)) {
                                        try (GZIPInputStream compressedStream = new GZIPInputStream(fileStream)) {
                                            result = FileDeserializer.deserialize(compressedStream);
                                        }
                                    }

                                    SwingUtilities.invokeAndWait(new Runnable() {

                                        @Override
                                        public void run() {
                                            Presentation.set(result);
                                        }

                                    });

                                    // Thread.currentThread().interrupt();

                                    HomeDisplay.instance.dispose();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });

                buttons.add(button);
            }
            walk.close();
            if (fileSystem != null)
                fileSystem.close();

            return buttons.toArray(new JButton[0]);
        } catch (IOException | URISyntaxException e) {
            return new JButton[0];
        }

    }
}
