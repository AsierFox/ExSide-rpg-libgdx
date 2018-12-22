package com.needsoft.exside.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.needsoft.exside.interfaces.Renderable;
import com.needsoft.exside.screens.PlayingScreen;
import com.needsoft.exside.types.Direction;
import com.needsoft.exside.types.EntityState;

public abstract class Entity extends Sprite implements Renderable {

	public boolean isVisible;
	
	public Vector2 vel;
	
	public Rectangle collider;
	
	public Direction direction;
	public EntityState currentState;
	public EntityState previousState;
	
	protected float gravity;
	protected float speed;
	
	protected float animationTimer;
	
	protected boolean canJump;
	
	protected PlayingScreen belongsToLevel;
	
	
	public Entity(final Texture texture) {
		super(texture);
	}
	
	public Entity(final TextureRegion textureRegion) {
		super(textureRegion);
		collider = new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	// TODO Move from Screen to levels in a future
	@Override
	public void init(final float x, final float y, final PlayingScreen level) {
		belongsToLevel = level;
		
		vel = new Vector2();

		setX(x);
		setY(y);
		
		direction = Direction.NORTH;
		currentState = EntityState.IDLE;
		previousState = EntityState.IDLE;
		
		// TODO Set anchor point to the center
		
		isVisible = true;
		
		gravity = 60 * 1.8f;
		speed = 90;
		
		animationTimer = 0;
		
		canJump = false;
	}

	protected void updateCollider() {
		collider.x = getX();
		collider.y = getY();
	}

	// TODO Move to the map class
	protected boolean isTileSolid(final float x, final float y) {
		final Cell cell = belongsToLevel.getMapCollisionLayer().getCell((int) (x / belongsToLevel.getMapCollisionLayer().getTileWidth()),
				(int) (y / belongsToLevel.getMapCollisionLayer().getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("isSolid")
				&& cell.getTile().getProperties().get("isSolid", Boolean.class).booleanValue();
	}

	protected boolean isRightCollision() {
		for (float i = 0; i < collider.getHeight(); i += belongsToLevel.getMapCollisionLayer().getTileHeight() / 2) {
			if (isTileSolid(collider.getX() + collider.getWidth(), collider.getY() + i)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isLeftCollision() {
		for (float i = 0; i < collider.getHeight(); i += belongsToLevel.getMapCollisionLayer().getTileHeight() / 2) {
			if (isTileSolid(collider.getX(), collider.getY() + i)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isTopCollision() {
		for (float i = 0; i < collider.getWidth(); i += belongsToLevel.getMapCollisionLayer().getTileWidth() / 2) {
			if (isTileSolid(collider.getX() + i, collider.getY() + collider.getHeight())) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isBottomCollision() {
		for (float i = 0; i < collider.getWidth(); i += belongsToLevel.getMapCollisionLayer().getTileWidth() / 2) {
			if (isTileSolid(collider.getX() + i, collider.getY())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isVisible() {
		return isVisible;
	}
	
	@Override
	public void dispose() {
		getTexture().dispose();
	}
	
}
