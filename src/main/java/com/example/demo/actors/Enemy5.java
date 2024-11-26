package com.example.demo.actors;

import com.example.demo.actors.projectiles.LaserProjectile;

/**
 * Represents an enemy fighter plane (Enemy5) in the game.
 * This class extends the FighterPlane class and defines specific behavior for the Enemy5, including movement and projectile firing.
 */
public class Enemy5 extends FighterPlane {

    private static final String IMAGE_NAME = "enemyplane5.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 3;

    private int burstCounter = 0;
    private static final int BURST_SIZE = 3; // Number of projectiles in a burst
    private static final double BURST_RATE = 0.05; // Rate to fire projectiles per burst

    /**
     * Constructs an instance of Enemy1 with the given initial position.
     *
     * @param initialXPos the initial x position of the enemy.
     * @param initialYPos the initial y position of the enemy.
     */
    public Enemy5(double initialXPos, double initialYPos) {
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
     * @return a new instance of a LaserProjectile, or null if no projectile is fired.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (burstCounter < BURST_SIZE && Math.random() < BURST_RATE) {
            // Fire a projectile
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            burstCounter++;  // Increment the burst counter

            // Return the fired projectile
            return new LaserProjectile(projectileXPosition, projectileYPosition);
        }

        // Reset burst after a burst is complete
        if (burstCounter >= BURST_SIZE) {
            burstCounter = 0; // Reset the counter for the next burst
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
}
