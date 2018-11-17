package model;

import java.awt.Graphics;

import type.AbstractMap;

public class Map extends AbstractMap {

	public Map() {
		super();
	}
	
	
	@Override
	public void drawMap(Graphics g, Rectangle screen) {
		int row, iMax, col, jMax, xdepart, ydepart, x, y;
		
		row= screen.getY() / tile_height;
		col= screen.getX() / tile_width;
		
		iMax= screen.getWidth() / tile_height;
		jMax= screen.getHeight() / tile_width;
		
		xdepart= col * tile_width - screen.getX();
		ydepart= row * tile_height - screen.getY();
		
		if(screen.getEndX() % tile_width != 0) jMax++;
		if(screen.getEndY() % tile_height != 0) iMax++;
		
		for(int i= 0; i < iMax; i++) {
			for(int j= 0; j < jMax; j++){
				x= xdepart + j * tile_width;
				y= ydepart + i * tile_height;
				g.drawImage(tileSet.get(tiles[row + i][col + j]), x, y, null);
			}
		}
	}

}
