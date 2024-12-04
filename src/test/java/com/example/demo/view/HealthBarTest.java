// HealthBarTest.java
package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HealthBarTest extends ApplicationTest {

    private HealthBar healthBar;
    private Group group;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            healthBar = new HealthBar(50, 50, 100);
            group = new Group();
            healthBar.addToScene(group);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testHealthBarInitialization() {
        Platform.runLater(() -> {
            assertNotNull(healthBar, "HealthBar should be initialized");
            assertEquals(2, group.getChildren().size(), "Group should contain two rectangles");
            Rectangle backgroundBar = (Rectangle) group.getChildren().get(0);
            Rectangle healthBarRect = (Rectangle) group.getChildren().get(1);
            assertEquals(Color.GRAY, backgroundBar.getFill(), "Background bar should be gray");
            assertEquals(Color.RED, healthBarRect.getFill(), "Health bar should be red");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdateHealth() {
        Platform.runLater(() -> {
            healthBar.updateHealth(50, 100);
            assertEquals(50, healthBar.getHealthBarWidth(), "Health bar width should be 50 when health is 50%");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testUpdatePosition() {
        Platform.runLater(() -> {
            healthBar.updatePosition(100, 100);
            double[] expectedPosition = {100, 100};
            assertArrayEquals(expectedPosition, healthBar.getPosition(), "Health bar position should be updated to (100, 100)");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}