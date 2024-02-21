package com.scc210groupproject.structure;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;
import com.scc210groupproject.ui.UIFrame;

public class AudioElement extends ExtendedElement {

    private JButton button = new JButton(new ImageIcon("src/main/resources/images/audio.png"));
    private File file;
    private Clip clip;

    public AudioElement(File file) {
        this.file = file;
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(UIFrame.instance, e, "Alert", JOptionPane.WARNING_MESSAGE);
        }

        AudioElement self = this;
        super.addInputListener(new IMouseClicked() {

            @Override
            public void mouseClicked(Object target, InputState state) {
                AudioInputStream audio = null;
                try {
                    audio = AudioSystem.getAudioInputStream(self.file);
                } catch (Exception e){
                    JOptionPane.showMessageDialog(UIFrame.instance, e, "Alert", JOptionPane.WARNING_MESSAGE);
                }

                clip.stop();
                try {
                    clip.close();
                    clip.open(audio);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(UIFrame.instance, e, "Alert", JOptionPane.WARNING_MESSAGE);
                }
                clip.setFramePosition(0);
                clip.start();
            }
        });
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
        return button;
    }
    
}