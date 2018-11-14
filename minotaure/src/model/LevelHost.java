package model;

import java.awt.Graphics;

import type.AbstractLevel;
import type.LevelListener;

public class LevelHost extends AbstractLevel {
	
	private Boss boss;
	private Thread gameLoop;
	
	private boolean inGame;
	
	public LevelHost() {
		player= new Player();
		boss= new Boss();
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
		drawBoss(g);
	}
	
	private void drawBoss(Graphics g) {
		boss.draw(g, scrollBoxes.getScreenCoordinates());
	}


	private void runGameLoop() {
		gameLoop= new Thread(()-> {
			
			while(inGame) {
				loop();
			}
		});
		
		gameLoop.start();
	}
	
	private void loop() {
		Coordinates vectors= player.memorizeMoves();
		scroll(vectors);
		boss.memorizeMoves();
		
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
