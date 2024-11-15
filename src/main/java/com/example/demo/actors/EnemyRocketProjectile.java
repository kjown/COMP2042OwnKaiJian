package com.example.demo.actors;

public class EnemyRocketProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 30;
	private static final int HORIZONTAL_VELOCITY = -10;
	private static final int HEALTH = 1;

	public EnemyRocketProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

//	@Override
//	public void updateActor() {
//		updatePosition();
//	}


}