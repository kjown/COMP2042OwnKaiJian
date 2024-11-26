package com.example.demo.actors.enemies;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.projectiles.LaserProjectile;

public class Enemy4 extends FighterPlane {

    private static final String IMAGE_NAME = "enemyplane4.png";
    private static final int IMAGE_HEIGHT = 200;
    private static final int HORIZONTAL_VELOCITY = -2;
    private static final double PROJECTILE_X_POSITION_OFFSET = -50.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 20;
    private static final double FIRE_RATE = 0.05;

    private ActiveActorDestructible user;  // reference to the player (user)

    public Enemy4(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        this.user = user;
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);

            return new LaserProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
