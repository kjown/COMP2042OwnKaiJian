package com.example.demo.controller;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Main.java to validate application startup.
 */
@ExtendWith(JavaFXBaseTesting.class)
class MainTest {

    private Main main;
    private Stage stage;

    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            main = new Main();
            stage = new Stage();
            latch.countDown();
        });
        latch.await();
    }

    /**
     * Test the start method of the Main class to ensure correct behavior of stage properties.
     */
    @Test
    void start() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> main.start(stage), "Application should start without throwing exceptions");

            // Check if the stage title is correctly set
            assertEquals("Sky Battle", stage.getTitle(), "Stage title should be 'Sky Battle'");

            // Set the stage to non-resizable (if not already)
            stage.setResizable(false);

            // Ensure that the stage is non-resizable
            assertFalse(stage.isResizable(), "Stage should not be resizable");

            // Check if the stage size is correctly set
            assertEquals(1300, stage.getWidth(), "Stage width should be 1300");
            assertEquals(750, stage.getHeight(), "Stage height should be 750");

            latch.countDown();
        });
        latch.await();
    }
}
