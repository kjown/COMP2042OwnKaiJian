package com.example.demo.actors.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class EnemyRocketProjectileTest extends ApplicationTest {

    private EnemyRocketProjectile enemyRocketProjectile;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> enemyRocketProjectile = new EnemyRocketProjectile(0, 0));
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = enemyRocketProjectile.getTranslateX();
            enemyRocketProjectile.updatePosition();
            assertEquals(initialX - 10, enemyRocketProjectile.getTranslateX(), "EnemyRocketProjectile should move 10 units to the left");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testDestroy() {
        Platform.runLater(() -> {
            enemyRocketProjectile.takeDamage(); // This should destroy the projectile
            assertTrue(enemyRocketProjectile.isDestroyed(), "EnemyRocketProjectile should be destroyed when health reaches zero");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}