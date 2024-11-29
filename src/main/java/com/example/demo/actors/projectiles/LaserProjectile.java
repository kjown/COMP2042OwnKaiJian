package com.example.demo.actors.projectiles;

/**
 * Represents a laser projectile fired by enemy or player actors in the game.
 * This class defines the behavior of a laser, including its movement and collision handling.
 */
public class LaserProjectile extends Projectile {

    // Constant values for the projectile's attributes
    private static final String IMAGE_NAME = "laser.png";
    private static final int IMAGE_HEIGHT = 30;
    private static final int HORIZONTAL_VELOCITY = -7;  // Controls horizontal speed of the laser
    private static final int HEALTH = 1;

    /**
     * Constructs an instance of a LaserProjectile at the specified position.
     *
     * @param initialXPos the initial X position of the laser.
     * @param initialYPos the initial Y position of the laser.
     */
    public LaserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
    }

    /**
     * Updates the position of the laser. The laser moves horizontally at a fixed velocity.
     */
    @Override
    public void updatePosition() {
        // Move the projectile horizontally based on the set horizontal velocity
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the actor's state. This method calls the updatePosition method to ensure
     * the laser's position is updated during each game cycle.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
