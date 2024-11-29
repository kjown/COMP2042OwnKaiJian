package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectiles.LaserProjectile;

/**
 * Represents an enemy fighter plane (Enemy5) in the game.
 * This class extends the FighterPlane class and defines specific behavior for the Enemy5,
 * including horizontal movement and firing a burst of projectiles at a certain rate.
 */
public class Enemy5 extends FighterPlane {

    // Constant values for Enemy5's attributes
    private static final String IMAGE_NAME = "enemyplane5.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 3;

    // Burst firing mechanics
    private int burstCounter = 0;
    private static final int BURST_SIZE = 3; // Number of projectiles in a burst
    private static final double BURST_RATE = 0.05; // Rate to fire projectiles per burst

    /**
     * Constructs an instance of Enemy5 with the given initial position.
     *
     * @param initialXPos the initial X position of the enemy plane.
     * @param initialYPos the initial Y position of the enemy plane.
     */
    public Enemy5(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * Updates the position of the enemy fighter plane. The enemy moves horizontally
     * at a constant velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a burst of projectiles. The number of projectiles in the burst is defined by
     * the BURST_SIZE constant. The firing probability for each projectile in the burst
     * is determined by the BURST_RATE constant.
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

        // Reset burst after all projectiles in the burst have been fired
        if (burstCounter >= BURST_SIZE) {
            burstCounter = 0; // Reset the counter for the next burst
        }

        return null;
    }

    /**
     * Updates the enemy's behavior, including moving the enemy and possibly firing a burst of projectiles.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
