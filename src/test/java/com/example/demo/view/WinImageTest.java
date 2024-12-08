// WinImageTest.java
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

class WinImageTest extends ApplicationTest {

    private WinImage winImage;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            winImage = new WinImage(100, 100);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testWinImageInitialization() {
        Platform.runLater(() -> {
            assertNotNull(winImage, "WinImage should be initialized");
            assertEquals(100, winImage.getLayoutX(), "X position should be 100");
            assertEquals(100, winImage.getLayoutY(), "Y position should be 100");
            assertFalse(winImage.isVisible(), "WinImage should be initially hidden");
            assertNotNull(winImage.getImage(), "WinImage should be loaded");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowWinImage() {
        Platform.runLater(() -> {
            winImage.showWinImage();
            assertTrue(winImage.isVisible(), "WinImage should be visible");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}