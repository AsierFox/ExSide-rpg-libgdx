package com.needsoft.exside.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.needsoft.exside.entities.Player;

public class PlayingScreen extends ScreenAdapter {
	
	private OrthographicCamera camera;
	
	public TiledMap map;
	// TODO Refactor this to a map class
	public TiledMapTileLayer collisionLayer;
	private AnimatedTiledMapTile animatedTile;
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private ShapeRenderer shapeRenderer;
	
	private Player player;
	
	private ShaderProgram shader;
	
	
	@Override
	public void show() {
		// TODO Refactor to map class
		map = new TmxMapLoader().load("maps/shader-map.tmx");
		collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
		
		/*
		// Set animations to map
		Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>(2);
		// Find animation tile in the tileset
		final Iterator<TiledMapTile> tileIterator = map.getTileSets().getTileSet(0).iterator();
		while (tileIterator.hasNext()) {
			TiledMapTile tile = tileIterator.next();
			if (tile.getProperties().containsKey("animation")
					&& tile.getProperties().get("animation", String.class).equals("sea-water")) {
				frameTiles.add((StaticTiledMapTile) tile);
			}
		}
		animatedTile = new AnimatedTiledMapTile(1 / 3f, frameTiles);
		// Iterate over tiled map to replace the tile animations
		TiledMapTileLayer foregroundLayer = (TiledMapTileLayer) map.getLayers().get("foreground");
		for (int x = 0; x < foregroundLayer.getWidth(); x++) {
			for (int y = 0; y < foregroundLayer.getHeight(); y++) {
				final Cell tileCell = foregroundLayer.getCell(x, y);
				if (tileCell != null && tileCell.getTile() != null && tileCell.getTile().getProperties().containsKey("animation")) {
					if (tileCell.getTile().getProperties().get("animation", String.class).equals("sea-water")) {
						tileCell.setTile(animatedTile);
					}
				}
			}
		}
		*/
		
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		shapeRenderer = new ShapeRenderer();
		
		player = new Player();
		player.init(100, 200, this);
		Gdx.input.setInputProcessor(player);
		
		// Don't need to specify width/height, resize() is called just after show()
		camera = new OrthographicCamera();
		
		// We don't need to have all attributes due to shaders are simple
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/none.vsh"), Gdx.files.internal("shaders/none.fsh"));
		System.out.println(shader.isCompiled() + " Errors => [" + shader.getLog() + "]");
		mapRenderer.getBatch().setShader(shader);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		mapRenderer.setView(camera);
		
		player.update(delta);
		
//		shader.begin();
//		shader.setUniformf("u_distort", MathUtils.random(4), MathUtils.random(4), 0);
//		shader.end();
		
		// Background layer index
		mapRenderer.render(new int[] { 0 });

		mapRenderer.getBatch().begin();
		
		mapRenderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("background"));
		
		player.render(mapRenderer.getBatch());
		
		//mapRenderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get("foreground"));
		
		mapRenderer.getBatch().end();
		
		// Foreground layer index
		//mapRenderer.render(new int[] { 1 });
		
		shapeRenderer.setProjectionMatrix(camera.combined);

		//renderShapes();
		
		System.out.println("FPS : " + Gdx.app.getGraphics().getFramesPerSecond());
	}
	
	private void renderShapes() {
		for (MapObject mapObject : map.getLayers().get("objects").getObjects()) {
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
		mapRenderer.dispose();
		map.dispose();
	}

}
