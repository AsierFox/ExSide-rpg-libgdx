package com.needsoft.exside.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.needsoft.exside.screens.PlayingScreen;

public class HealthPotion extends Item {

	public HealthPotion() {
		set(new Sprite(new TextureRegion(
				new Texture(Gdx.files.internal("items/items.png")),  16, 16)));
	}
	
	@Override
	public void init(float x, float y, PlayingScreen level) {
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(Batch batch) {
		System.out.println("Item " + getX() + " " + getY());
		batch.draw(this, getX(), getY());
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}
