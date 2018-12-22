package com.needsoft.exside.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class MapManager {

	public TiledMap map;
	public TiledMapTileLayer collisionLayer;
	
	private AnimatedTiledMapTile animatedTile;
	
	
	public MapManager(final String mapPath) {
		map = new TmxMapLoader().load(mapPath);
		collisionLayer = (TiledMapTileLayer) map.getLayers().get(0);
		
		setupTileAnimations();
	}
	
	private void setupTileAnimations() {
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
	}

	// TODO Return an Item
	public List<Sprite> loadItems() {
		List<Sprite> items = new ArrayList<Sprite>();

		MapObjects mapObjects = map.getLayers().get("items").getObjects();
		if (mapObjects.getCount() <= 0) {
			return items;
		}

		for (MapObject mapObject : mapObjects) {
			Sprite item = null;

			EllipseMapObject circleMapObject = (EllipseMapObject) mapObject;
			float spawnX = circleMapObject.getEllipse().x;
			float spawnY = circleMapObject.getEllipse().y;

			if (mapObject.getProperties().containsKey("healthPotion")) {
				item = new Sprite(new TextureRegion(
						new Texture(Gdx.files.internal("items/items.png")), 30, 50));
			}

			item.setX(spawnX);
			item.setY(spawnY);
			items.add(item);
		}

		return items;
	}
	
	public void dispose() {
		map.dispose();
	}

}
