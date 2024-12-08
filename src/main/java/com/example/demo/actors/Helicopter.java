package com.example.demo.actors;

import com.example.demo.controller.AudioManager;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * The Helicopter class represents an enemy helicopter in the game.
 * It extends from ActiveActorDestructible and provides functionality for firing projectiles, taking damage,
 * and handling health and destruction logic.
 */
public abstract class Helicopter extends ActiveActorDestructible {

    // The health of the helicopter
    public int health;

    /**
     * Constructs a Helicopter object with the specified image name, image height, initial position, and health.
     *
     * @param imageName The image file name for the helicopter sprite.
     * @param imageHeight The height of the helicopter image.
     * @param initialXPos The initial X position of the helicopter.
     * @param initialYPos The initial Y position of the helicopter.
     * @param health The initial health of the helicopter.
     */
    public Helicopter(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    /**
     * Abstract method for firing a projectile from the helicopter.
     * This method should be implemented by subclasses to define the specific behavior of firing projectiles.
     *
     * @return An instance of ActiveActorDestructible representing the fired projectile.
     */
    public abstract ActiveActorDestructible fireProjectile();

    /**
     * Handles the logic for taking damage. Reduces the health of the helicopter and plays a hit marker sound.
     * If the health reaches zero, the helicopter is destroyed and a destruction sound is played.
     */
    @Override
    public void takeDamage() {
        health--;
        // Play hit marker sound
        AudioManager.getInstance().playSoundEffect("/com/example/demo/music/hitmarker.mp3");

        if (healthAtZero()) {
            AudioManager.getInstance().playSoundEffect("/com/example/demo/music/destroyedsound.wav");
            this.destroy();
        }
    }

    /**
     * Calculates the X position for firing a projectile, adjusted by an offset.
     *
     * @param xPositionOffset The X position offset for the projectile.
     * @return The calculated X position for the projectile.
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Calculates the Y position for firing a projectile, adjusted by an offset.
     *
     * @param yPositionOffset The Y position offset for the projectile.
     * @return The calculated Y position for the projectile.
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Checks if the health of the helicopter has reached zero.
     *
     * @return true if health is zero, false otherwise.
     */
    private boolean healthAtZero() {
        return health == 0;
    }

    /**
     * Gets the current health of the helicopter.
     *
     * @return The current health of the helicopter.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Overrides the destroy method to show an explosion before removing the object.
     */
    @Override
    public void destroy() {
        Platform.runLater(() -> {
            try {
                Image explosionImage = new Image(getClass().getResource("/com/example/demo/images/explode.png").toExternalForm());
                this.setImage(explosionImage);
            } catch (NullPointerException e) {
                System.err.println("Resource not found: /com/example/demo/images/explode.png");
                e.printStackTrace();
            }
        });

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(super::destroy);
        }).start();
    }
}
