package com.example.demo.levels;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class LevelTwoTest extends ApplicationTest {

    private LevelTwo levelTwo;
    private Controller controller;
    private Stage stage;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            levelTwo = new LevelTwo(600, 800, controller, stage);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testInitializeLevel() {
        Platform.runLater(() -> {
            assertNotNull(levelTwo, "LevelTwo should be initialized");
            assertNotNull(levelTwo.getUser(), "User should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testCheckIfGameOver_UserDestroyed() {
        Platform.runLater(() -> {
            while (levelTwo.getUser().getHealth() > 0) {
                levelTwo.getUser().takeDamage();
            }
            levelTwo.checkIfGameOver();
            assertTrue(levelTwo.isGameOver(), "Game should be over if user is destroyed");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testSpawnEnemyUnits() {
        Platform.runLater(() -> {
            levelTwo.spawnEnemyUnits();
            assertTrue(levelTwo.getCurrentNumberOfEnemies() > 0, "Enemies should be spawned");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testTransitionToNextLevel() {
        Platform.runLater(() -> {
            levelTwo.getUser().setNumberOfKills(5); // Simulate reaching kill target
            levelTwo.checkIfGameOver();
            assertTrue(levelTwo.isGameOver(), "Game should be over if user reaches kill target");
            assertEquals("com.example.demo.levels.LevelThree", levelTwo.getNextLevel(), "Should transition to LevelThree");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}