package com.needsoft.exside.entities.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.needsoft.exside.entities.EntityAnimationSet;
import com.needsoft.exside.types.Direction;

public class PlayerAnimationSet extends EntityAnimationSet {

	private final int TEXTURE_WIDTH = 32;
	private final int TEXTURE_HEIGHT = 48;
	
	
	public PlayerAnimationSet(final Sprite sprite) {
		super(sprite);
		
		// idle SOUTH
		Array<TextureRegion> idleSouthFrames = new Array<TextureRegion>(1);
		idleSouthFrames.add(new TextureRegion(sprite, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT));
		idle.put(Direction.SOUTH, new Animation<TextureRegion>(idleAnimationDuration, idleSouthFrames));
		
		// idle WEST
		Array<TextureRegion> idleWestFrames = new Array<TextureRegion>(1);
		idleWestFrames.add(new TextureRegion(sprite, 0, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT));
		idle.put(Direction.WEST, new Animation<TextureRegion>(idleAnimationDuration, idleWestFrames));
		
		// idle EAST
		Array<TextureRegion> idleEastFrames = new Array<TextureRegion>(1);
		idleEastFrames.add(new TextureRegion(sprite, 0, TEXTURE_HEIGHT * 2, TEXTURE_WIDTH, TEXTURE_HEIGHT));
		idle.put(Direction.EAST, new Animation<TextureRegion>(idleAnimationDuration, idleEastFrames));
		
		// idle NORTH
		Array<TextureRegion> idleNorthFrames = new Array<TextureRegion>(1);
		idleNorthFrames.add(new TextureRegion(sprite, TEXTURE_WIDTH * 3, 0, TEXTURE_HEIGHT, TEXTURE_HEIGHT));
		idle.put(Direction.NORTH, new Animation<TextureRegion>(idleAnimationDuration, idleNorthFrames));
		
		// walking SOUTH
		Array<TextureRegion> walkingSouthFrames = new Array<TextureRegion>(4);
		for (int i = 0; i < 4; i++) {
			walkingSouthFrames.add(new TextureRegion(sprite, TEXTURE_WIDTH * i, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT));
		}
		walking.put(Direction.SOUTH, new Animation<TextureRegion>(walkingAnimationDuration, walkingSouthFrames));
		
		// walking WEST
		Array<TextureRegion> walkingWestFrames = new Array<TextureRegion>(4);
		for (int i = 0; i < 4; i++) {
			walkingWestFrames.add(new TextureRegion(sprite, TEXTURE_WIDTH * i, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT));
		}
		walking.put(Direction.WEST, new Animation<TextureRegion>(walkingAnimationDuration, walkingWestFrames));
		
		// walking EAST
		Array<TextureRegion> walkingEastFrames = new Array<TextureRegion>(4);
		for (int i = 0; i < 4; i++) {
			walkingEastFrames.add(new TextureRegion(sprite, TEXTURE_WIDTH * i, TEXTURE_HEIGHT * 2, TEXTURE_WIDTH, TEXTURE_HEIGHT));
		}
		walking.put(Direction.EAST, new Animation<TextureRegion>(walkingAnimationDuration, walkingEastFrames));
		

		// walking NORTH
		Array<TextureRegion> walkingNorthFrames = new Array<TextureRegion>(4);
		for (int i = 0; i < 4; i++) {
			walkingNorthFrames.add(new TextureRegion(sprite, TEXTURE_WIDTH * i, TEXTURE_HEIGHT * 3, TEXTURE_WIDTH, TEXTURE_HEIGHT));
		}
		walking.put(Direction.NORTH, new Animation<TextureRegion>(walkingAnimationDuration, walkingNorthFrames));
		
	}
	
	public Animation<TextureRegion> getIdle(final Direction direction) {
		return idle.get(direction);
	}
	
	public Animation<TextureRegion> getWalking(final Direction direction) {
		return walking.get(direction);
	}

}
