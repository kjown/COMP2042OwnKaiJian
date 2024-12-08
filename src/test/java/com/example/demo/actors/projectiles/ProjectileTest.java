package com.example.demo.actors.projectiles;

import com.example.demo.controller.Main;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest extends ApplicationTest {

    private Projectile projectile;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> projectile = new LaserProjectile(0, 0)); // Adjusted constructor call
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testTakeDamage() {
        Platform.runLater(() -> {
            projectile.takeDamage();
            assertEquals(0, projectile.getHealth(), "Projectile health should be zero after taking damage");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            projectile.updatePosition();
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdateActor() {
        Platform.runLater(() -> {
            projectile.updateActor();
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testDestroy() {
        Platform.runLater(() -> {
            projectile.takeDamage();
            assertTrue(projectile.isDestroyed(), "Projectile should be destroyed when health reaches zero");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}