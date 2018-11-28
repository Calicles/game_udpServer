package model;

import java.util.concurrent.locks.ReentrantLock;

import type.AbstractMap;
import type.AbstractTransfer;

public class IA_transfertStrategy_std extends AbstractTransfer {
	
	private Thread greyCell;
	private Rectangle ownPosition, player1, player2;
	private Coordinates lastVectors;
	private AbstractMap map;
	private boolean thinking;
	
	public IA_transfertStrategy_std(Rectangle ownPosition, Rectangle player1, AbstractMap map) {
		super();
		this.xVector= 0;
		this.yVector= -4;
		this.lastVectors= new Coordinates(0, 0);
	}
	
	@Override
	public void setAttributes(Rectangle ownPosition, Rectangle player1, AbstractMap map) {
		this.map= map;
		this.ownPosition= ownPosition;
		this.player1= player1;
		buildThinkPattern();
		greyCell.start();
	}
	
	private void buildThinkPattern() {
		greyCell= new Thread(()->{
			thinking= true;
			while(true){
		/*	if(Rectangle.isNext(ownPosition, player1, ownPosition.getDiagonal()))
				manHuntPlayer1();
			else if(Rectangle.isNext(ownPosition, player2, ownPosition.getDiagonal()))
				manHuntPlayer2();
			else {	
				findWay();
			}*/ findWay(); //TODO Remove
				pause();
			}
		});
		
	}
	
	private void findWay() {
		if(xVector == 0 && yVector == 0) {
			checkLastVectors();
		}
		this.adaptVectors(ownPosition, map);
	}

	private void checkLastVectors() {
		Rectangle tile;
		if(lastVectors.getX() < 0 || lastVectors.getX() > 0) {
			if(ownPosition.getY() != 0 && (tile= this.checkOnUpTiles(this.ownPosition, map)) == null) {
				yVector= -4; xVector= 0;
			}else if(ownPosition.getEndY() != map.getHeight() && (tile= this.checkOnDownTiles(ownPosition, map)) == null) {
				yVector= 4; xVector= 0;
			}else {
				//GO back
				if(lastVectors.getX() < 0)
					xVector= 4;
				else 
					xVector= -4;
				yVector= 0;
			}
		}else {
			if(ownPosition.getX() != 0 && (tile= this.checkLeftTiles(ownPosition, map)) == null) {
				xVector= -4; yVector= 0;
			}else if(ownPosition.getEndX() != map.getWidth() && (tile= this.checkRightTiles(ownPosition, map)) == null) {
				xVector= 4; yVector= 0;
			}else {
				if(lastVectors.getY() < 0)
					yVector= 4;
				else
					yVector= -4;
				xVector= 0;
			}
		}
		
	}

	private void manHuntPlayer1() {
		// TODO Auto-generated method stub
		
	}

	private void manHuntPlayer2() {
		// TODO Auto-generated method stub
		
	}

	public void think(Coordinates ownPosition, Coordinates player1, Coordinates player2) {
		update(ownPosition, player1, player2);
		
		synchronized(this) {
			notify();
			thinking= true;
		}
	}

	private void update(Coordinates ownPosition, Coordinates player1, Coordinates player2) {
		this.ownPosition.setCoordinates(ownPosition);
		this.player1.setCoordinates(player1);
		//this.player2.setCoordinates(player2);
	}

	private void pause() {
		try {
			synchronized(this) {
				thinking= false;
				wait();
			}
		}catch(InterruptedException ie) {}
	}

	@Override
	public Coordinates memorizeBossMoves() {
		if(!thinking) {
			// finished thinking
			if(xVector != 0 || yVector != 0)
				lastVectors.setCoordinates(xVector, yVector);
			return new Coordinates(xVector, yVector);
		}else									
			// don't moves
			return new Coordinates(0, 0);
	}

}
