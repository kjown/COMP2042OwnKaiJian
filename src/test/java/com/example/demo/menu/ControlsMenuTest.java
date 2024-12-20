package com.example.demo.menu;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ControlsMenuTest extends ApplicationTest {

    private ControlsMenu controlsMenu;
    private Stage stage;
    private Controller controller;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            controlsMenu = new ControlsMenu(stage, 600, 800, controller);
            controlsMenu.show();
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testControlsMenuInitialization() {
        Platform.runLater(() -> {
            assertNotNull(controlsMenu, "ControlsMenu should be initialized");
            assertNotNull(stage.getScene(), "Scene should be set on the stage");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}