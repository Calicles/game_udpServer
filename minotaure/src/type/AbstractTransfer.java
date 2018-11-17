package type;

import model.Coordinates;
import model.Rectangle;

public abstract class AbstractTransfer {
	
	protected int xVector, yVector;
	
	
	public void movesLeft() {}
	public void movesRight() {}
	public void movesUp() {}
	public void movesDown() {}
	public void released() {}
	
	public Coordinates memorizePlayerMoves(Rectangle position, Rectangle player2Position, AbstractMap map) {return null;}
	public void memorizeBossMoves(Rectangle position, Rectangle playerPosition2, AbstractMap map) {}
	
	public Coordinates adaptVectorsBySolidTiles(Rectangle position, AbstractMap map) {
		Rectangle tile;
		int playerX= position.getX(), playerY= position.getY();
		
		//On cherche le vecteur
		if(xVector != 0) {
			
			//On cherche la direction
			if(xVector < 0) {
				
				//si au bord de la map on annule ou réduit le vecteur
				if(playerX < map.getTileWidth() && playerX < 4) {
					xVector= 0 - playerX;
					
				//Si au bord d'une tuile solide idem
				}else if((tile= checkLeftTiles(position, map)) != null) {
					xVector= tile.getEndX() - playerX;
				}
			}else {
				if(position.getEndX() > map.getWidth() - map.getTileWidth()) {
					xVector= map.getWidth() - position.getEndX();
				}else if((tile= checkRightTiles(position, map)) != null) {
						xVector= tile.getX() - (position.getEndX());
				}
			}
		}else if(yVector != 0) {

			if(yVector < 0) {
				if(playerY < map.getTileHeight() && playerY < 4) {
					yVector= 0 - playerY;
				}else if((tile= checkOnUpTiles(position, map)) != null){
					yVector= tile.getEndY() - playerY;
				}
			}else {
				if(position.getEndY() > map.getHeight() - map.getTileHeight() && position.getEndY() >= map.getHeight()) {

					yVector= map.getHeight() - position.getEndY();
				}else if((tile= checkOnDownTiles(position, map)) != null) {
					yVector= tile.getEndY() - playerY;
				}
			}
		}
		//On return pour scrolling
		return new Coordinates(xVector, yVector);
	}
	private Rectangle checkOnDownTiles(Rectangle position, AbstractMap map) {

		int x, y, xMax, yMax;
		
		x= position.getX() / map.getTileWidth();
		xMax= position.getEndX() / map.getTileWidth();
		y= position.getY() / map.getTileHeight() +1;
		yMax= y;
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	private Rectangle checkOnUpTiles(Rectangle position, AbstractMap map) {

		int x, y, xMax, yMax;
		
		x= position.getX() / map.getTileWidth();
		xMax= position.getEndX() / map.getTileWidth();
		y= position.getY() / map.getTileHeight() -1;
		yMax= y;
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	private Rectangle checkRightTiles(Rectangle position, AbstractMap map) {
		
		int x, y, xMax, yMax;
		
		x= position.getEndX() / map.getTileWidth() +1;
		xMax= x;
		y= position.getY() / map.getTileWidth();
		yMax= position.getEndY() / map.getTileHeight();
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	
	private Rectangle checkLeftTiles(Rectangle position, AbstractMap map) {
		
		int x, y, xMax, yMax;
		
		x= position.getX() / map.getTileWidth() -1;
		xMax= x;
		y= position.getY() / map.getTileHeight();
		yMax= position.getEndY() / map.getTileHeight();
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	
	public Rectangle isSolidTileOnRoad(Rectangle surface, AbstractMap map) {
		int[][] tiles= map.getTiles();
		
		for(int i= surface.getY(); i < surface.getHeight(); i++) {
			
			for(int j= surface.getX(); j < surface.getWidth(); j++) {
				if(AbstractMap.isSolidTiles(tiles[i][j])){
					int x= j * map.getTileWidth();
					int y= i * map.getTileHeight();
					return new Rectangle(new Coordinates(x, y),
							map.getTileWidth(), map.getTileHeight());
					
				}
			}
		}
		return null;
	}
	
}
