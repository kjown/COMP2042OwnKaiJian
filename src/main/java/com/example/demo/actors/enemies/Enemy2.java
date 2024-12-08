package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.Helicopter;
import com.example.demo.actors.projectiles.EnemyBulletProjectile;

/**
 * The Enemy2 class {@link Helicopter} represents a type of enemy helicopter in the game.
 * It extends the Helicopter class and includes logic for horizontal movement,
 * firing projectiles with a specific fire rate, and updating the position.
 */
public class Enemy2 extends Helicopter {

    // Constant values for Enemy2's attributes
    private static final String IMAGE_NAME = "enemy2.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -2;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 7;
    private static final double FIRE_RATE = .03;

    /**
     * Constructs an Enemy2 object with the specified initial position.
     *
     * @param initialXPos The initial X position of the enemy helicopter.
     * @param initialYPos The initial Y position of the enemy helicopter.
     */
    public Enemy2(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    /**
     * Updates the position of the enemy helicopter.
     * In this case, the helicopter only moves horizontally at a fixed velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
        // Uncomment and modify for vertical movement if needed
        // moveVertically();
    }

    /**
     * Fires a projectile at a certain probability based on the defined fire rate.
     * If a projectile is fired, it returns a new EnemyBulletProjectile; otherwise, returns null.
     *
     * @return A new EnemyBulletProjectile if fired, otherwise null.
     */
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            System.out.println("Firing projectile at X: " + projectileXPosition + ", Y: " + projectileYPosition);
            return new EnemyBulletProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    /**
     * Updates the enemy helicopter's behavior, including movement and projectile firing.
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
