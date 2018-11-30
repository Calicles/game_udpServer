package model;

import contracts.Charac_withoutTransfert;
import type.AbstractCharacter;

public class Player2 extends AbstractCharacter implements Charac_withoutTransfert{

	public Player2(String player2Url, String coorDepart) {
		super(player2Url, coorDepart);
	}
	
	public Player2() {
		super();
	}
	
	public void setUrlImg(String url) {
		super.setUrlImage(url);
	}
	
	public void setPosition(String coorDepart) {
		super.setPosition(coorDepart);
	}

}
