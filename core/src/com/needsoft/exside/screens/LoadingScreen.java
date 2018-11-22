package com.needsoft.exside.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.needsoft.exside.Game;

public class LoadingScreen extends ScreenAdapter {

	private Game belongsToGame;
	
	private ShapeRenderer shapeRenderer;
	
	// TODO Then pass to the class to get the loaded resources
	private AssetManager assetManager;
	
	/** 0 to 1 value */
	private float progress;
	
	
	public LoadingScreen(final Game belongsToGame) {
		this.belongsToGame = belongsToGame;
		
		shapeRenderer = new ShapeRenderer();
		
		assetManager = new AssetManager();
	}
	
	@Override
	public void show() {
		progress = 0f;
		
		queueAssets();
	}
	
	private void queueAssets() {
		//assetManager.load("asset/path", Texture.class || Audio.class...);
		// Then in the screen -> assetManager.get("asset/path", Texture.class || Audio.class...)
	}
	
	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.rect(32, Game.WINDOW_HEIGHT / 2 - 32, Game.WINDOW_WIDTH - 64, 40);
		
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(32 + 6, (Game.WINDOW_HEIGHT / 2 - 32) + 6, Game.WINDOW_WIDTH - 64 - 12, 40 - 12);
		
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(32 + 6, (Game.WINDOW_HEIGHT / 2 - 32) + 6, progress * (Game.WINDOW_WIDTH - 64 - 12), 40 - 12);
		
		shapeRenderer.end();
	}
	
	private void update(final float delta) {
		progress = MathUtils.lerp(progress, assetManager.getProgress(), .1f);

		if (assetManager.update()) {
			// Assets loaded
			belongsToGame.setScreen(new PlayingScreen());
		}
	}
	
	@Override
	public void resize(int width, int height) {
	}
	
	@Override
	public void dispose() {
		shapeRenderer.dispose();
		assetManager.dispose();
	}
	
}
