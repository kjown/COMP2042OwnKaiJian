package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectiles.EnemyRocketProjectile;

/**
 * Represents an enemy fighter plane (Enemy1) in the game.
 * This class extends the FighterPlane class and defines specific behavior for the Enemy1, including movement and projectile firing.
 */
public class Enemy1 extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane1.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an instance of Enemy1 with the given initial position.
	 *
	 * @param initialXPos the initial x position of the enemy.
	 * @param initialYPos the initial y position of the enemy.
	 */
	public Enemy1(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy, moving it horizontally.
	 * The horizontal velocity determines how fast the enemy moves across the screen.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane if the random chance is below the fire rate.
	 * The projectile's position is offset from the enemy plane's current position.
	 *
	 * @return a new instance of an EnemyRocketProjectile, or null if no projectile is fired.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyRocketProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the state of the enemy, including moving the enemy and firing projectiles.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	public double getPublicProjectileXPosition(double xPositionOffset) {
		return getProjectileXPosition(xPositionOffset);
	}

	public double getPublicProjectileYPosition(double yPositionOffset) {
		return getProjectileYPosition(yPositionOffset);
	}
}
