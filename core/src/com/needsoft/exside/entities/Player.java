package com.needsoft.exside.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	
	public Player() {
		super(new TextureRegion(new Texture(Gdx.files.internal("entities/player/player.png")), 30, 50));
	}
	
	@Override
	public void update(float delta) {
		applyGravity(delta);
		
		if (Gdx.input.isKeyJustPressed(Keys.W)) {
			vel.y = speed * 15;
		}
		else if (Gdx.input.isKeyPressed(Keys.S)) {
			vel.y = -speed;
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			vel.x = -speed;
		}
		else if (Gdx.input.isKeyPressed(Keys.D)) {
			vel.x = speed;
		}
		else {
			vel.x = 0;
		}
		
		System.out.println(vel);
		
		float oldX = getX();
		float oldY = getY();
		boolean isXCollision = false;
		boolean isYCollision = false;
		
		setX(getX() + vel.x * delta);
		
		if (vel.x < 0) {
			isXCollision = isLeftCollision();
		}
		else if (vel.x > 0) {
			isXCollision = isRightCollision();
		}
		
		if (isXCollision) {
			setX(oldX);
			vel.x = 0;
		}

		setY(getY() + vel.y * delta);
		
		if (vel.y > 0) {
			isYCollision = isTopCollision();
		}
		else if (vel.y < 0) {
			isYCollision = isBottomCollision();
		}
		
		if (isYCollision) {
			setY(oldY);
			vel.y = 0;
		}
		
		System.out.println("Collides X: " + isXCollision + " // Collides Y: " + isYCollision);
	}

	private void applyGravity(float delta) {
		vel.y -= gravity * delta;
		
		if (vel.y > speed) {
			vel.y = speed;
		}
		else if (vel.y  < speed) {
			vel.y = -speed;
		}
	}
	
	@Override
	public void render(Batch batch) {
		batch.draw(this, getX(), getY());
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
}
