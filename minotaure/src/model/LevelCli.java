package model;

import java.awt.Dimension;

import type.AbstractLevel;
import type.LevelListener;

public class LevelCli extends AbstractLevel {

	private Boss_IALess boss;
	
	public LevelCli(Dimension screenSize) {
		super("ressources/player/set.txt", screenSize);
		boss= null;
	}
	
	@Override
	public Coordinates getBossCoordinates() {
		if(boss != null)
			return boss.getCoordinates();
		return null;
	}
	
	public void loop() {
		Coordinates vectors= player.memorizePlayerMoves(null, map);//TODO Change
		scroll(vectors);
		fireUpdate();
	}
	
	@Override
	public void playerMovesLeft() {
		super.playerMovesLeft();
		loop();
	}
	@Override
	public void playerMovesRight() {
		super.playerMovesRight();
		loop();
	}
	@Override
	public void playerMovesUp() {
		super.playerMovesUp();
		loop();
	}
	@Override
	public void playerMovesDown() {
		super.playerMovesDown();
		loop();
	}
	@Override
	public void released() {
		super.released();
		fireUpdate();
	}

	@Override
	public void update(TransferEvent te) {
		if(player2 != null) {
			
		}else
			player2= new Player2("ressources/player/set.txt");
	}

	@Override
	protected void fireUpdate() {
		TransferEvent event= new TransferEvent(player.getCoordinates(), player.getImages());
		for(LevelListener l:listeners) {
			l.update(event);
		}
	}

}
