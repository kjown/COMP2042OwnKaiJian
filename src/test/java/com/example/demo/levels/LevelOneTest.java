package com.example.demo.levels;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class LevelOneTest extends ApplicationTest {

    private LevelOne levelOne;
    private Controller controller;
    private Stage stage;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            levelOne = new LevelOne(600, 800, controller, stage);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testInitializeLevel() {
        Platform.runLater(() -> {
            assertNotNull(levelOne, "LevelOne should be initialized");
            assertNotNull(levelOne.getUser(), "User should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testTransitionToNextLevel() {
        Platform.runLater(() -> {
            levelOne.getUser().setNumberOfKills(10); // Simulate reaching kill target
            levelOne.checkIfGameOver();
            assertTrue(levelOne.isGameOver(), "Game should be over if user reaches kill target");
            assertEquals("com.example.demo.levels.LevelTwo", levelOne.getNextLevel(), "Should transition to LevelTwo");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}