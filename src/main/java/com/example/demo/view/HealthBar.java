package com.example.demo.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a health bar UI component for tracking an entity's health.
 * The health bar is displayed as a rectangular bar that fills up with a color
 * (e.g., red) as the entity's health increases. It also has a background bar
 * to show the maximum health.
 */
public class HealthBar {

    // Constant values for the size of the health bar
    private static final double BAR_WIDTH = 100;
    private static final double BAR_HEIGHT = 10;

    private final Rectangle backgroundBar;
    private final Rectangle healthBar;

    /**
     * Creates a new HealthBar with the specified position and health.
     * This constructor initializes the background and foreground health bars
     * and places them at the specified position.
     *
     * @param initialX The initial X position of the health bar on the screen.
     * @param initialY The initial Y position of the health bar on the screen.
     * @param maxHealth The maximum health of the entity this health bar represents.
     *                  The health bar will fill according to the current health percentage.
     */
    public HealthBar(double initialX, double initialY, int maxHealth) {
        // Create the background bar for the health bar
        backgroundBar = new Rectangle(initialX, initialY, BAR_WIDTH, BAR_HEIGHT);
        backgroundBar.setFill(Color.GRAY); // Set the background color to gray

        // Create the health bar itself, representing the current health
        healthBar = new Rectangle(initialX, initialY, BAR_WIDTH, BAR_HEIGHT);
        healthBar.setFill(Color.RED); // Set the health color to red
    }

    /**
     * Updates the current health value of the health bar.
     * The health bar's width will be adjusted based on the percentage of current health
     * relative to the maximum health.
     *
     * @param currentHealth The current health value of the entity.
     * @param maxHealth The maximum health value of the entity.
     *                  The health bar width is calculated as the ratio of currentHealth/maxHealth.
     */
    public void updateHealth(int currentHealth, int maxHealth) {
        // Calculate the percentage of health left and adjust the width of the health bar accordingly
        double healthPercentage = (double) currentHealth / maxHealth;
        healthBar.setWidth(BAR_WIDTH * healthPercentage); // Adjust the width of the health bar
    }

    /**
     * Adds the HealthBar components (background bar and health bar) to the specified JavaFX Group.
     * This method adds both the background and health bars as children of the given group,
     * allowing them to be rendered within a JavaFX scene.
     *
     * @param group The {@link javafx.scene.Group} to which the health bar components will be added.
     *              This is typically a part of the scene where graphical elements are displayed.
     */
    public void addToScene(javafx.scene.Group group) {
        group.getChildren().addAll(backgroundBar, healthBar);
    }

    /**
     * Updates the position of the health bar.
     * This method moves both the background and the health bar to a new position on the screen.
     *
     * @param x The new X position of the health bar.
     * @param y The new Y position of the health bar.
     *          The position is updated for both the background and the health bar itself.
     */
    public void updatePosition(double x, double y) {
        backgroundBar.setX(x);
        backgroundBar.setY(y);
        healthBar.setX(x);
        healthBar.setY(y);
    }

    /**
     * Returns the current width of the health bar.
     * The width of the health bar is dependent on the entity's current health.
     *
     * @return the current width of the health bar, which represents the current health percentage.
     */
    public double getHealthBarWidth() {
        return healthBar.getWidth();
    }

    /**
     * Returns the current position of the health bar.
     *
     * @return an array containing the X and Y coordinates of the health bar's position.
     */
    public double[] getPosition() {
        return new double[]{backgroundBar.getX(), backgroundBar.getY()};
    }
}
