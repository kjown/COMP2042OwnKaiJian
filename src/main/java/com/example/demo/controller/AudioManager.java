package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private static AudioManager instance;

    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer soundEffectPlayer;

    private boolean isBackgroundMusicOn = true;

    private AudioManager() {}

    // Public method to provide a single instance
    public static AudioManager getInstance() {
        if (instance == null) {
            synchronized (AudioManager.class) {
                if (instance == null) {
                    instance = new AudioManager();
                }
            }
        }
        return instance;
    }

    public void initializeBackgroundMusic(String musicPath) {
        if (backgroundMusicPlayer == null) {
            Media media = new Media(getClass().getResource(musicPath).toExternalForm());
            backgroundMusicPlayer = new MediaPlayer(media);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusicPlayer.setVolume(0.1);
            if (isBackgroundMusicOn) {
                backgroundMusicPlayer.play();
            }
        }
    }

    public void playSoundEffect(String soundPath) {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop();
        }
        Media media = new Media(getClass().getResource(soundPath).toExternalForm());
        soundEffectPlayer = new MediaPlayer(media);

        soundEffectPlayer.setVolume(0.2);

        soundEffectPlayer.play();
    }

    /**
     * Play the background music.
     */
    public void resumeBackgroundMusic() {
        if (backgroundMusicPlayer == null) {
            initializeBackgroundMusic("/com/example/demo/music/backgroundmusic.mp3");  // Update with correct path
        }
        if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PAUSED
                || backgroundMusicPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
            backgroundMusicPlayer.play();
        }
    }

    /**
     * Pause the background music.
     */
    public void pauseBackgroundMusic() {
        if (backgroundMusicPlayer != null && backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMusicPlayer.pause();
        }
    }

    /**
     * Setter for the background music on/off state.
     *
     * @param isOn true to turn on the music, false to turn off
     */
    public void setBackgroundMusicOn(boolean isOn) {
        isBackgroundMusicOn = isOn;
        if (isOn) {
            resumeBackgroundMusic();
        } else {
            pauseBackgroundMusic();
        }
    }

    /**
     * Getter for the background music on/off state.
     *
     * @return true if the background music is on, false otherwise
     */
    public boolean isBackgroundMusicOn() {
        return isBackgroundMusicOn;
    }

}
