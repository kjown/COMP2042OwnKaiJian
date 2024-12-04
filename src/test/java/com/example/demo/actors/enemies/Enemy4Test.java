package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectiles.LaserProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class Enemy4Test extends ApplicationTest {

    private Enemy4 enemy;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> enemy = new Enemy4(100, 100));
        waitForFxEvents();
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
            assertTrue(currentX != 100.0 || currentY != 100.0, "The enemy should move from its initial position");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testFireProjectile() {
        Platform.runLater(() -> {
            ActiveActorDestructible projectile = enemy.fireProjectile();
            if (projectile != null) {
                assertTrue(projectile instanceof LaserProjectile, "The projectile should be an instance of LaserProjectile");
                assertEquals(enemy.getProjectileXPosition(-50.0), projectile.getLayoutX(), "Projectile X position should match expected value");
                assertEquals(enemy.getProjectileYPosition(50.0), projectile.getLayoutY(), "Projectile Y position should match expected value");
            } else {
                assertNull(projectile, "No projectile should be fired when random check fails");
            }
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    private void waitForFxEvents() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}