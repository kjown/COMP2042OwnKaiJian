// LevelViewLevelBossTest.java
package com.example.demo.view;

import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelViewLevelBossTest extends ApplicationTest {

    private LevelViewLevelBoss levelViewLevelBoss;
    private Group root;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            root = new Group();
            levelViewLevelBoss = new LevelViewLevelBoss(root, 3);
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testLevelViewLevelBossInitialization() {
        Platform.runLater(() -> {
            assertNotNull(levelViewLevelBoss, "LevelViewLevelBoss should be initialized");
            assertNotNull(root, "Root group should be initialized");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testShowShield() {
        Platform.runLater(() -> {
            levelViewLevelBoss.showShield();
            assertTrue(root.getChildren().contains(levelViewLevelBoss.getShieldImage()), "Root should contain the shield image");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    void testHideShield() {
        Platform.runLater(() -> {
            levelViewLevelBoss.showShield();
            levelViewLevelBoss.hideShield();
            assertTrue(root.getChildren().contains(levelViewLevelBoss.getShieldImage()), "Shield image should be hidden but still in the root");
            assertTrue(!levelViewLevelBoss.getShieldImage().isVisible(), "Shield image should be invisible");
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}