package model;

import type.AbstractCharacter_with_transfer;
import type.AbstractMap;

public class Boss extends AbstractCharacter_with_transfer {

	public Boss(String setUrl) {
		super(setUrl);
		transfer_strategy= new IA_transfertStrategy_std();
	}
	
	public void memorizeMoves(Rectangle playerPosition, AbstractMap map) {
		
	}
}
