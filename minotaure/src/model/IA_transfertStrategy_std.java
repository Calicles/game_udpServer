package model;

import java.util.concurrent.locks.ReentrantLock;

import type.AbstractMap;
import type.AbstractTransfer;

public class IA_transfertStrategy_std extends AbstractTransfer {
	
	private Thread greyCell;
	private ReentrantLock lock;
	
	public IA_transfertStrategy_std() {
		lock= new ReentrantLock();
		buildThinkPattern();
	}
	
	private void buildThinkPattern() {
		greyCell= new Thread(()->{
			
			pause();
		});
		
	}
	
	public void think() {
		synchronized(lock) {
			notify();
		}
	}

	private void pause() {
		try {
			synchronized(lock) {
			wait();
			}
		}catch(InterruptedException ie) {}
	}

	@Override
	public void memorizeBossMoves(Rectangle position, Rectangle playerPosition, AbstractMap map) {
		//l'IA trouve une direction
		findVector(position, playerPosition, map);
		adaptVectors(position, map);
		
		//Change les coordonnes du perso
		position.setCoordinates(position.getX() + xVector, position.getY() + yVector);
	}

	private Coordinates findVector(Rectangle position, Rectangle playerPosition, AbstractMap map) {
		// TODO Auto-generated method stub
		return null;
	}


}
