package model;

import type.AbstractLevel;
import type.LevelListener;

public class LevelHost extends AbstractLevel {
	
	private Thread gameLoop;
	private Player player;
	private Player2 player2;
	private Boss boss;
	
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
	
	private void runGameLoop() {
		gameLoop= new Thread(()-> {
			
			while(inGame) {
				loop();
			}
		});
	}
	
	private void loop() {
		player.memorizeMoves();
		synchronized(this) {
			player2.memorizeMoves();
			boss.memorizeMoves();
			checkCollision();
		}
		fireUpdate();
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
