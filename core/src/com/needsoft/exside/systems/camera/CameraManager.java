package com.needsoft.exside.systems.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.needsoft.exside.entities.Entity;

public class CameraManager {

	public static void lockOnEntity(Camera camera, final Entity entity) {
		// TODO Make a function in Entity to get the center of the Sprite
		lockOnTarget(camera, new Vector2(entity.getX() + entity.getWidth() / 2,
				entity.getY() + entity.getHeight() / 2));
	}
	
	public static void lockOnTarget(Camera camera, final Vector2 target) {
		Vector3 position = camera.position;
		position.x = target.x;
		position.y = target.y;
		camera.position.set(position);
	}
	
	public static void lerpToTarget(Camera camera, final Vector2 target) {
		Vector3 position = camera.position;
		// Using interpolation function
		final float lerpFactor = .1f;
		position.x = target.x + (target.x - camera.position.x) * lerpFactor;
		position.y = target.y + (target.y - camera.position.y) * lerpFactor  ;
		camera.position.set(position);
	}
	
	public static void lockAverageBetweenTargets(Camera camera, Vector2 targetA, Vector2 targetB) {
		lockOnTarget(camera, new Vector2((targetA.x + targetB.x) / 2, (targetA.y + targetB.y) / 2));
	}
	
	public static void lerpAverageBetweenTargets(Camera camera, Vector2 targetA, Vector2 targetB) {
		lerpToTarget(camera, new Vector2((targetA.x + targetB.x) / 2, (targetA.y + targetB.y) / 2));
	}
	
	/**
	 * If the position approaches at the raius limit respect the point focus, it focus to this point.
	 * 
	 * @param camera
	 * @param position
	 * @param pointToFocus
	 * @param radiusLimit
	 * @return If has qpproached
	 */
	public static boolean focusAtApproached(Camera camera, final Vector2 position, final Vector2 pointToFocus, final float radiusLimit) {
		if (position.dst(pointToFocus) <= radiusLimit) {
			lerpToTarget(camera, pointToFocus);
			return true;
		}
		return false;
	}
	
	public static void clampBondaries(Camera camera, final float x, final float y, final float width, final float height) {
		Vector3 position = camera.position;
		
		if (position.x < x) {
			position.x = x;
		}
		else if (position.x > width) {
			position.x = width;
		}
		if (position.y < y) {
			position.y = y;
		}
		else if (position.y > height) {
			position.y = height;
		}
		
		camera.position.set(position);
	}

}
