package com.example.demo.actors.enemies;

import com.example.demo.view.LevelViewLevelBoss;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.*;

class BossTest extends ApplicationTest {

    private Boss boss;
    private LevelViewLevelBoss levelView;

    @Override
    public void start(Stage stage) {
        // This method is required by ApplicationTest to initialize JavaFX
    }

    @BeforeEach
    void setUp() {
        Group root = new Group();
        int heartsToDisplay = 5;
        levelView = new LevelViewLevelBoss(root, heartsToDisplay);

        boss = new Boss(levelView);
    }

    @Test
    void testConstructor() {
        assertEquals(1000.0, boss.getLayoutX(), "Initial X position should be 1000.0");
        assertEquals(400.0, boss.getLayoutY(), "Initial Y position should be 400.0");
        assertEquals(10, boss.getHealth(), "Initial health should be 10");
    }

    @Test
    void testUpdatePosition() {
        double initialY = boss.getLayoutY() + boss.getTranslateY();
        boolean positionChanged = false;

        for (int i = 0; i < 100; i++) {
            boss.updatePosition();
            double newY = boss.getLayoutY() + boss.getTranslateY();
            if (newY != initialY) {
                positionChanged = true;
                break;
            }
        }

        assertTrue(positionChanged, "The Boss's total Y position should change after multiple updates.");
    }

    @Test
    void testTakeDamage() {
        boss.takeDamage();
        assertEquals(9, boss.getHealth(), "Health should decrease by 1 after taking damage");
    }

    @Test
    void testFireProjectile() {
        boolean projectileFired = false;
        for (int i = 0; i < 100; i++) {
            if (boss.fireProjectile() != null) {
                projectileFired = true;
                break;
            }
        }
        assertTrue(projectileFired, "Projectile should be fired at least once in 100 attempts");
    }

    @Test
    void testShieldActivation() {
        boolean shieldActivated = false;
        for (int i = 0; i < 1000; i++) {
            boss.updateActor();
            if (boss.isShielded()) {
                shieldActivated = true;
                break;
            }
        }
        assertTrue(shieldActivated, "Shield should be activated at least once in 1000 attempts");
    }
}