package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Singleton class responsible for managing background music and sound effects in the application.
 * It provides methods to play, pause, and control the volume of background music and sound effects.
 */
public class AudioManager {

    private static AudioManager instance;

    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer soundEffectPlayer;

    private boolean isBackgroundMusicOn = true;

    /**
     * Private constructor to prevent direct instantiation of the AudioManager class.
     * Use the {@link #getInstance()} method to get the singleton instance.
     */
    private AudioManager() {}

    /**
     * Provides the singleton instance of the AudioManager.
     *
     * @return the singleton instance of the AudioManager
     */
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

    /**
     * Initializes the background music player with the specified music file.
     * The background music will start playing if it is enabled and will loop indefinitely.
     *
     * @param musicPath the path to the background music file
     */
    public void initializeBackgroundMusic(String musicPath) {
        if (backgroundMusicPlayer == null) {
            Media media = new Media(getClass().getResource(musicPath).toExternalForm());
            backgroundMusicPlayer = new MediaPlayer(media);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // Set to loop indefinitely
            backgroundMusicPlayer.setVolume(0.1);  // Set the volume to 10%
            if (isBackgroundMusicOn) {
                backgroundMusicPlayer.play();
            }
        }
    }

    /**
     * Plays a sound effect from the specified sound file path.
     * Stops any currently playing sound effect before starting a new one.
     *
     * @param soundPath the path to the sound effect file
     */
    public void playSoundEffect(String soundPath) {
        if (soundEffectPlayer != null) {
            soundEffectPlayer.stop();  // Stop the current sound effect if it exists
        }
        Media media = new Media(getClass().getResource(soundPath).toExternalForm());
        soundEffectPlayer = new MediaPlayer(media);

        soundEffectPlayer.setVolume(0.2);  // Set sound effect volume to 20%

        soundEffectPlayer.play();  // Play the sound effect
    }

    /**
     * Resumes the background music if it was paused or stopped.
     * Initializes the background music if it hasn't been initialized yet.
     */
    public void resumeBackgroundMusic() {
        if (backgroundMusicPlayer == null) {
            initializeBackgroundMusic("/com/example/demo/music/backgroundmusic.mp3");  // Ensure the correct path to the music file
        }
        if (backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PAUSED
                || backgroundMusicPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
            backgroundMusicPlayer.play();  // Resume playing the background music
        }
    }

    /**
     * Pauses the background music if it is currently playing.
     */
    public void pauseBackgroundMusic() {
        if (backgroundMusicPlayer != null && backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMusicPlayer.pause();  // Pause the background music
        }
    }

    /**
     * Toggles the background music on or off.
     * If background music is enabled, it will be resumed or started.
     * If it is disabled, it will be paused.
     *
     * @param isOn true to turn on the background music, false to turn it off
     */
    public void setBackgroundMusicOn(boolean isOn) {
        isBackgroundMusicOn = isOn;
        if (isOn) {
            resumeBackgroundMusic();  // Resume or start background music
        } else {
            pauseBackgroundMusic();  // Pause background music
        }
    }

    /**
     * Checks if the background music is currently enabled (on).
     *
     * @return true if the background music is enabled, false if it is disabled
     */
    public boolean isBackgroundMusicOn() {
        return isBackgroundMusicOn;
    }

}
