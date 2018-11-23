package com.needsoft.exside.systems.camera;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.needsoft.exside.entities.Entity;

public class CameraManager {

	public static void lockOnEntity(Camera camera, final Entity entity) {
		Vector3 position = camera.position;
		// TODO Make a function in Entity to get the center of the Sprite
		// TODO Then call to lockOnTarge or something, its same method...
		position.x = entity.getX() + entity.getWidth() / 2;
		position.y = entity.getY() + entity.getHeight() / 2;
		camera.position.set(position);
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
	
}
