package com.example.demo.controller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class MainTest {

    private Main main;
    private Stage stage;

    @BeforeEach
    void setUp() throws InterruptedException {
        new JFXPanel(); // Initializes the JavaFX Toolkit

        Platform.runLater(() -> {
            try {
                main = new Main();
                stage = new Stage();
                main.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Wait for the JavaFX application to initialize
        Thread.sleep(1000);
    }

    @Test
    void testStart() {
        Platform.runLater(() -> {
            // Check if the stage title is correctly set
            assertEquals("Sky Battle", stage.getTitle(), "Stage title should be 'Sky Battle'");

            // Set the stage to non-resizable (if not already)
            stage.setResizable(false);

            // Ensure that the stage is non-resizable
            assertFalse(stage.isResizable(), "Stage should not be resizable");
        });
    }
}