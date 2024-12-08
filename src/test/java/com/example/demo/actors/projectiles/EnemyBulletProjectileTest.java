package com.example.demo.actors.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class EnemyBulletProjectileTest extends ApplicationTest {

    private EnemyBulletProjectile enemyBulletProjectile;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> enemyBulletProjectile = new EnemyBulletProjectile(0, 0));
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = enemyBulletProjectile.getTranslateX();
            enemyBulletProjectile.updatePosition();
            assertEquals(initialX - 13, enemyBulletProjectile.getTranslateX(), "EnemyBulletProjectile should move 13 units to the left");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testDestroy() {
        Platform.runLater(() -> {
            enemyBulletProjectile.takeDamage();
            assertTrue(enemyBulletProjectile.isDestroyed(), "EnemyBulletProjectile should be destroyed when health reaches zero");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}