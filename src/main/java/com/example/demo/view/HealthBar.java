package com.example.demo.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a health bar UI component for tracking an entity's health.
 */
public class HealthBar {

    private static final double BAR_WIDTH = 100;
    private static final double BAR_HEIGHT = 10;
    private final Rectangle backgroundBar;
    private final Rectangle healthBar;

    /**
     * Creates a new HealthBar with the specified position and health.
     *
     * @param initialX The initial X position of the health bar.
     * @param initialY The initial Y position of the health bar.
     * @param maxHealth The maximum health of the entity.
     */
    public HealthBar(double initialX, double initialY, int maxHealth) {
        backgroundBar = new Rectangle(initialX, initialY, BAR_WIDTH, BAR_HEIGHT);
        backgroundBar.setFill(Color.GRAY); // Background color

        healthBar = new Rectangle(initialX, initialY, BAR_WIDTH, BAR_HEIGHT);
        healthBar.setFill(Color.RED); // Color for health
    }

    /**
     * Updates the current health value of the health bar.
     *
     * @param currentHealth The current health of the entity.
     * @param maxHealth The maximum health of the entity.
     */
    public void updateHealth(int currentHealth, int maxHealth) {
        double healthPercentage = (double) currentHealth / maxHealth;
        healthBar.setWidth(BAR_WIDTH * healthPercentage); // Adjust the width based on health
    }

    /**
     * Adds the HealthBar components to the specified JavaFX Group.
     * This method includes both the background bar and the health bar,
     * adding them as children to the given Group.
     *
     * @param group The {@link javafx.scene.Group} to which the health bar
     *              components will be added.
     */
    public void addToScene(javafx.scene.Group group) {
        group.getChildren().addAll(backgroundBar, healthBar);
    }

    /**
     * Updates the position of the health bar.
     *
     * @param x The new X position.
     * @param y The new Y position.
     */
    public void updatePosition(double x, double y) {
        backgroundBar.setX(x);
        backgroundBar.setY(y);
        healthBar.setX(x);
        healthBar.setY(y);
    }
}
