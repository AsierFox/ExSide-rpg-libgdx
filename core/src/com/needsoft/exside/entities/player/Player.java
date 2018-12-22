package com.needsoft.exside.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.needsoft.exside.entities.Entity;
import com.needsoft.exside.screens.PlayingScreen;
import com.needsoft.exside.types.Direction;
import com.needsoft.exside.types.EntityState;

public class Player extends Entity implements InputProcessor {

	private PlayerAnimationSet animationSet;
	
	
	public Player() {
		super(new TextureRegion(new Texture(Gdx.files.internal("entities/player/player.png")), 30, 50));
		
		animationSet = new PlayerAnimationSet(this);
	}

	@Override
	public void init(float x, float y, PlayingScreen level) {
		super.init(x, y, level);

		collider.height -= 25;
	}

	@Override
	public void update(float delta) {

		//applyGravity(delta);
		
		// TODO Refactor things to Entity
		
		// Check collisions
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
			
			if (isYCollision) {
				canJump = true;
			}
		}
		
		if (isYCollision) {
			setY(oldY);
			vel.y = 0;
		}

		updateCollider();
		
		// Update state
		if (vel.x == 0 && vel.y == 0) {
			currentState = EntityState.IDLE;
		}
		else {
			currentState = EntityState.WALKING;
		}
		
		// Update animation
		
		// If the current state is the same as the previous state increase the state timer.
        // Otherwise the state has changed and we need to reset timer.
        animationTimer = currentState == previousState ? animationTimer + delta : 0;
		
		if (vel.y > 0) {
			direction = Direction.NORTH;
		}
		else if (vel.y < 0) {
			direction = Direction.SOUTH;
		}
		else if (vel.x > 0) {
			direction = Direction.EAST;
		}
		else if (vel.x < 0) {
			direction = Direction.WEST;
		}
		
		if (currentState == EntityState.IDLE) {
			setRegion(animationSet.getIdle(direction).getKeyFrame(animationTimer));
		}
		else if (currentState == EntityState.WALKING) {
			setRegion(animationSet.getWalking(direction).getKeyFrame(animationTimer, true));
		}
		
		// Update previous state
		previousState = currentState;
		
		//System.out.println("Collides X: " + isXCollision + " // Collides Y: " + isYCollision);
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
		if (keycode == Keys.W) {
			vel.y = speed;
			/*
			if (canJump) {
				vel.y = speed;
				canJump = false;
			}
			*/
		}
		else if (keycode == Keys.S) {
			vel.y = -speed;
		}
		else if (keycode == Keys.D) {
			vel.x = speed;
		}
		else if (keycode == Keys.A) {
			vel.x = -speed;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.W:
		case Keys.S:
			vel.y = 0;
			break;
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
