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

    private JButton button= new JButton(new ImageIcon("src/main/resources/images/audio.png"));
    private AudioInputStream audio;

    public AudioElement(File file) {
        try {
            audio = AudioSystem.getAudioInputStream(file);
        } catch (Exception e){
            JOptionPane.showMessageDialog(UIFrame.instance,e, "Alert", JOptionPane.WARNING_MESSAGE);
        }

        super.addInputListener(new IMouseClicked() {

            @Override
            public void mouseClicked(Object target, InputState state) {
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(audio);
                    clip.start();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(UIFrame.instance,e, "Alert", JOptionPane.WARNING_MESSAGE);
                }
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