package model;

import type.AbstractCharacter_with_transfer;
import type.AbstractMap;

public class Player extends AbstractCharacter_with_transfer {
	
	public Player(String setUrl) {
		super(setUrl);
		transfer_strategy= new Player_transferStrategy_std();
	}

	public void movesLeft() {transfer_strategy.movesLeft();}
	public void movesRight() {transfer_strategy.movesRight();}
	public void movesUp() {transfer_strategy.movesUp();}
	public void movesDown() {transfer_strategy.movesDown();}
	
	public void movesReleased() {
		animIndex= 0; 
		animationStoped();
		transfer_strategy.released();
		}
	
	public Coordinates memorizeMoves(Rectangle player2Position, AbstractMap map) {
		Coordinates vectors= transfer_strategy.memorizePlayerMoves(this.position, player2Position, map);
		
		//Change les coordonnes du perso
		position.setCoordinates(position.getX() + vectors.getX(), position.getY() + vectors.getY());
		
		//change la sprite
		changeSprite(vectors);
		return vectors;
	}

}
