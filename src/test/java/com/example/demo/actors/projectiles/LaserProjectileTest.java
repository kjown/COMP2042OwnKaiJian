package com.example.demo.actors.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class LaserProjectileTest extends ApplicationTest {

    private LaserProjectile laserProjectile;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> laserProjectile = new LaserProjectile(0, 0));
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = laserProjectile.getTranslateX();
            laserProjectile.updatePosition();
            assertEquals(initialX - 7, laserProjectile.getTranslateX(), "LaserProjectile should move 7 units to the left");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testDestroy() {
        Platform.runLater(() -> {
            laserProjectile.takeDamage();
            assertTrue(laserProjectile.isDestroyed(), "LaserProjectile should be destroyed when health reaches zero");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}