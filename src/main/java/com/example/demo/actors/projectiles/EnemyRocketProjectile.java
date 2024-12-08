package com.example.demo.actors.projectiles;

/**
 * Represents a rocket projectile fired by enemy actors in the game.
 * This class defines the behavior of an enemy rocket, including its movement and collision handling.
 */
public class EnemyRocketProjectile extends Projectile {

	// Constant values for the projectile's attributes
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 30;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final int HEALTH = 1;

	/**
	 * Constructs an instance of an EnemyRocketProjectile at the specified position.
	 *
	 * @param initialXPos the initial X position of the rocket.
	 * @param initialYPos the initial Y position of the rocket.
	 */
	public EnemyRocketProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
	}

	/**
	 * Updates the position of the rocket. The rocket moves horizontally at a fixed velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
}
