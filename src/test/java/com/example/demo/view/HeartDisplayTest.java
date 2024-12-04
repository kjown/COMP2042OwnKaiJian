// HeartDisplayTest.java
package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HeartDisplayTest extends ApplicationTest {

    private HeartDisplay heartDisplay;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            heartDisplay = new HeartDisplay(100, 100, 3);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testHeartDisplayInitialization() {
        Platform.runLater(() -> {
            assertNotNull(heartDisplay, "HeartDisplay should be initialized");
            HBox container = heartDisplay.getContainer();
            assertNotNull(container, "Container should be initialized");
            assertEquals(3, container.getChildren().size(), "Container should have 3 hearts");
            for (int i = 0; i < 3; i++) {
                assertEquals(ImageView.class, container.getChildren().get(i).getClass(), "Each child should be an instance of ImageView");
            }
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testRemoveHeart() {
        Platform.runLater(() -> {
            heartDisplay.removeHeart();
            HBox container = heartDisplay.getContainer();
            assertEquals(2, container.getChildren().size(), "Container should have 2 hearts after removing one");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}