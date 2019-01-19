package com.needsoft.exside.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.needsoft.exside.interfaces.Renderable;

public abstract class Item extends Sprite implements Renderable {

	public boolean isVisible;

	public Rectangle collider;

	public Item() {
	}
	
}
