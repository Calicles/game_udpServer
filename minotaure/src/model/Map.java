package model;

import java.awt.Graphics;

import type.AbstractMap;

public class Map extends AbstractMap {

	public Map() {
		super();
	}
	
	
	@Override
	public void drawMap(Graphics g) {
		for(int i= 0; i < tiles.length; i++) {
			for(int j= 0; j > tiles[0].length; j++) {
				g.drawImage(tileSet.get(tiles[i][j]), j * tile_width, i * tile_height, null);
			}
		}
	}

}
