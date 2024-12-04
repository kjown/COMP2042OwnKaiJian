// EndMenuTest.java
package com.example.demo.menu;

import com.example.demo.controller.Controller;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

class EndMenuTest extends ApplicationTest {

    private EndMenu endMenu;
    private Stage stage;
    private Controller controller;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            stage = new Stage();
            controller = new Controller(stage, 600, 800);
            endMenu = new EndMenu(stage, 600, 800, controller);
            endMenu.show();
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testEndMenuInitialization() {
        Platform.runLater(() -> {
            assertNotNull(endMenu, "EndMenu should be initialized");
            assertNotNull(stage.getScene(), "Scene should be set on the stage");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}