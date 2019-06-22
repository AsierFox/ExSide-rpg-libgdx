package com.needsoft.exside.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.needsoft.exside.interfaces.Renderable;
import com.needsoft.exside.screens.PlayingScreen;

public abstract class Item extends Sprite implements Renderable {

	public boolean isVisible;

	public Rectangle collider;
	
	protected PlayingScreen belongsToLevel;

	@Override
	public void init(final float x, final float y, final PlayingScreen level) {
		belongsToLevel = level;
		
		setX(x);
		setY(y);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
