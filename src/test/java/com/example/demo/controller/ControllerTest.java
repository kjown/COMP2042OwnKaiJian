package com.example.demo.controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

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
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testLaunchGame() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.launchGame());
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testGoToLevel() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.goToLevel(Controller.LEVEL_ONE_CLASS_NAME));
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testGoToMainMenu() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.goToMainMenu());
        });
        WaitForAsyncUtils.waitForFxEvents();
    }


    @Test
    void testSetBackgroundMusicOn() {
        Platform.runLater(() -> {
            controller.setBackgroundMusicOn(true);
            assertTrue(controller.isBackgroundMusicOn());
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testResumeBackgroundMusic() {
        Platform.runLater(() -> {
            controller.resumeBackgroundMusic();
        });
        WaitForAsyncUtils.waitForFxEvents();
        assertTrue(controller.isBackgroundMusicOn());
    }

    @Test
    void testUpdate() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> controller.update(null, Controller.LEVEL_ONE_CLASS_NAME));
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}