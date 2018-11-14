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
				
				//si au bord de la map on annule ou réduit le vecteur
				if(playerX < map.getTileWidth() && x <= 0) {
					xVector= 0 - playerX;
					
				//Si au bord d'une tuile solide idem
				}else if((tile= checkLeftTiles()) != null) {
					xVector= tile.getEndX() - playerX;
				}
			}else {
				if(playerX > map.getWidth() - map.getTileWidth()) {
					xVector= map.getWidth() - playerX;
				}else {
					if((tile= checkRightTiles()) != null) {
						xVector= tile.getX() - (position.getEndX());
					}
				}
			}
		}else if(yVector != 0) {
			y= position.getY() + yVector;
			if(yVector < 0) {
				if(playerY < map.getTileHeight() && y <= 0) {
					yVector= 0 - playerY;
				}else if((tile= checkOnUpTiles()) != null){
					yVector= tile.getEndY() - playerY;
				}
			}else {
				if(playerY > map.getHeight() - map.getTileHeight() && y >= map.getHeight()) {
					yVector= map.getHeight() - playerY;
				}else if((tile= checkOnDownTiles()) != null) {
					yVector= tile.getEndY() - playerY;
				}
			}
		}
		//Change les coordonnes du joueur
		position.setCoordinates(position.getX() + xVector, position.getY() + yVector);
		//On return pour scrolling
		return new Coordinates(xVector, yVector);
	}
	
	
private Rectangle checkOnDownTiles() {
		// TODO Auto-generated method stub
		return null;
	}
private Rectangle checkOnUpTiles() {
		// TODO Auto-generated method stub
		return null;
	}
private Rectangle checkRightTiles() {
		// TODO Auto-generated method stub
		return null;
	}


	private Rectangle checkLeftTiles() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
