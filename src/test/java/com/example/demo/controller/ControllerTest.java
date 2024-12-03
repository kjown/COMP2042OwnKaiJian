package com.example.demo.controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ControllerTest {

    private Controller controller;
    private Stage stage;

    @Start
    private void start(Stage stage) {
        this.stage = stage;
    }

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            controller = new Controller(stage, 800, 600);
        });
    }

    @Test
    void testLaunchGame() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.launchGame());
        });
    }

    @Test
    void testGoToLevel() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.goToLevel(Controller.LEVEL_ONE_CLASS_NAME));
        });
    }

    @Test
    void testGoToMainMenu() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.goToMainMenu());
        });
    }

    @Test
    void testIsBackgroundMusicOn() {
        Platform.runLater(() -> {
            assertFalse(controller.isBackgroundMusicOn());
        });
    }

    @Test
    void testSetBackgroundMusicOn() {
        Platform.runLater(() -> {
            controller.setBackgroundMusicOn(true);
            assertTrue(controller.isBackgroundMusicOn());
        });
    }

    @Test
    void testPauseBackgroundMusic() {
        Platform.runLater(() -> {
            controller.pauseBackgroundMusic();
            assertFalse(controller.isBackgroundMusicOn());
        });
    }

    @Test
    void testResumeBackgroundMusic() {
        Platform.runLater(() -> {
            controller.resumeBackgroundMusic();
            assertTrue(controller.isBackgroundMusicOn());
        });
    }

    @Test
    void testUpdate() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.update(null, Controller.LEVEL_ONE_CLASS_NAME));
        });
    }
}