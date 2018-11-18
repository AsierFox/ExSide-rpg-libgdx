package com.needsoft.exside.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.needsoft.exside.entities.Player;

public class PlayingScreen extends ScreenAdapter {
	
	private OrthographicCamera camera;
	
	public TiledMap map;
	// TODO Refactor this to a map class
	public TiledMapTileLayer collisionLayer;
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;
	
	private Player player;
	
	
	@Override
	public void show() {
		map = new TmxMapLoader().load("maps/test-map.tmx");
		collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
		
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		shapeRenderer = new ShapeRenderer();
		
		player = new Player();
		
		player.init(100, 200, this);
		
		// Don't need to specify width/height, resize() is called just after show()
		camera = new OrthographicCamera();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		mapRenderer.setView(camera);
		mapRenderer.render();
		
		mapRenderer.getBatch().begin();
		
		player.update(delta);	
		player.render(mapRenderer.getBatch());
		
		mapRenderer.getBatch().end();
		
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(Color.WHITE);
//		shapeRenderer.rect(player.getX(), player.getY(), player.getWidth() + 10, player.getHeight() + 10);
//		shapeRenderer.end();
		
		System.out.println("FPS : " + Gdx.app.getGraphics().getFramesPerSecond());
	}
	
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.projection);
	}
	
	@Override
	public void hide() {
		dispose();
		shapeRenderer.dispose();
	}
	
	@Override
	public void dispose() {
		player.dispose();
		mapRenderer.dispose();
		map.dispose();
	}

}
