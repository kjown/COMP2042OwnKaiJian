// LevelViewLevelTwoTest.java
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

class LevelViewLevelTwoTest extends ApplicationTest {

    private LevelViewLevelTwo levelViewLevelTwo;
    private Group root;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            root = new Group();
            levelViewLevelTwo = new LevelViewLevelTwo(root, 3);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testLevelViewLevelTwoInitialization() {
        Platform.runLater(() -> {
            assertNotNull(levelViewLevelTwo, "LevelViewLevelTwo should be initialized");
            assertNotNull(root, "Root group should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowHeartDisplay() {
        Platform.runLater(() -> {
            levelViewLevelTwo.showHeartDisplay();
            assertEquals(1, root.getChildren().size(), "Root should contain the heart display");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowWinImage() {
        Platform.runLater(() -> {
            levelViewLevelTwo.showWinImage();
            assertEquals(1, root.getChildren().size(), "Root should contain the win image");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testRemoveHearts() {
        Platform.runLater(() -> {
            levelViewLevelTwo.showHeartDisplay();
            levelViewLevelTwo.removeHearts(1);
            assertEquals(1, levelViewLevelTwo.getHeartDisplay().getContainer().getChildren().size(), "Heart display should have 1 heart remaining");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowPauseMenuImage() {
        Platform.runLater(() -> {
            levelViewLevelTwo.showPauseMenuImage();
            assertTrue(root.getChildren().contains(levelViewLevelTwo.getPauseMenu()), "Root should contain the pause menu");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testHidePauseMenuImage() {
        Platform.runLater(() -> {
            levelViewLevelTwo.showPauseMenuImage();
            levelViewLevelTwo.hidePauseMenuImage();
            assertTrue(root.getChildren().contains(levelViewLevelTwo.getPauseMenu()), "Pause menu should be hidden but still in the root");
            assertTrue(!levelViewLevelTwo.getPauseMenu().isVisible(), "Pause menu should be invisible");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}