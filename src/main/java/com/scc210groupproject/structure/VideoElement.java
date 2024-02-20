package com.scc210groupproject.structure;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class VideoElement extends ExtendedElement {

    private JPanel videoPanel;
    private final JFXPanel jfxPanel = new JFXPanel();
    private MediaPlayer mediaPlayer;
    private File videoFile;

    public VideoElement(File videoFile, int height, int width) {
        this.videoFile = videoFile;
        videoPanel = new JPanel(new BorderLayout());
        videoPanel.add(jfxPanel, BorderLayout.CENTER);;
        initializePlayer();
    }

    private void initializePlayer() {
        Platform.runLater(() -> {
            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            jfxPanel.setScene(new javafx.scene.Scene(new javafx.scene.Group(mediaView)));

            mediaPlayer.play();
            mediaPlayer.setAutoPlay(true);
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
        return videoPanel;
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
        return videoPanel;
    }
    
}