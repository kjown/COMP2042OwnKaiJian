package com.example.demo.actors.projectiles;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.controller.Main;

/**
 * Represents a generic projectile in the game. This class serves as the base class
 * for all projectile types (such as bullets, lasers, rockets) in the game.
 * The projectile can move, take damage, and be destroyed when it goes out of bounds or its health reaches zero.
 */
public abstract class Projectile extends ActiveActorDestructible {
	private int health;

	/**
	 * Constructs a new instance of a Projectile.
	 *
	 * @param imageName the name of the image representing the projectile.
	 * @param imageHeight the height of the projectile's image.
	 * @param initialXPos the initial X position of the projectile.
	 * @param initialYPos the initial Y position of the projectile.
	 * @param health the initial health of the projectile. The projectile is destroyed when health reaches zero.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Called when the projectile takes damage. Decreases the health of the projectile
	 * and destroys it if health reaches zero.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();  // Destroy the projectile when health reaches zero
		}
	}

	/**
	 * Abstract method for updating the position of the projectile.
	 * Each specific projectile type (e.g., laser, bullet) will implement its own version
	 * of this method to move in a particular way.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state of the projectile. This includes updating its position
	 * and checking if the projectile is out of bounds. If the projectile is out of bounds,
	 * it will be destroyed.
	 */
	public void updateActor() {
		updatePosition();
		if (outOfBounds()) {
			this.destroy();  // Destroy the projectile if it goes out of bounds
		}
	}

	/**
	 * Checks if the projectile is out of bounds. The projectile is considered out of bounds
	 * if it moves beyond the screen width.
	 *
	 * @return true if the projectile is out of bounds, false otherwise.
	 */
	public boolean outOfBounds() {
		return getTranslateX() > Main.getScreenWidth();  // Checks if the projectile is past the screen width
	}

	/**
	 * Checks if the projectile's health has reached zero.
	 *
	 * @return true if the health is zero, false otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the projectile.
	 *
	 * @return the current health of the projectile.
	 */
	public int getHealth() {
		return health;
	}
}
