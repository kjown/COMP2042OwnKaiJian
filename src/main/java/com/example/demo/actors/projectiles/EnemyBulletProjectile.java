package com.example.demo.actors.projectiles;

/**
 * Represents a projectile fired by enemy actors in the game.
 * This class defines the behavior of an enemy's bullet, including its movement and collision handling.
 */
public class EnemyBulletProjectile extends Projectile {

    // Constant values for the projectile's attributes
    private static String IMAGE_NAME = "enemyBullet.png";
    private static int IMAGE_HEIGHT = 10;
    private static int HORIZONTAL_VELOCITY = -13;
    private static int HEALTH = 1;

    /**
     * Constructs an instance of an EnemyBulletProjectile at the specified position.
     *
     * @param initialXPos the initial X position of the projectile.
     * @param initialYPos the initial Y position of the projectile.
     */
    public EnemyBulletProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
    }

    /**
     * Updates the position of the projectile. The projectile moves horizontally at a fixed velocity.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }
}
