package com.example.demo.levels;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class LevelFourTest extends ApplicationTest {

    private LevelFour levelFour;
    private Controller controller;
    private Stage stage;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            levelFour = new LevelFour(600, 800, controller, stage);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testInitializeLevel() {
        Platform.runLater(() -> {
            assertNotNull(levelFour, "LevelFour should be initialized");
            assertNotNull(levelFour.getUser(), "User should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testCheckIfGameOver_UserDestroyed() {
        Platform.runLater(() -> {
            while (levelFour.getUser().getHealth() > 0) {
                levelFour.getUser().takeDamage();
            }
            levelFour.checkIfGameOver();
            assertTrue(levelFour.isGameOver(), "Game should be over if user is destroyed");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }


    @Test
    void testSpawnEnemyUnits() {
        Platform.runLater(() -> {
            levelFour.spawnEnemyUnits();
            assertNotNull(levelFour.getEnemy4(), "Enemy4 should be spawned");
            assertTrue(levelFour.getCurrentNumberOfEnemies() > 0, "Enemies should be spawned");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}