package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	private static final int SHIELD_SIZE = 60;
	public int health;
	
	public ShieldImage(double xPosition, double yPosition, int initialHealth) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
//		this.setImage(new Image(IMAGE_NAME));
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
		this.health = initialHealth;
	}

	public void showShield() {
		this.setVisible(true);
	}
	
	public void hideShield() {
		this.setVisible(false);
	}

	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			hideShield();
		}
	}

	public int getHealth() {
		return health;
	}

	public void setPosition(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
}