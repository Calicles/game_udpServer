package model;

import type.AbstractMap;
import type.AbstractTransfer;

public class Player_transferStrategy_std extends AbstractTransfer {

	public void released() {xVector= 0; yVector= 0;}
	public void movesLeft() {xVector= -4;}
	public void movesRight() {xVector= 4;}
	public void movesUp() {yVector= -4;}
	public void movesDown() {yVector= 4;}
	
	@Override
	public Coordinates memorizeMoves(Rectangle position, AbstractMap map) {
		Rectangle tile;
		int playerX= position.getX(), playerY= position.getY(), x, y;
		
		//On cherche le vecteur
		if(xVector != 0) {
			x= playerX + xVector;
			
			//On cherche la direction
			if(xVector < 0) {
				
				//si au bord de la map
				if(playerX < map.getTileWidth() && x <= 0) {
					xVector= 0 - playerX;
				}else {
					
					//Si rencontre une tuile solide
					if((tile= checkLeftTile()) != null) {
						xVector= (tile.getX() + tile.getWidth()) - playerX;
					}
				}
			}else {
				if(playerX > map.getWidth() - map.getTileWidth()) {
					xVector= map.getWidth() - playerX;
				}else {
					if((tile= checkRightTile()) != null) {
						xVector= tile.getX() - (playerX + position.getWidth());
					}
				}
			}
		}else if(yVector != 0) {
			y= position.getY() + yVector;
			if(yVector < 0) {
				
			}else {
				
			}
		}
		//Change les coordonnes du joueur
		position.setCoordinates(position.getX() + xVector, position.getY() + yVector);
		return new Coordinates(xVector, yVector);
	}
	
	
private Rectangle checkRightTile() {
		// TODO Auto-generated method stub
		return null;
	}


}
	private Rectangle checkLeftTile() {
		// TODO Auto-generated method stub
		return null;
	}
