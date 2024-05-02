package com.example.pj.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicThread extends Thread {
    private File musicFile;
    private boolean isRunning;
    private Clip clip;

    public MusicThread(File musicFile) {
        this.musicFile = musicFile;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try {
            while (true) { // Vòng lặp vô hạn
                clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicFile);
                clip.open(inputStream);
                clip.start();

                while (isRunning && clip.getMicrosecondPosition() < clip.getMicrosecondLength()) {
                    Thread.sleep(10);
                }

                clip.stop();
                clip.close();
                inputStream.close();
            }
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void resumeMusic() {
        if (clip != null && !clip.isRunning()) {
            long currentPosition = clip.getMicrosecondPosition();
            clip.setMicrosecondPosition(currentPosition);
            clip.start();
        }
    }
    public void stopMusic() {
        isRunning = false;
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}