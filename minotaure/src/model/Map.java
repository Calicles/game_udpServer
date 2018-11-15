package model;

import java.awt.Graphics;

import type.AbstractMap;

public class Map extends AbstractMap {

	public Map() {
		super();
	}
	
	
	@Override
	public void drawMap(Graphics g, Rectangle screen) {
		int x, y, endX, endY, xTile, yTile;
		x= screen.getX() / tile_width;
		endX= screen.getEndX() / tile_width;
		y= screen.getY() / tile_height;
		endY= screen.getEndY() / tile_height;
		for(int i= y; i < endY; i++) {
			for(int j= x; j < endX; j++){
				xTile= (x * tile_width) - screen.getX();
				yTile= (y * tile_height) - screen.getY();
				g.drawImage(tileSet.get(tiles[i][j]), xTile + j*tile_width, yTile + i*tile_height, null);
			}
		}
	}

}
