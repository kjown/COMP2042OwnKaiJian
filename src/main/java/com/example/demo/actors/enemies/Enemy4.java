package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectiles.LaserProjectile;

/**
 * The Enemy4 class represents an advanced enemy fighter plane that moves horizontally
 * and fires laser projectiles at a random rate. It extends from the FighterPlane class.
 */
public class Enemy4 extends FighterPlane {

    // Constant values for Enemy4's attributes
    private static final String IMAGE_NAME = "enemyplane4.png";
    private static final int IMAGE_HEIGHT = 200;
    private static final int HORIZONTAL_VELOCITY = -2;
    private static final double PROJECTILE_X_POSITION_OFFSET = -50.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 20;
    private static final double FIRE_RATE = 0.05;

    // Reference to the user (player)
    private ActiveActorDestructible user;

    /**
     * Constructs an Enemy4 object with the specified initial position.
     *
     * @param initialXPos The initial X position of the enemy plane.
     * @param initialYPos The initial Y position of the enemy plane.
     */
    public Enemy4(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        this.user = user;
    }

    /**
     * Updates the position of the enemy plane. The plane moves horizontally
     * with a constant velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a laser projectile with a certain probability based on the fire rate.
     *
     * @return A new LaserProjectile if fired, otherwise null.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);

            return new LaserProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    /**
     * Updates the enemy actor's behavior, including movement and projectile firing.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Public wrapper method to get the projectile X position.
     *
     * @param xPositionOffset The X position offset.
     * @return The projectile X position.
     */
    public double getProjectileXPosition(double xPositionOffset) {
        return getProjectileXPosition(xPositionOffset);
    }

    /**
     * Public wrapper method to get the projectile Y position.
     *
     * @param yPositionOffset The Y position offset.
     * @return The projectile Y position.
     */
    public double getProjectileYPosition(double yPositionOffset) {
        return getProjectileYPosition(yPositionOffset);
    }
}
