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
	
	public Coordinates adaptVectors(Rectangle position, AbstractMap map) {
		
		//On cherche si vecteur null pas de calcul !
		if(xVector != 0 || yVector !=0) {
			
			//On cherche la direction
			if(xVector < 0) {
				
				checkLeft(position, map);
				
			}else if(xVector > 0) {
				
				checkRight(position, map);
				
			}else if(yVector < 0) {
				
				checkUp(position, map);
				
			}else if(yVector > 0) {
				
				checkDown(position, map);
			}
		}
		//On return pour scrolling
		return new Coordinates(xVector, yVector);
	}
	
	protected void checkLeft(Rectangle position, AbstractMap map) {
		int playerX= position.getX();
		
		if((playerX < map.tile_width) && (playerX < 4)) {
			xVector= 0 - playerX; 
			
		}/**else if((tile= checkLeftTiles(position, map)) != null) {
			xVector= tile.getEndX() - playerX;
		}**/
		yVector= 0;	
	}
	
	protected void checkRight(Rectangle position, AbstractMap map) {
		int playerEndX= position.getEndX();
		
		if((position.getEndX() > (map.getWidth() - map.getTileWidth())) &&
				playerEndX > map.getWidth() - 4) {
			
			xVector= map.getWidth() - playerEndX;
			
		}/**else if((tile= checkRightTiles(position, map)) != null) {
				xVector= tile.getX() - (position.getEndX());
		}**/
		yVector= 0;
	}
	
	protected void checkUp(Rectangle position, AbstractMap map) {
		int playerY= position.getY();
		
		if((playerY < map.tile_height) && (playerY < 4)) {
			yVector= 0 - playerY;
		}/**else if((tile= checkOnUpTiles(position, map)) != null){
		yVector= tile.getEndY() - playerY;
		}**/
		xVector= 0;
	}
	
	protected void checkDown(Rectangle position, AbstractMap map) {
		int playerEndY= position.getEndY();

		if((playerEndY > (map.getHeight() - map.tile_height)) && 
				(playerEndY > map.getHeight() - 4)) {
			
			yVector= map.getHeight() - position.getEndY();
		}/**else if((tile= checkOnDownTiles(position, map)) != null) {
			yVector= tile.getEndY() - playerY;
		}**/
		xVector= 0;
	}
	
	protected Rectangle checkOnDownTiles(Rectangle position, AbstractMap map) {

		int x, y, xMax, yMax;
		
		x= position.getX() / map.getTileWidth();
		xMax= position.getEndX() / map.getTileWidth();
		y= position.getY() / map.getTileHeight() +1;
		yMax= y;
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	protected Rectangle checkOnUpTiles(Rectangle position, AbstractMap map) {

		int x, y, xMax, yMax;
		
		x= position.getX() / map.getTileWidth();
		xMax= position.getEndX() / map.getTileWidth();
		y= position.getY() / map.getTileHeight() -1;
		yMax= y;
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	protected Rectangle checkRightTiles(Rectangle position, AbstractMap map) {
		
		int x, y, xMax, yMax;
		
		x= position.getEndX() / map.getTileWidth() +1;
		xMax= x;
		y= position.getY() / map.getTileWidth();
		yMax= position.getEndY() / map.getTileHeight();
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	
	protected Rectangle checkLeftTiles(Rectangle position, AbstractMap map) {
		
		int x, y, xMax, yMax;
		
		x= position.getX() / map.getTileWidth() -1;
		xMax= x;
		y= position.getY() / map.getTileHeight();
		yMax= position.getEndY() / map.getTileHeight();
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), xMax, yMax), map);
	}
	
	protected Rectangle isSolidTileOnRoad(Rectangle surface, AbstractMap map) {
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
