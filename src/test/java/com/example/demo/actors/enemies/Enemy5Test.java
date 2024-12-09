package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectiles.LaserProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class Enemy5Test extends ApplicationTest {

    private Enemy5 enemy;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> enemy = new Enemy5(100, 100));
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

    private void waitForFxEvents() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}