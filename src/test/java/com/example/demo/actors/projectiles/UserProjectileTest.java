package com.example.demo.actors.projectiles;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class UserProjectileTest extends ApplicationTest {

    private UserProjectile userProjectile;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> userProjectile = new UserProjectile(0, 0));
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testTakeDamage() {
        Platform.runLater(() -> {
            userProjectile.takeDamage();
            assertEquals(0, userProjectile.getHealth(), "UserProjectile health should be zero after taking damage");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            double initialX = userProjectile.getTranslateX();
            userProjectile.updatePosition();
            assertEquals(initialX + 15, userProjectile.getTranslateX(), "UserProjectile should move 15 units to the right");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testDestroy() {
        Platform.runLater(() -> {
            userProjectile.takeDamage(); // This should destroy the projectile
            assertTrue(userProjectile.isDestroyed(), "UserProjectile should be destroyed when health reaches zero");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}