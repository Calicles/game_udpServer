package model;

import java.awt.Graphics;

import type.AbstractLevel;
import type.LevelListener;

public class LevelHost extends AbstractLevel {
	
	private Boss boss;
	private Thread gameLoop;
	private final long SLEEP= 1000 / 24;
	private long before, after;
	
	private boolean inGame;
	
	public LevelHost() {
		super("ressources/player/set.txt", "");
		boss= new Boss("ressources/boss/set.txt");
		inGame= false;
	}
	
	public Coordinates getBossCoordinates() {return boss.getCoordinates();}
	
	public void playerMovesLeft() {player.movesLeft();}
	public void playerMovesRight() {player.movesRight();}
	public void playerMovesUp() {player.movesUp();}
	public void playerMovesDown() {player.movesDown();}
	public void playerMovesReleased() {player.movesReleased();}
	
	public void drawLevel(Graphics g) {
		super.drawLevel(g);
		boss.draw(g, scrollBoxes.getScreenCoordinates());
	}

	private void runGameLoop() {
		gameLoop= new Thread(()-> {
			
			before= System.currentTimeMillis();
			while(inGame) {
				loop();
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();
			}
		});
		
		gameLoop.start();
	}
	
	private void sleep() {
		try {
			Thread.sleep(fixSleep());;
		}catch(InterruptedException ie) {}
	}

	private long fixSleep() {
		long delta= after - before;
		if(delta < SLEEP)
			return SLEEP - delta;
		else 
			return 0;
	}

	private void loop() {
		Coordinates vectors= player.memorizeMoves(player2.getPosition(), map);
		scroll(vectors);
		boss.memorizeMoves(player.getPosition(), map);
		
		synchronized(this) {
			checkCollision();
			fireUpdate();
		}
	}


	protected void fireUpdate() {
		for(LevelListener l:listeners) {
			l.update(new TransferEvent(player.getCoordinates(), 
					player2.getCoordinates(), boss.getCoordinates()));
		}
	}

	public void levelstart(){
		inGame= true;
		runGameLoop();
	}
	
	public void update(TransferEvent te) {
		player2.setCoordinates(te.getNewPlayerPosition());
	}


	private void checkCollision() {
		//TODO
	}
	

}
