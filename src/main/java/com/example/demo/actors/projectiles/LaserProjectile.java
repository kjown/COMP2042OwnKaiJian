package com.example.demo.actors.projectiles;

public class LaserProjectile extends Projectile {

    private static final String IMAGE_NAME = "laser.png";
    private static final int IMAGE_HEIGHT = 30;
    private static final int HORIZONTAL_VELOCITY = -7;  // Controls horizontal speed
    private static final int HEALTH = 1;

    public LaserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
    }

    @Override
    public void updatePosition() {
        // Move the projectile horizontally
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
