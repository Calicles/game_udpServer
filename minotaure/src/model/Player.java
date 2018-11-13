package model;

import type.AbstractCharacter_with_transfer;

public class Player extends AbstractCharacter_with_transfer {
	
	public Player() {
		transfer_strategy= new Player_transferStrategy_std();
	}



}
