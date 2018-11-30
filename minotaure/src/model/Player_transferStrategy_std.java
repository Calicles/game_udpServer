package model;

import contracts.Player_transfert;
import type.AbstractMap;
import type.AbstractTransfer;

public class Player_transferStrategy_std extends AbstractTransfer implements Player_transfert {
	
	public Player_transferStrategy_std() {
		super();
	}

	public void released() {xVector= 0; yVector= 0;}
	public void movesLeft() {xVector= -4;}
	public void movesRight() {xVector= 4;}
	public void movesUp() {yVector= -4;}
	public void movesDown() {yVector= 4;}
	
	@Override
	public Coordinates memorizePlayerMoves(Rectangle position, Rectangle player2Position, AbstractMap map) {
		
		adaptVectors(position, map);
		
		if(player2Position != null)
			adaptVectorsByPlayer2(position, player2Position);
		
		return new Coordinates(xVector, yVector);
	}
	
}
