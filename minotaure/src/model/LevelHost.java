package model;

import java.awt.Dimension;
import java.awt.Graphics;

import type.AbstractLevel;
import type.LevelListener;

public class LevelHost extends AbstractLevel {
	
	private Boss boss;
	private Thread gameLoop;
	private final long SLEEP= 1000 / 24;
	private long before, after;
	
	private boolean inGame;
	
	public LevelHost(Dimension screenSize, Player player1, Player2 player2, Boss boss) {
		super(screenSize, player1, player2);
		this.boss= boss;
		boss.setAttributes(player.getPosition(), map);	
		inGame= false;
	}
	
	public Coordinates getBossCoordinates() {return boss.getCoordinates();}
	public Coordinates getBossImages(){return boss.getImages();}
	
	public void playerMovesLeft() {player.movesLeft();}
	public void playerMovesRight() {player.movesRight();}
	public void playerMovesUp() {player.movesUp();}
	public void playerMovesDown() {player.movesDown();}
	public void playerMovesReleased() {player.movesReleased();}
	
	public void drawLevel(Graphics g) {
		super.drawLevel(g);
		boss.draw(g, scrollBoxes.getScreenPosition());
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

	private synchronized void loop() {
		Rectangle player2coor;
		if(player2 != null) player2coor= player2.getPosition();
		else player2coor= null;
		Coordinates vectors= player.memorizePlayerMoves(player2coor, map);
		scroll(vectors);
		boss.memorizeBossMoves();
	 
		checkCollision();
		fireUpdate();
		boss.think(player.getCoordinates(), player2coor);
		
	}


	protected void fireUpdate() {
		for(LevelListener l:listeners) {
			l.update(new TransferEvent(player.getCoordinates(), player.getImages(), 
					boss.getCoordinates(), boss.getImages()));
		}
	}

	public void start(){
		inGame= true;
		runGameLoop();
	}
	
	public void update(TransferEvent te) {
		
		try {
			
			player2.setCoordinates(te.getNewPlayerPosition());
			player2.setImages(te.getPlayerImages());
	
		}catch(NullPointerException ne) {
			
			if(te.getNewPlayerPosition() != null) {
				player2= new Player2("ressources/player/set.txt");
				player2.setCoordinates(te.getNewPlayerPosition());
				player2.setImages(te.getPlayerImages());
			}
		}
	}


	private void checkCollision() {
		//TODO
	}
	

}
