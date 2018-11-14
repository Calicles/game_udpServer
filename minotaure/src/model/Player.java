package model;

import type.AbstractCharacter_with_transfer;
import type.AbstractMap;

public class Player extends AbstractCharacter_with_transfer {
	
	public Player() {
		transfer_strategy= new Player_transferStrategy_std();
	}

	public void movesLeft() {transfer_strategy.movesLeft();}
	public void movesRight() {transfer_strategy.movesRight();}
	public void movesUp() {transfer_strategy.movesUp();}
	public void movesDown() {transfer_strategy.movesDown();}
	public void movesReleased() {transfer_strategy.released();}
	
	public Coordinates memorizeMoves(AbstractMap map) {
		return transfer_strategy.memorizeMoves(this.position, map);
	}



}
