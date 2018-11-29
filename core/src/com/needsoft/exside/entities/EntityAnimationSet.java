package com.needsoft.exside.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.needsoft.exside.types.Direction;

public abstract class EntityAnimationSet {

	protected float idleAnimationDuration;
	protected float walkingAnimationDuration;
	
	protected Map<Direction, Animation<TextureRegion>> idle;
	protected Map<Direction, Animation<TextureRegion>> walking;
	
	
	public EntityAnimationSet(final Sprite sprite) {
		idleAnimationDuration = .2f;
		walkingAnimationDuration = .2f;
		
		idle = new HashMap<Direction, Animation<TextureRegion>>();
		walking = new HashMap<Direction, Animation<TextureRegion>>();
	}
	
	public abstract Animation<TextureRegion> getIdle(final Direction direction);
	public abstract Animation<TextureRegion> getWalking(final Direction direction);

}
