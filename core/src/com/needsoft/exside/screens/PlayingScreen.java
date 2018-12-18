package com.needsoft.exside.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.needsoft.exside.entities.player.Player;
import com.needsoft.exside.maps.MapManager;
import com.needsoft.exside.systems.camera.CameraManager;
import com.needsoft.exside.systems.hud.HUD;
import com.needsoft.exside.systems.shaders.ShaderManager;

public class PlayingScreen extends ScreenAdapter {
	
	private OrthographicCamera camera;
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;
	
	private MapManager mapManager;
	private ShaderManager shaderManager;
	
	private Player player;
	
	private HUD hud;
	
	
	@Override
	public void show() {
		mapManager = new MapManager("maps/test-map.tmx");
		
		mapRenderer = new OrthogonalTiledMapRenderer(mapManager.map);

		shapeRenderer = new ShapeRenderer();
		
		player = new Player();
		player.init(100, 200, this);
		Gdx.input.setInputProcessor(player);

		hud = new HUD();
		
		// Don't need to specify width/height, resize() is called just after show()
		camera = new OrthographicCamera();
		
		shaderManager = new ShaderManager();
		mapRenderer.getBatch().setShader(shaderManager.getShader());
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		// Background layer index
		mapRenderer.render(new int[] { 0 });
		
		mapRenderer.getBatch().begin();
		
		mapRenderer.renderTileLayer((TiledMapTileLayer) mapManager.map.getLayers().get("background"));

		player.render(mapRenderer.getBatch());

		mapRenderer.renderTileLayer((TiledMapTileLayer) mapManager.map.getLayers().get("foreground"));
		
		hud.render(mapRenderer.getBatch());
		
		mapRenderer.getBatch().end();
		
		// Foreground layer index
		mapRenderer.render(new int[] { 1 });
		
		shapeRenderer.setProjectionMatrix(camera.combined);

		renderShapes();
		
		//System.out.println("FPS : " + Gdx.app.getGraphics().getFramesPerSecond());
	}
	
	private void update(final float delta) {
		updateCamera();
		
		player.update(delta);
		hud.update(delta);
	}

	private void updateCamera() {
		CameraManager.lerpToTarget(camera, new Vector2(player.getX(), player.getY()));
		
		float mapWidth = ((TiledMapTileLayer) mapManager.map.getLayers().get(0)).getTileWidth()
				* ((TiledMapTileLayer) mapManager.map.getLayers().get(0)).getWidth();
		float mapHeight = ((TiledMapTileLayer) mapManager.map.getLayers().get(0)).getTileHeight()
				* ((TiledMapTileLayer) mapManager.map.getLayers().get(0)).getHeight();
		
		CameraManager.clampBondaries(camera, 0, 0, mapWidth, mapHeight);
		// Vital to call update after camera's position change
		camera.update();
		mapRenderer.setView(camera);
	}
	
	private void renderShapes() {
		for (MapObject mapObject : mapManager.map.getLayers().get("objects").getObjects()) {
			if (mapObject instanceof RectangleMapObject) {
				final Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
				shapeRenderer.end();
			}
			else if (mapObject instanceof CircleMapObject) {
				final Circle circle = ((CircleMapObject) mapObject).getCircle();
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.circle(circle.x, circle.y, circle.radius);
				shapeRenderer.end();
			}
			else if (mapObject instanceof EllipseMapObject) {
				final Ellipse ellipse = ((EllipseMapObject) mapObject).getEllipse();
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.ellipse(ellipse.x, ellipse.y, ellipse.width, ellipse.height);
				shapeRenderer.end();
			}
			else if (mapObject instanceof PolylineMapObject) {
				Gdx.gl.glLineWidth(3);
				final Polyline polyline = ((PolylineMapObject) mapObject).getPolyline();
				shapeRenderer.begin(ShapeType.Line);
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.polyline(polyline.getTransformedVertices());
				shapeRenderer.end();
			}
			else if (mapObject instanceof PolygonMapObject) {
				Gdx.gl.glLineWidth(3);
				final Polygon polyligon = ((PolygonMapObject) mapObject).getPolygon();
				shapeRenderer.begin(ShapeType.Line);
				shapeRenderer.setColor(Color.WHITE);
				shapeRenderer.polygon(polyligon.getTransformedVertices());
				shapeRenderer.end();
			}
		}
	}
	
	public TiledMap getMap() {
		return mapManager.map;
	}
	
	public TiledMapTileLayer getMapCollisionLayer() {
		return mapManager.collisionLayer;
	}
	
	@Override
	public void resize(int width, int height) {
		// Reduce viewport
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}
	
	@Override
	public void hide() {
		dispose();
		shapeRenderer.dispose();
	}
	
	@Override
	public void dispose() {
		player.dispose();
		hud.dispose();
		mapRenderer.dispose();
		mapManager.dispose();
		shaderManager.dispose();
	}

}
