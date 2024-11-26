package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.projectiles.EnemyBulletProjectile;

public class Enemy2 extends Helicopter {
    private static final String IMAGE_NAME = "enemy2.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -2;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 7;
    private static final double FIRE_RATE = .03;


    public Enemy2(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
//        moveVertically();
    }

    /**
     * Oscillates the helicopter vertically between the UPPER_BOUND and LOWER_BOUND.
     */
//    private void moveVertically() {
//        if (moveUpCounter < MOVE_UP_DURATION) {
//            // Move up while within the duration
//            setY(getY() - VERTICAL_VELOCITY); // Move up by `verticalVelocity` units
//            moveUpCounter++;
//        } else {
//            // Move down at a slower speed
//            setY(getY() + DOWNWARD_VELOCITY);
//
//            // Reset the counter and reverse direction once it reaches the lower bound
//            if (getY() >= LOWER_BOUND) {
//                moveUpCounter = 0; // Reset the counter to start moving up again
//            }
//        }
//
//        // Ensure the helicopter stays within the upper bound by reversing direction if exceeded
//        if (getY() <= UPPER_BOUND) {
//            moveUpCounter = MOVE_UP_DURATION; // Start moving down if it hits the upper bound
//        }
//    }

    /**
     * Fires a projectile with a certain probability based on the fire rate.
     * @return a new projectile if fired, otherwise null.
     */
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            System.out.println("Firing projectile at X: " + projectileXPosition + ", Y: " + projectileYPosition);
            return new EnemyBulletProjectile(projectileXPosition, projectileYPosition);
        }
        System.out.println("Projectile not fired");
        return null;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
