package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelViewTest extends ApplicationTest {

    private LevelView levelView;
    private Group root;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            root = new Group();
            levelView = new LevelView(root, 3);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testLevelViewInitialization() {
        Platform.runLater(() -> {
            assertNotNull(levelView, "LevelView should be initialized");
            assertNotNull(root, "Root group should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowHeartDisplay() {
        Platform.runLater(() -> {
            levelView.showHeartDisplay();
            assertEquals(1, root.getChildren().size(), "Root should contain the heart display");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowWinImage() {
        Platform.runLater(() -> {
            levelView.showWinImage();
            assertEquals(1, root.getChildren().size(), "Root should contain the win image");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testRemoveHearts() {
        Platform.runLater(() -> {
            levelView.showHeartDisplay();
            levelView.removeHearts(1);
            assertEquals(1, levelView.getHeartDisplay().getContainer().getChildren().size(), "Heart display should have 1 heart remaining");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowPauseMenuImage() {
        Platform.runLater(() -> {
            levelView.showPauseMenuImage();
            assertTrue(root.getChildren().contains(levelView.getPauseMenu()), "Root should contain the pause menu");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testHidePauseMenuImage() {
        Platform.runLater(() -> {
            levelView.showPauseMenuImage();
            levelView.hidePauseMenuImage();
            assertTrue(root.getChildren().contains(levelView.getPauseMenu()), "Pause menu should be hidden but still in the root");
            assertTrue(!levelView.getPauseMenu().isVisible(), "Pause menu should be invisible");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}