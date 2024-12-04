// PauseMenuTest.java
package com.example.demo.menu;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PauseMenuTest extends ApplicationTest {

    private PauseMenu pauseMenu;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            pauseMenu = new PauseMenu(100, 100);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testPauseMenuInitialization() {
        Platform.runLater(() -> {
            assertNotNull(pauseMenu, "PauseMenu should be initialized");
            assertTrue(pauseMenu instanceof ImageView, "PauseMenu should be an instance of ImageView");
            assertFalse(pauseMenu.isVisible(), "PauseMenu should be initially invisible");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowPauseMenu() {
        Platform.runLater(() -> {
            pauseMenu.showPauseMenu();
            assertTrue(pauseMenu.isVisible(), "PauseMenu should be visible after calling showPauseMenu");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testHidePauseMenu() {
        Platform.runLater(() -> {
            pauseMenu.showPauseMenu(); // First show the menu
            pauseMenu.hidePauseMenu();
            assertFalse(pauseMenu.isVisible(), "PauseMenu should be invisible after calling hidePauseMenu");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}