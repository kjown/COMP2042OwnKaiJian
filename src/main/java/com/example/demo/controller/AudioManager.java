package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer soundEffectPlayer;

    private boolean isBackgroundMusicOn = true;

    /**
     * Initialize background music.
     *
     * @param musicPath the path to the music file
     */
    public void initializeBackgroundMusic(String musicPath) {
        if (backgroundMusicPlayer == null) {
            Media media = new Media(getClass().getResource(musicPath).toExternalForm());
            backgroundMusicPlayer = new MediaPlayer(media);

            // Set up background music to play in a loop
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusicPlayer.setVolume(0.1);  // Adjust volume here

            // Play the background music if enabled
            if (isBackgroundMusicOn) {
                backgroundMusicPlayer.play();
            }
        }
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

    /**
     * Play a sound effect.
     *
     * @param soundPath the path to the sound effect file
     */
    public void playSoundEffect(String soundPath) {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop();
        }
        Media media = new Media(getClass().getResource(soundPath).toExternalForm());
        soundEffectPlayer = new MediaPlayer(media);
        soundEffectPlayer.play();
    }
}
