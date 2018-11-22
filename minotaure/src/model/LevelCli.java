package model;

import java.awt.Dimension;
import java.awt.Graphics;

import type.AbstractLevel;
import type.LevelListener;

public class LevelCli extends AbstractLevel {

	private Boss_IALess boss;
	
	public LevelCli(Dimension screenSize) {
		super("ressources/player/set.txt", screenSize);
		boss= null;
	}
	
	@Override
	public void drawLevel(Graphics g) {
		super.drawLevel(g);
		boss.draw(g, scrollBoxes.getScreenPosition());
	}
	
	@Override
	public Coordinates getBossCoordinates() {
		if(boss != null)
			return boss.getCoordinates();
		return null;
	}
	
	public void loop() {
		Rectangle player2Position;
		if(player2 != null)
			player2Position= player2.getPosition();
		else player2Position= null;
		Coordinates vectors= player.memorizePlayerMoves(player2Position, map);
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
		try {
			player2.setCoordinates(te.getNewPlayerPosition());
			player2.setImages(te.getPlayerImages());
			boss.setCoordinates(te.getNewBossPosition());
			boss.setImages(te.getBossImages());
		}catch(NullPointerException ne) {
			if(player2 == null) {
				player2= new Player2("ressources/player/set.txt");
				player2.setCoordinates(te.getNewPlayerPosition());
				player2.setImages(te.getBossImages());
			}
			if(boss == null) {
				boss= new Boss_IALess("ressources/boss/set.txt");
				boss.setCoordinates(te.getNewBossPosition());
				boss.setImages(te.getBossImages());
			}
		}
		fireUpdate();
	}

	@Override
	protected void fireUpdate() {
		TransferEvent event= new TransferEvent(player.getCoordinates(), player.getImages());
		for(LevelListener l:listeners) {
			l.update(event);
		}
	}

}
