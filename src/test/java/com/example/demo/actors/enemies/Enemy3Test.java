package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectiles.EnemyBulletProjectile;
import com.example.demo.view.LevelViewLevelThree;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class Enemy3Test extends ApplicationTest {

    private Enemy3 enemy;
    private LevelViewLevelThree levelView;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> enemy = new Enemy3(levelView));
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = enemy.getLayoutX();
            double initialY = enemy.getLayoutY();
            enemy.updatePosition();
        });
        WaitForAsyncUtils.waitForFxEvents();
        Platform.runLater(() -> {
            double currentX = enemy.getLayoutX() + enemy.getTranslateX();
            double currentY = enemy.getLayoutY() + enemy.getTranslateY();
            assertTrue(currentX != 1000.0 || currentY != 400.0, "The enemy should move from its initial position");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}