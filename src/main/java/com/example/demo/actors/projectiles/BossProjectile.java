package com.example.demo.actors.projectiles;

/**
 * Represents a projectile fired by the Boss in the game.
 * The projectile moves horizontally from right to left at a fixed velocity.
 * This class extends the {@link Projectile} class, inheriting its basic behavior
 * while adding specific properties like velocity and image.
 */
public class BossProjectile extends Projectile {

	/** The image file name representing the projectile's appearance. */
	private static final String IMAGE_NAME = "fireball.png";

	/** The height of the projectile's image. */
	private static final int IMAGE_HEIGHT = 30;

	/** The horizontal velocity at which the projectile moves (negative for leftward movement). */
	private static final int HORIZONTAL_VELOCITY = -15;

	/** The initial X position from where the projectile is fired. */
	private static final int INITIAL_X_POSITION = 950;

	/** The health of the projectile, indicating how many hits it can take (default is 1). */
	private static final int HEALTH = 1;

	/**
	 * Constructs a new BossProjectile at a given initial Y position.
	 *
	 * @param initialYPos The initial Y position for the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, HEALTH);
	}

	/**
	 * Updates the position of the projectile.
	 * The projectile moves horizontally to the left based on its fixed velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
}
