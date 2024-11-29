package com.example.demo.actors.projectiles;

/**
 * Represents a projectile fired by the user in the game.
 * This class extends the Projectile class and defines the specific behavior for the user's projectile,
 * including movement and projectile properties.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";  // Image representing the user's projectile
	private static final int IMAGE_HEIGHT = 10;  // Height of the projectile's image
	private static final int HORIZONTAL_VELOCITY = 15;  // Horizontal speed of the projectile
	private static final int HEALTH = 1;  // Health of the projectile (affects its destruction)

	/**
	 * Constructs an instance of a UserProjectile with the given initial position.
	 *
	 * @param initialXPos the initial X position of the projectile.
	 * @param initialYPos the initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
	}

	/**
	 * Updates the position of the user projectile. The projectile moves horizontally to the right.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);  // Move the projectile to the right
	}
}