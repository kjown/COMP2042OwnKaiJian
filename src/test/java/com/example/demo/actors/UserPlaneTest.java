package com.example.demo.actors;

import javafx.application.Platform;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.*;

class UserPlaneTest extends ApplicationTest {

    private UserPlane userPlane;

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> userPlane = new UserPlane(5));
        waitForFxEvents(); // Ensures the JavaFX thread completes initialization
    }

    @Test
    void testBottomBoundary() {
        Platform.runLater(() -> {
            userPlane.setLayoutY(600); // Assume 600 is the bottom limit
            userPlane.moveDown(); // Try moving down further
            userPlane.updatePosition();
            waitForFxEvents();

            // Assert position doesn't go beyond the bottom boundary
            assertEquals(600, userPlane.getLayoutY(), "The plane should stay within the bottom boundary");
        });

        waitForFxEvents();
    }


    @Test
    void testTakeDamage() {
        Platform.runLater(() -> {
            int initialHealth = userPlane.getHealth();
            userPlane.takeDamage();
            assertEquals(initialHealth - 1, userPlane.getHealth(), "Health should decrease by 1 after taking damage");
        });
        waitForFxEvents();
    }

    @Test
    void testFireProjectile() {
        Platform.runLater(() -> {
            assertNotNull(userPlane.fireProjectile(), "Fire projectile should return a non-null object");
        });
        waitForFxEvents();
    }

    @Test
    void testActivateShield() {
        Platform.runLater(() -> {
            userPlane.incrementKillCount();
            userPlane.incrementKillCount();
            assertTrue(userPlane.isShieldActive(), "Shield should be active after required kills");
        });
        waitForFxEvents();
    }

    @Test
    void testDeactivateShield() throws InterruptedException {
        Platform.runLater(() -> {
            userPlane.incrementKillCount();
            userPlane.incrementKillCount();
        });
        waitForFxEvents();

        Thread.sleep(6000); // Wait for shield duration to expire
        Platform.runLater(userPlane::updateActor);
        waitForFxEvents();

        assertFalse(userPlane.isShieldActive(), "Shield should be deactivated after duration expires");
    }

    private void waitForFxEvents() {
        try {
            Thread.sleep(200); // Increase the sleep duration if necessary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
