package com.example.demo.levels;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class LevelBossTest extends ApplicationTest {

    private LevelBoss levelBoss;
    private Controller controller;
    private Stage stage;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            levelBoss = new LevelBoss(600, 800, controller, stage);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testInitializeBoss() {
        Platform.runLater(() -> {
            assertNotNull(levelBoss, "LevelBoss should be initialized");
            assertNotNull(levelBoss.getBoss(), "Boss should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testSpawnBoss() {
        Platform.runLater(() -> {
            levelBoss.spawnEnemyUnits();
            assertNotNull(levelBoss.getBoss(), "Boss should be spawned");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testCheckIfGameOver_UserDestroyed() {
        Platform.runLater(() -> {
            while (levelBoss.getUser().getHealth() > 0) {
                levelBoss.getUser().takeDamage();
            }
            levelBoss.checkIfGameOver();

            assertTrue(levelBoss.isGameOver(), "Game should be over if user is destroyed");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testCheckIfGameOver_BossDestroyed() {
        Platform.runLater(() -> {
            while (levelBoss.getBoss().getHealth() > 0) {
                levelBoss.getBoss().takeDamage();
            }
            levelBoss.checkIfGameOver();

            assertTrue(levelBoss.isGameOver(), "Game should be over if boss is destroyed");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testCheckIfGameOver_GameContinues() {
        Platform.runLater(() -> {
            assertFalse(levelBoss.isGameOver(), "Game should continue if neither the user nor the boss is destroyed");

            levelBoss.checkIfGameOver();

            assertFalse(levelBoss.isGameOver(), "Game should continue if neither the user nor the boss is destroyed");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}