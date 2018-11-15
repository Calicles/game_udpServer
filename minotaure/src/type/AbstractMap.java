package type;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import model.Rectangle;
import services.Map_reader;

public abstract class AbstractMap {
	
	public static final int solidTile= 10;
	protected int[][] tiles;
	protected HashMap<Integer, BufferedImage> tileSet;
	protected Dimension size;
	
	protected int tile_width, tile_height;
	
	public AbstractMap() {
		tiles= Map_reader.readMap();
		tileSet= Map_reader.readTileSet();
		
		tile_width= tileSet.get(0).getWidth();
		tile_height= tileSet.get(0).getHeight();
		size= new Dimension(tile_width * tiles[0].length, tile_height * tiles.length);
	}
	
	public int getWidth() {return size.width;}
	public int getHeight() {return size.height;}
	public Dimension getSize() {return size;}
	public int[][] getTiles(){return tiles;}
	public int getTileWidth() {return tile_width;}
	public int getTileHeight() {return tile_height;}
	
	abstract public void drawMap(Graphics g);
	
	public static boolean isSolidTiles(int tile_num) {return tile_num >= solidTile;}

	public boolean isScreenOnBoard(Rectangle screenPosition) {
		return (screenPosition.getX() <= 0 || screenPosition.getEndX() >= size.width
				|| screenPosition.getY() <= 0 || screenPosition.getEndY() >= size.height);
	}


}
