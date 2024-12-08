package com.example.demo.actors.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class BossProjectileTest extends ApplicationTest {

    private BossProjectile bossProjectile;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> bossProjectile = new BossProjectile(0));
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = bossProjectile.getTranslateX();
            bossProjectile.updatePosition();
            assertEquals(initialX - 15, bossProjectile.getTranslateX(), "BossProjectile should move 15 units to the left");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }


    @Test
    void testDestroy() {
        Platform.runLater(() -> {
            bossProjectile.takeDamage();
            assertTrue(bossProjectile.isDestroyed(), "BossProjectile should be destroyed when health reaches zero");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}