package com.example.demo.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The AudioManager class is responsible for managing the background music and sound effects in the game.
 * It ensures that the audio is controlled globally within the application and follows the Singleton design pattern
 * to provide a single instance for audio management.
 */
public class AudioManager {

    private static AudioManager instance;

    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer soundEffectPlayer;

    private boolean isBackgroundMusicOn = true;

    /**
     * Private constructor to prevent direct instantiation of the AudioManager class.
     */
    private AudioManager() {}

    /**
     * Returns the singleton instance of AudioManager.
     *
     * @return the singleton instance of AudioManager
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
     * Initializes the background music with the specified music file path.
     *
     * @param musicPath the file path of the background music
     */
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

    /**
     * Plays a sound effect from the specified file path.
     * If a sound effect is already playing, it is stopped before the new sound effect is played.
     *
     * @param soundPath the file path of the sound effect
     */
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
     * Resumes playing the background music if it is paused or stopped.
     * If the background music is not initialized, it will be initialized with the default music path.
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
     * Pauses the background music if it is currently playing.
     */
    public void pauseBackgroundMusic() {
        if (backgroundMusicPlayer != null && backgroundMusicPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            backgroundMusicPlayer.pause();
        }
    }

    /**
     * Sets the background music on or off.
     * If the background music is turned on, it will resume playing.
     * If turned off, it will pause the music.
     *
     * @param isOn true to turn on the background music, false to turn it off
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
     * Returns whether the background music is currently on or off.
     *
     * @return true if the background music is on, false otherwise
     */
    public boolean isBackgroundMusicOn() {
        return isBackgroundMusicOn;
    }

}
