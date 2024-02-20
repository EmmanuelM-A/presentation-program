package com.scc210groupproject.structure;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.print.attribute.standard.Media;
import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


class VideoElement extends ExtendedElement {

    private final JFXPanel jfxPanel = new JFXPanel();
    private MediaPlayer mediaPlayer;
    private File videoFile;

    public VideoElement(File videoFile, int height, int width) {
        this.videoFile = videoFile;
        setLayout(new BorderLayout());
        add(jfxPanel, BorderLayout.CENTER);
        setPreferredSize(new Dimension(height, width));
        initializePlayer();
    }

    private void initializePlayer() {
        Platform.runLater(() -> {
            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            jfxPanel.setScene(new javafx.scene.Scene(new javafx.scene.Group(mediaView)));

            //mediaPlayer.play();
        });
    }

    public void stop() {
        if (mediaPlayer != null) {
            Platform.runLater(() -> {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            });
        }
    }

    public void play() {
        if (mediaPlayer != null) {
            Platform.runLater(() -> {
                if(mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                    mediaPlayer.play();
                }
            });
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            Platform.runLater(() -> {
                mediaPlayer.pause();
            });
        }
    }

    public JPanel getPanel() {
        return this;
    }
}

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeSelf'");
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSelf'");
    }

    @Override
    public Component asComp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asComp'");
    }
    
}