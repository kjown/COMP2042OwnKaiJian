package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a shield image in the game.
 * This class is used to display a shield on the screen, which can take damage and hide when its health reaches zero.
 */
public class ShieldImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 60;

	/**
	 * The current health of the shield.
	 * The shield will be hidden when health reaches zero.
	 */
	public int health;

	/**
	 * Constructs a new ShieldImage with the specified position and initial health.
	 * The shield image is initially hidden.
	 *
	 * @param xPosition The X coordinate for the shield's position.
	 * @param yPosition The Y coordinate for the shield's position.
	 * @param initialHealth The initial health value for the shield.
	 */
	public ShieldImage(double xPosition, double yPosition, int initialHealth) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Shield is hidden by default
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
		this.health = initialHealth;
	}

	/**
	 * Makes the shield visible on the screen.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield from the screen.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

	/**
	 * Reduces the shield's health by the specified damage value.
	 * If the shield's health reaches zero or below, the shield is hidden.
	 *
	 * @param damage The amount of damage to reduce from the shield's health.
	 */
	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			hideShield();
		}
	}

	/**
	 * Gets the current health of the shield.
	 *
	 * @return The current health of the shield.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the position of the shield image.
	 *
	 * @param x The new X coordinate for the shield's position.
	 * @param y The new Y coordinate for the shield's position.
	 */
	public void setPosition(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}
