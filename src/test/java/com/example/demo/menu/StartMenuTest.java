package com.example.demo.menu;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StartMenuTest extends ApplicationTest {

    private StartMenu startMenu;
    private Stage stage;
    private Controller controller;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            startMenu = new StartMenu(stage, 600, 800, controller);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testStartMenuInitialization() {
        Platform.runLater(() -> {
            assertNotNull(startMenu, "StartMenu should be initialized");
            startMenu.show();
            assertNotNull(stage.getScene(), "Scene should be set on the stage");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}