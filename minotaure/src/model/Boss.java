package model;

import type.AbstractCharacter_with_transfer;
import type.AbstractMap;

public class Boss extends AbstractCharacter_with_transfer {

	public Boss(String setUrl, Rectangle player1, AbstractMap map) {
		super(setUrl);
		transfer_strategy= new IA_transfertStrategy_std(this.position, player1, map);
	}
	
	@Override
	public void memorizeBossMoves() {
		Coordinates vectors= transfer_strategy.memorizeBossMoves();	
		this.changeSprite(vectors);
		position.translate(vectors);
	}
	
	@Override
	public void think(Coordinates player1, Coordinates player2) {
		this.transfer_strategy.think(position, player1, player2);
	}
}
