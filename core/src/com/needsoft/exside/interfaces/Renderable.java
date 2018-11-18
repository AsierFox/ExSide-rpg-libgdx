package com.needsoft.exside.interfaces;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.needsoft.exside.screens.PlayingScreen;

public interface Renderable {
	
	void init(final float x, final float y, final PlayingScreen level);
	
	void update(final float delta);
	
	void render(final Batch batch);
	
	void dispose();
	
	boolean isVisible();

}
