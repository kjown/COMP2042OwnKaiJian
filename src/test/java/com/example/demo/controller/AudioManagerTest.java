package com.example.demo.controller;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AudioManagerTest {

    private AudioManager audioManager;

    @Start
    private void start(Stage stage) {
        audioManager = AudioManager.getInstance();
    }

    @BeforeEach
    void setUp() {
        audioManager = AudioManager.getInstance();
    }

    @Test
    void testInitializeBackgroundMusic() {
        String musicPath = "/com/example/demo/music/backgroundmusic.mp3";
        assertNotNull(getClass().getResource(musicPath), "Background music file should exist");
        audioManager.initializeBackgroundMusic(musicPath);
        MediaPlayer player = audioManager.getBackgroundMusicPlayer();
        assertNotNull(player, "Background music player should be initialized");
        WaitForAsyncUtils.sleep(1, java.util.concurrent.TimeUnit.SECONDS); // Wait for the player to start playing
        assertEquals(MediaPlayer.Status.PLAYING, player.getStatus(), "Background music should be playing");
    }

    @Test
    void testResumeBackgroundMusic() {
        audioManager.pauseBackgroundMusic();
        audioManager.resumeBackgroundMusic();
        MediaPlayer player = audioManager.getBackgroundMusicPlayer();
        assertEquals(MediaPlayer.Status.PLAYING, player.getStatus(), "Background music should be playing");
    }

    @Test
    void testPauseBackgroundMusic() {
        audioManager.resumeBackgroundMusic();
        audioManager.pauseBackgroundMusic();
        MediaPlayer player = audioManager.getBackgroundMusicPlayer();
        assertEquals(MediaPlayer.Status.PAUSED, player.getStatus(), "Background music should be paused");
    }

    @Test
    void testSetBackgroundMusicOn() {
        audioManager.setBackgroundMusicOn(true);
        assertTrue(audioManager.isBackgroundMusicOn(), "Background music should be on");
        audioManager.setBackgroundMusicOn(false);
        assertFalse(audioManager.isBackgroundMusicOn(), "Background music should be off");
    }

    @Test
    void testIsBackgroundMusicOn() {
        audioManager.setBackgroundMusicOn(true);
        assertTrue(audioManager.isBackgroundMusicOn(), "Background music should be on");
        audioManager.setBackgroundMusicOn(false);
        assertFalse(audioManager.isBackgroundMusicOn(), "Background music should be off");
    }
}