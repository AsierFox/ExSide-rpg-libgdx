package com.needsoft.exside.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.needsoft.exside.interfaces.Renderable;
import com.needsoft.exside.screens.PlayingScreen;

public abstract class Entity extends Sprite implements Renderable {

	public boolean isVisible;
	
	public Vector2 vel;
	
	protected float gravity;
	protected float speed;
	
	protected boolean canJump;
	
	protected PlayingScreen belongsToLevel;
	
	
	public Entity(final Texture texture) {
		super(texture);
	}
	
	public Entity(final TextureRegion textureRegion) {
		super(textureRegion);
	}
	
	// TODO Move from Screen to levels in a future
	@Override
	public void init(final float x, final float y, final PlayingScreen level) {
		belongsToLevel = level;
		
		vel = new Vector2();

		setX(x);
		setY(y);
		
		isVisible = true;
		
		gravity = 60 * 1.8f;
		speed = 60 * 2;
		
		canJump = false;
	}
	
	// TODO Move to the map class
	protected boolean isTileSolid(final float x, final float y) {
		final Cell cell = belongsToLevel.collisionLayer.getCell((int) (x / belongsToLevel.collisionLayer.getTileWidth()),
				(int) (y / belongsToLevel.collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().get("isSolid") != null
				&& ((Boolean) (cell.getTile().getProperties().get("isSolid"))).booleanValue();
	}

	protected boolean isRightCollision() {
		for (float i = 0; i < getHeight(); i += belongsToLevel.collisionLayer.getTileHeight() / 2) {
			if (isTileSolid(getX() + getWidth(), getY() + i)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isLeftCollision() {
		for (float i = 0; i < getHeight(); i += belongsToLevel.collisionLayer.getTileHeight() / 2) {
			if (isTileSolid(getX(), getY() + i)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isTopCollision() {
		for (float i = 0; i < getWidth(); i += belongsToLevel.collisionLayer.getTileWidth() / 2) {
			if (isTileSolid(getX() + i, getY() + getHeight())) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isBottomCollision() {
		for (float i = 0; i < getWidth(); i += belongsToLevel.collisionLayer.getTileWidth() / 2) {
			if (isTileSolid(getX() + i, getY())) {
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
