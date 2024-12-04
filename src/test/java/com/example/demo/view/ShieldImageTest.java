// ShieldImageTest.java
package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShieldImageTest extends ApplicationTest {

    private ShieldImage shieldImage;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            shieldImage = new ShieldImage(100, 100, 10);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShieldImageInitialization() {
        Platform.runLater(() -> {
            assertNotNull(shieldImage, "ShieldImage should be initialized");
            assertEquals(100, shieldImage.getLayoutX(), "X position should be 100");
            assertEquals(100, shieldImage.getLayoutY(), "Y position should be 100");
            assertEquals(10, shieldImage.getHealth(), "Initial health should be 10");
            assertFalse(shieldImage.isVisible(), "Shield should be initially hidden");
            assertNotNull(shieldImage.getImage(), "Shield image should be loaded");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowShield() {
        Platform.runLater(() -> {
            shieldImage.showShield();
            assertTrue(shieldImage.isVisible(), "Shield should be visible");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testHideShield() {
        Platform.runLater(() -> {
            shieldImage.showShield();
            shieldImage.hideShield();
            assertFalse(shieldImage.isVisible(), "Shield should be hidden");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testTakeDamage() {
        Platform.runLater(() -> {
            shieldImage.showShield();
            shieldImage.takeDamage(5);
            assertEquals(5, shieldImage.getHealth(), "Shield health should be reduced to 5");
            assertTrue(shieldImage.isVisible(), "Shield should still be visible");

            shieldImage.takeDamage(5);
            assertEquals(0, shieldImage.getHealth(), "Shield health should be reduced to 0");
            assertFalse(shieldImage.isVisible(), "Shield should be hidden");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testSetPosition() {
        Platform.runLater(() -> {
            shieldImage.setPosition(200, 200);
            assertEquals(200, shieldImage.getLayoutX(), "X position should be 200");
            assertEquals(200, shieldImage.getLayoutY(), "Y position should be 200");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}