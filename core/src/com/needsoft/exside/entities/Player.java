package com.needsoft.exside.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity implements InputProcessor {
	
	public Player() {
		super(new TextureRegion(new Texture(Gdx.files.internal("entities/player/player.png")), 30, 50));
	}
	
	@Override
	public void update(float delta) {
		//applyGravity(delta);
		
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

		setY(getY() + vel.y * delta * 5f);
		
		if (vel.y > 0) {
			isYCollision = isTopCollision();
		}
		else if (vel.y < 0) {
			isYCollision = isBottomCollision();
			
			if (isYCollision) {
				canJump = true;
			}
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
		else if (vel.y  < -speed) {
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

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.W:
			if (canJump) {
				vel.y = speed;
				canJump = false;
			}
			break;
		case Keys.D:
			vel.x = speed;
			break;
		case Keys.A:
			vel.x = -speed;
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.D:
		case Keys.A:
			vel.x = 0;
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
