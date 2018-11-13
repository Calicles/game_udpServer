package model;

import java.awt.Graphics;

import type.AbstractLevel;
import type.LevelListener;

public class LevelHost extends AbstractLevel {
	
	protected Boss boss;
	private Thread gameLoop;
	
	private boolean inGame;
	
	public LevelHost() {
		player= new Player();
		boss= new Boss();
		inGame= false;
	}
	
	
	public void playerMovesLeft() {}
	public void playerMovesRight() {}
	public void playerMovesUp() {}
	public void playerMovesDown() {}
	
	public void drawLevel(Graphics g) {
		super.drawLevel(g);
		drawBoss(g);
	}
	
	private void drawBoss(Graphics g) {
		// TODO Auto-generated method stub
		
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
		player.memorizeMoves();
		boss.memorizeMoves();
		synchronized(this) {
			checkCollision();
			fireUpdate();
		}
	}
	
	protected void fireUpdate() {
		for(LevelListener l:listeners) {
			l.update(new TransferEvent(player.getCoordinates(), player2.getCoordinates(), boss.getCoordinates()));
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
		
		
	}

}
