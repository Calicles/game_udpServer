package type;

import model.Coordinates;
import model.Rectangle;
import model.Tile;

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
		
		Rectangle tile;
		int playerX= position.getX();
		
		if(playerX < map.tile_width){
			
			if(playerX < 4) {
				xVector= 0 - playerX; 
			}
			
		}else if((tile= checkLeftTiles(position, map)) != null) {
			
			if(tile.getEndX() - playerX >= -4)
				xVector= (tile.getEndX()+1) - playerX;
		}
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
		
		Rectangle tile;
		int playerY= position.getY();
		
		if(playerY <= map.tile_height){
			
			if(playerY < 4) {
				yVector= 0 - playerY;
			}
			
		}else if((tile= checkOnUpTiles(position, map)) != null){
			
			if(tile.getEndY() - playerY >= -4)
				yVector= (tile.getEndY()+1) - playerY;
			
		}
		xVector= 0;
	}
	
	protected void checkDown(Rectangle position, AbstractMap map) {
		
		Rectangle tile;
		int playerEndY= position.getEndY();

		if(playerEndY >= (map.getHeight() - map.tile_height)) {
				if(playerEndY > map.getHeight() - 4) {
					yVector= map.getHeight() - position.getEndY();
				}
		}else if((tile= checkOnDownTiles(position, map)) != null) {
			if(tile.getY() - playerEndY <= 4)
				yVector= tile.getY() - (playerEndY+1);
		}
		xVector= 0;
	}
	
	protected Rectangle checkOnDownTiles(Rectangle position, AbstractMap map) {

		int x, y, endX;
		
		x= position.getX() / map.getTileWidth();
		endX= position.getEndX() / map.getTileWidth();
		y= position.getEndY() / map.getTileHeight() +1;

		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), endX - x, 0), map);
	}
	protected Rectangle checkOnUpTiles(Rectangle position, AbstractMap map) {

		int x, y, endX;
		
		x= position.getX() / map.getTileWidth();
		endX= position.getEndX() / map.getTileWidth();
		y= position.getY() / map.getTileHeight() -1;
		
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), endX - x, 0), map);
	}
	protected Rectangle checkRightTiles(Rectangle position, AbstractMap map) {
		
		int x, y, width, height;
		
		x= position.getEndX() / map.getTileWidth() +1;
		width= x;
		y= position.getY() / map.getTileWidth();
		height= position.getEndY() / map.getTileHeight();
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), width, height), map);
	}
	
	protected Rectangle checkLeftTiles(Rectangle position, AbstractMap map) {
		
		int x, y, endY;
		
		x= position.getX() / map.getTileWidth() -1;
		y= position.getY() / map.getTileHeight();
		endY= position.getEndY() / map.getTileHeight();
		
		return isSolidTileOnRoad(new Rectangle(new Coordinates(x, y), 0, endY - y), map);
	}
	
	protected Rectangle isSolidTileOnRoad(Rectangle surface, AbstractMap map) {
		int[][] tiles= map.getTiles();
		for(int i= surface.getY(); i <= surface.getEndY(); i++) {
			
			for(int j= surface.getX(); j <= surface.getEndX(); j++) {
				
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
