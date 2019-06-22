package com.needsoft.exside.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.needsoft.exside.items.HealthPotion;
import com.needsoft.exside.items.Item;

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
				if (null != tileCell && null != tileCell.getTile() && tileCell.getTile().getProperties().containsKey("animation")) {
					if (tileCell.getTile().getProperties().get("animation", String.class).equals("sea-water")) {
						tileCell.setTile(animatedTile);
					}
				}
			}
		}
	}
	
	public List<Item> loadItems() {
		List<Item> items = new ArrayList<>();
		
		MapObjects mapObjects = map.getLayers().get("items").getObjects();
		if (mapObjects.getCount() <= 0) {
			return items;
		}

		for (MapObject mapObject : mapObjects) {
			Item item = null;

			EllipseMapObject circleMapObject = (EllipseMapObject) mapObject;
			float spawnX = circleMapObject.getEllipse().x;
			float spawnY = circleMapObject.getEllipse().y;

			if (mapObject.getProperties().containsKey("healthPotion")) {
				item = new HealthPotion();
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
