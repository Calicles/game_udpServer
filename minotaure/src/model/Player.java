package model;

import contracts.Charac_withTransfert;
import contracts.Player_transfert;
import type.AbstractCharacter_with_transfer;
import type.AbstractMap;
import type.AbstractTransfer;

public class Player extends AbstractCharacter_with_transfer implements Charac_withTransfert {
	
	private Player_transfert transfer_strategy;
	
	public Player(String setUrl, String coorDepart) {
		super(setUrl, coorDepart);
	}
	
	public Player() {
		super();
	}
	
	public void setUrlImg(String url) {
		super.setUrlImage(url);
	}
	
	public void setPosition(String coorDepart) {
		super.setPosition(coorDepart);
	}
	
	public void setTransfert(Player_transfert t) {
		this.transfer_strategy= t;
	}
	
	public void movesLeft() {transfer_strategy.movesLeft();}
	public void movesRight() {transfer_strategy.movesRight();}
	public void movesUp() {transfer_strategy.movesUp();}
	public void movesDown() {transfer_strategy.movesDown();}
	
	public void movesReleased() {
		animationStoped();
		transfer_strategy.released();
		}
	
	@Override
	public Coordinates memorizePlayerMoves(Rectangle player2Position, AbstractMap map) {
		Coordinates vectors= transfer_strategy.memorizePlayerMoves(this.position, player2Position, map);
		
		//Change les coordonnes du perso
		position.setCoordinates(position.getX() + vectors.getX(), position.getY() + vectors.getY());
		
		//change la sprite
		changeSprite(vectors);
		return vectors;
	}

	@Override
	public void setAttributes(Rectangle position, AbstractMap map) {
		// TODO Auto-generated method stub
		
	}

}
