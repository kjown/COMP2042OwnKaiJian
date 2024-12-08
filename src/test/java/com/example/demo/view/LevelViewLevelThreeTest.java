// LevelViewLevelThreeTest.java
package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LevelViewLevelThreeTest extends ApplicationTest {

    private LevelViewLevelThree levelViewLevelThree;
    private Group root;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            root = new Group();
            levelViewLevelThree = new LevelViewLevelThree(root, 3);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testLevelViewLevelThreeInitialization() {
        Platform.runLater(() -> {
            assertNotNull(levelViewLevelThree, "LevelViewLevelThree should be initialized");
            assertNotNull(root, "Root group should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}