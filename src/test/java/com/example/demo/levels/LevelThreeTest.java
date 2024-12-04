package com.example.demo.levels;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class LevelThreeTest extends ApplicationTest {

    private LevelThree levelThree;
    private Controller controller;
    private Stage stage;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            levelThree = new LevelThree(600, 800, controller, stage);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testInitializeLevel() {
        Platform.runLater(() -> {
            assertNotNull(levelThree, "LevelThree should be initialized");
            assertNotNull(levelThree.getUser(), "User should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testCheckIfGameOver_UserDestroyed() {
        Platform.runLater(() -> {
            while (levelThree.getUser().getHealth() > 0) {
                levelThree.getUser().takeDamage();
            }
            levelThree.checkIfGameOver();
            assertTrue(levelThree.isGameOver(), "Game should be over if user is destroyed");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testSpawnEnemyUnits() {
        Platform.runLater(() -> {
            levelThree.spawnEnemyUnits();
            assertTrue(levelThree.getCurrentNumberOfEnemies() > 0, "Enemies should be spawned");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testTransitionToNextLevel() {
        Platform.runLater(() -> {
            levelThree.getUser().setNumberOfKills(5); // Simulate reaching kill target
            levelThree.checkIfGameOver();
            assertTrue(levelThree.isGameOver(), "Game should be over if user reaches kill target");
            assertEquals("com.example.demo.levels.LevelFour", levelThree.getNextLevel(), "Should transition to LevelFour");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}